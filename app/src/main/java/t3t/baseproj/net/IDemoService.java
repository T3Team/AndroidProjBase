package t3t.baseproj.net;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IDemoService {


    @GET("xxxx/xxxx")
    Observable<String> getList();


}
