package t3t.baseproj.demo.db.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import t3t.baseproj.R;
import t3t.baseproj.demo.db.model.CityList;

public class CityBeanHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.name)
    TextView txName;

    public CityBeanHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }


    public void setItem(final CityList.ListBean item) {
        txName.setText(item.getName());
    }

}
