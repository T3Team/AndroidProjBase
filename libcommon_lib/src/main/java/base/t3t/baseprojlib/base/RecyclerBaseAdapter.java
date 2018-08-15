package base.t3t.baseprojlib.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerBaseAdapter<Item, ViewHolder extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<ViewHolder> {

    private final List<Item> items = new ArrayList<Item>();
    private final LayoutInflater inflater;

    public RecyclerBaseAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void append(Item item) {
        if (item != null) {
            final int position = getItemCount();
            items.add(item);
            notifyItemInserted(position);
        }
    }

    public void appendTop(Item item) {
        if (item != null) {
            final int position = getItemCount();
            items.add(0, item);
            notifyItemInserted(position);
        }
    }

    public void append(List<? extends Item> items) {
        if (items == null || this.items == null) {
            return;
        }
        final int position = getItemCount();
        this.items.addAll(items);
        notifyItemRangeInserted(position, items.size());
    }

    public void swapData(List<? extends Item> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void swapItem(int position, Item item) {
        if (items.size() > position) {
            items.remove(position);
            items.add(position, item);
            notifyItemChanged(position);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public final Item getItem(int position) {
        return items.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateViewHolder(parent, viewType, inflater);
    }

    protected abstract ViewHolder onCreateViewHolder(ViewGroup parent, int viewType, LayoutInflater inflater);

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        onBindViewHolder(holder, position, getItem(position));
    }

    protected abstract void onBindViewHolder(ViewHolder holder, int position, Item item);

    public void remove(Item item) {
        if (items != null && items.remove(item)) {
            notifyDataSetChanged();
        }
    }

    public List<Item> getItems() {
        return items;
    }
}
