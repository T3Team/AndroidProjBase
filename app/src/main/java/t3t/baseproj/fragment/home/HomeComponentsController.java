package t3t.baseproj.fragment.home;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import base.t3t.baseprojlib.utils.DevUtil;
import t3t.baseproj.adapter.ItemAdapter;
import t3t.baseproj.model.ItemDescription;


public class HomeComponentsController extends HomeController {

    public HomeComponentsController(Context context) {
        super(context);
    }

    @Override
    protected String getTitle() {
        return "Components";
    }

    @Override
    ItemAdapter getItemAdapter() {
        DevUtil.v("dale","--->getItemAdapter");
        List<ItemDescription> data = new ArrayList<>();
        ItemDescription itemDescription = new ItemDescription(QMUIDEMOFragment.class, "QMUI Demo");
        data.add(itemDescription);
        return new ItemAdapter(getContext(), data);
    }
}
