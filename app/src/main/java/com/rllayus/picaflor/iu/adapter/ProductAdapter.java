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
    private List<ProductItem> items;
    private final Context context;
    public ProductAdapter(List<ProductItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    public List<ProductItem> getItems() {
        return items;
    }

    public void setItems(List<ProductItem> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ViewHolder(v,this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductItem item=items.get(position);
        System.out.println("sizi: "+items.size());
        if(holder.name==null)
            System.out.println("nullll");
        holder.name.setText(item.getNombre());
        holder.description.setText(item.getDescripcion());
        holder.price.setText(item.getPrecio()+"$");
        Picasso.with(context).load(item.getUrilogo()).into(holder.logo);
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