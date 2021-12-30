package com.example.votingpro.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.votingpro.Costume_Classes.Main_Category;
import com.example.votingpro.Interfaces.MainRVAEvent;
import com.example.votingpro.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyRVAMainCategory extends RecyclerView.Adapter<MyRVAMainCategory.MyViewHolder> {

    private ArrayList<Main_Category> categoryList;
    private MainRVAEvent activity;

    public MyRVAMainCategory(Context context, ArrayList<Main_Category> categoryList)
    {
        this.activity = (MainRVAEvent) context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mc_rva_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Main_Category category = categoryList.get(position);

        holder.itemView.setTag(categoryList.get(position));

        holder.tvCategory.setText(category.getCategory_Name());

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView categoryImage;
        private TextView tvCategory;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            categoryImage = itemView.findViewById(R.id.ivCategory_MC);
            tvCategory = itemView.findViewById(R.id.tvCategory_MC);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    activity.onRVItemClick( categoryList.indexOf( ( Main_Category ) itemView.getTag() ) );

                }
            });


        }
    }
}
