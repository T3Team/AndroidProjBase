package t3t.baseproj.demo.db.model;

import java.util.List;

/**
 * Created by dale on 2018/8/14.
 */
public class CityList {

    private boolean isRefreash = true;

    public boolean isRefreash() {
        return isRefreash;
    }

    public void setRefreash(boolean refreash) {
        isRefreash = refreash;
    }

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

}
