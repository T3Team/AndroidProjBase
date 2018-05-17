package base.android.t3t.netrequestdemo.http;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface INetService {


    @GET("netdemo/getcontent")
    Observable<String> getTesContent();

}
