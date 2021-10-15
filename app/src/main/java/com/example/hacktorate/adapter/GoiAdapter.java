package com.example.hacktorate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hacktorate.R;
import com.example.hacktorate.models.GoiDetail;

import java.util.List;

public class GoiAdapter extends RecyclerView.Adapter<GoiAdapter.GoiViewHolder>
{
    LayoutInflater layoutInflator;
    List<GoiDetail> goiDetails;


    public GoiAdapter(Context context,List<GoiDetail> details) {
      this.layoutInflator= LayoutInflater.from(context);
      this.goiDetails=details;
    }

    @NonNull
    @Override
    public GoiAdapter.GoiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.goiitem,parent,false);
       return new GoiViewHolder(view);




    }

    @Override
    public void onBindViewHolder(@NonNull GoiAdapter.GoiViewHolder holder, int position) {

        holder.itemName.setText(goiDetails.get(position).getGroceryName());
        holder.itemPlace.setText(goiDetails.get(position).getGroceryPlace());
        holder.itemPrice.setText("₹"+goiDetails.get(position).getGroceryPrice());


        holder.itemDate.setText(goiDetails.get(position).getGroceryTime());

    }

    @Override
    public int getItemCount() {
        return goiDetails.size();
    }

    public static class GoiViewHolder extends RecyclerView.ViewHolder{
        TextView itemName,itemPlace,itemPrice,itemDate;

        public GoiViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName=itemView.findViewById(R.id.ItemNameView);
            itemPlace=itemView.findViewById(R.id.ItemPlace);
            itemPrice=itemView.findViewById(R.id.ItemPrice);
            itemDate=itemView.findViewById(R.id.ItemDate);
        }
    }
}
