package t3t.baseproj.fragment.home;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import base.android.t3t.netrequestdemo.MainActivity;
import base.android.t3t.netrequestdemo.utils.ArouterUtils;
import base.t3t.companybusinesslib.base.BaseFragment;
import base.t3t.companybusinesslib.constant.NetDemoArouterParams;
import t3t.baseproj.adapter.ItemAdapter;
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
        BaseFragment fragment = (BaseFragment) ArouterUtils.getInstance().getFragment(NetDemoArouterParams.jumpToNetDemoMainragment).navigation();
        ItemDescription itemDescription = new ItemDescription(fragment, "网络请求Demo");
        data.add(itemDescription);
        return new ItemAdapter(getContext(), data);
    }

}
