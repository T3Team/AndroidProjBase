package t3t.baseproj.net;

import io.reactivex.Observable;
import retrofit2.http.GET;
import t3t.baseproj.demo.db.model.CityList;

public interface IDemoService {

    @GET("td1nyyK70a1a053dfad045d295228eaf093421d6a8c9179?uri=apptest/getcitylist")
    Observable<CityList> getCityList();


}
