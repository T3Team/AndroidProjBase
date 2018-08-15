package t3t.baseproj.fragment.home;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import base.android.t3t.netrequestdemo.NetFragment;
import t3t.baseproj.adapter.ItemAdapter;
import t3t.baseproj.demo.ImageLoader.fragment.SimpleLoaderDemoFragment;
import t3t.baseproj.demo.db.RealDemoFragment;
import t3t.baseproj.model.ItemDescription;

public class HomeUtilController extends HomeController {

    public HomeUtilController(Context context) {
        super(context);
    }

    @Override
    protected String getTitle() {
        return "Helper";
    }

    @Override
    ItemAdapter getItemAdapter() {
        List<ItemDescription> data = new ArrayList<>();
        ItemDescription itemDescription = new ItemDescription(NetFragment.class, "网络请求Demo");
        data.add(itemDescription);
        ItemDescription itemDescription2 = new ItemDescription(SimpleLoaderDemoFragment.class, "图片加载Demo");
        data.add(itemDescription2);
        ItemDescription itemDescription3 = new ItemDescription(RealDemoFragment.class, "realm演示Demo");
        data.add(itemDescription3);
        return new ItemAdapter(getContext(), data);
    }

}
