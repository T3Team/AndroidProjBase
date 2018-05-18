package t3t.baseproj.fragment.home;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import base.android.t3t.netrequestdemo.NetFragment;
import t3t.baseproj.adapter.ItemAdapter;
import t3t.baseproj.model.ItemDescription;

public class HomeLabController extends HomeController {

    public HomeLabController(Context context) {
        super(context);
    }

    @Override
    protected String getTitle() {
        return "Lab";
    }

    @Override
    ItemAdapter getItemAdapter() {

        List<ItemDescription> data = new ArrayList<>();

        return new ItemAdapter(getContext(),data);
    }

}
