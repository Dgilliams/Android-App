package com.theygiveafck.theygiveafck;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private List<Item> itemsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, desc;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.titleTextView);
            desc = view.findViewById(R.id.descriptionTextView);
            image = view.findViewById(R.id.itemImageView);
        }
    }


    public ItemAdapter(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item item = itemsList.get(position);
        holder.title.setText(item.getTitle());
        holder.desc.setText(item.getDesc());
        holder.image.setImageResource(item.getImg());
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}
