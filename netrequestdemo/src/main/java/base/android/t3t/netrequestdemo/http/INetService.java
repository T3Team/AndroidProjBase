package base.android.t3t.netrequestdemo.http;

import base.android.t3t.netrequestdemo.entity.NetDemoBean;
import io.reactivex.Observable;
import retrofit2.http.GET;


public interface INetService {


    @GET("td1nyyK70a1a053dfad045d295228eaf093421d6a8c9179?uri=apptest/getlist")
    Observable<NetDemoBean> getTesContent();

}
