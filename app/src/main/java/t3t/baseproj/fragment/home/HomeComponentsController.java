package t3t.baseproj.fragment.home;

import android.content.Context;

import java.util.ArrayList;

import t3t.baseproj.model.QDItemDescription;


public class HomeComponentsController extends HomeController {

    public HomeComponentsController(Context context) {
        super(context);
    }

    @Override
    protected String getTitle() {
        return "Components";
    }

}
