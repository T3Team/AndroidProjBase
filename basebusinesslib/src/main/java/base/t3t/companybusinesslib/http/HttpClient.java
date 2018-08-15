package base.t3t.companybusinesslib.http;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import base.t3t.baseprojlib.utils.DevUtil;
import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class HttpClient {
    public ProgressResponseBody.ProgressListener progressListener;
    private Retrofit client;

    /**
     * base url 建议统一以/结尾
     *
     * @return
     */
    protected abstract String setBaseUrl();

    public Retrofit getHttpClient() {
        return getHttpClient_(null);
    }

    /**
     * @param tempProgressListener 进度监听
     * @return
     */
    public Retrofit getHttpClient(final ProgressResponseBody.ProgressListener tempProgressListener) {
        return getHttpClient_(tempProgressListener);
    }

    private Retrofit getHttpClient_(final ProgressResponseBody.ProgressListener tempProgressListener) {
        progressListener = tempProgressListener;
        if (client == null) {
            Authenticator mAuthenticator = new Authenticator() {
                @Override
                public Request authenticate(Route route, Response response) throws IOException {
                    if (responseCount(response) >= 2) {
                        // If both the original call and the call with refreshed token failed,it will probably keep failing, so don't try again.
                        return null;
                    }
                    return response
                            .request().newBuilder()
                            .build();
                }
            };
            Interceptor mRequestInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Response response = chain.proceed(request);
                    ResponseBody body = response.body();
                    return response.newBuilder().body(new ProgressResponseBody(body, progressListener))
                            .build();
                }
            };

            HttpLoggingInterceptor logIterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    DevUtil.e("Http", message);
                }
            });
            logIterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            ApiStatusCodeInterceptor apiStatusCodeInterceptor = new ApiStatusCodeInterceptor();
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .addNetworkInterceptor(mRequestInterceptor)
                    .addNetworkInterceptor(new HeaderInterceptor())
                    .addInterceptor(apiStatusCodeInterceptor)
                    .addInterceptor(logIterceptor)
                    .authenticator(mAuthenticator)
                    .build();

            client = new Retrofit.Builder()
                    .baseUrl(setBaseUrl())
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return client;
    }


    private static int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }
}
