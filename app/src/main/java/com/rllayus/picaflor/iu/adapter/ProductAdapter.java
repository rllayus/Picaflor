package com.rllayus.picaflor.iu.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rllayus.picaflor.R;
import com.rllayus.picaflor.iu.DetailActivity;
import com.rllayus.picaflor.iu.SearchProductActivity;
import com.rllayus.picaflor.modelo.ProductItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo Laredo on 04-Aug-15.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> implements ItemClickListener{
    private ArrayList<ProductItem> items;
    private final Context context;
    public ProductAdapter(ArrayList<ProductItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ViewHolder(v,this);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method
     * should update the contents of the {@link ViewHolder#itemView} to reflect the item at
     * the given position.
     * <p/>
     * Note that unlike {@link android.widget.ListView}, RecyclerView will not call this
     * method again if the position of the item changes in the data set unless the item itself
     * is invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside this
     * method and should not keep a copy of it. If you need the position of an item later on
     * (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will have
     * the updated adapter position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductItem item=items.get(position);
        System.out.println("sizi: "+items.size());
        if(holder.name==null)
            System.out.println("nullll");
        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription());
        holder.price.setText(item.getPrice()+"$");
        Picasso.with(context).load(item.getImage()).into(holder.logo);
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return items.size();
    }
    @Override
    public void onItemClick(View view, int position) {
        DetailActivity.createInstance((Activity)context,items.get(position));
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView description;
        public TextView price;
        public ImageView logo;
        public TextView name;
        public ItemClickListener listener;
        public ViewHolder(View itemView,ItemClickListener listener) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.list_item_textview);
            description=(TextView) itemView.findViewById(R.id.list_item_description);
            price=(TextView)itemView.findViewById(R.id.list_item_price);
            logo=(ImageView)itemView.findViewById(R.id.list_item_logo);
            this.listener=listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v,getAdapterPosition());
        }
    }

}

interface ItemClickListener {
    void onItemClick(View view, int position);
}