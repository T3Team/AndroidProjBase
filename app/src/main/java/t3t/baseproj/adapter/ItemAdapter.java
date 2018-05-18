package t3t.baseproj.adapter;

import android.content.Context;

import java.util.List;

import base.t3t.baseprojlib.base.BaseRecyclerAdapter;
import base.t3t.baseprojlib.base.RecyclerViewHolder;
import t3t.baseproj.R;
import t3t.baseproj.model.ItemDescription;

public class ItemAdapter extends BaseRecyclerAdapter<ItemDescription> {

    public ItemAdapter(Context ctx, List<ItemDescription> data) {
        super(ctx, data);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.home_item_layout;
    }

    @Override
    public void bindData(RecyclerViewHolder holder, int position, ItemDescription item) {
        holder.getTextView(R.id.item_name).setText(item.getmKitName());
    }
}