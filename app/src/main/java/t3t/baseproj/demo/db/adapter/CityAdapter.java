package t3t.baseproj.demo.db.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import base.t3t.baseprojlib.base.RecyclerBaseAdapter;
import t3t.baseproj.R;
import t3t.baseproj.demo.db.holder.CityBeanHolder;
import t3t.baseproj.demo.db.model.CityList;

/**
 * Created by dale on 2018/8/14.
 */
public class CityAdapter extends RecyclerBaseAdapter<CityList.ListBean, RecyclerView.ViewHolder> {
    public CityAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType, LayoutInflater inflater) {
        return new CityBeanHolder(inflater.inflate(R.layout.layout_list_city_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, int position, CityList.ListBean listBean) {
        ((CityBeanHolder) holder).setItem(listBean);
    }
}
