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

public class MyRVAMainCategoryList extends RecyclerView.Adapter<MyRVAMainCategoryList.MyViewHolder> {

    private ArrayList<Main_Category> categoryList;
    private MainRVAEvent activity;

    public MyRVAMainCategoryList(Context context, ArrayList<Main_Category> categoryList)
    {
        this.activity = (MainRVAEvent) context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mc_rva_list_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Main_Category category = categoryList.get(position);

        holder.itemView.setTag(categoryList.get(position));

        holder.tvName.setText(category.getName());
        holder.tvStatus.setText(category.getStatus());
        holder.tvSubject.setText(category.getSubject());
        holder.tvLikeVote.setText("" + category.getLike_Voting());
        holder.tvNeutralVote.setText("" + category.getNeutral_Voting());
        holder.tvDislikeVote.setText("" + category.getDislike_Voting());


    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView ivCunt;
        private TextView tvName, tvStatus, tvSubject, tvLikeVote, tvNeutralVote, tvDislikeVote;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            ivCunt = itemView.findViewById(R.id.ivCunt_LL);
            tvName = itemView.findViewById(R.id.tvName_LL);
            tvStatus = itemView.findViewById(R.id.tvStatus_LL);
            tvSubject = itemView.findViewById(R.id.tvTodaySubject_LL);
            tvLikeVote = itemView.findViewById(R.id.tvLikeVote_LL);
            tvNeutralVote = itemView.findViewById(R.id.tvNeutralVote_LL);
            tvDislikeVote = itemView.findViewById(R.id.tvDislikeVote_LL);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    activity.onRVItemClick( categoryList.indexOf( ( Main_Category ) itemView.getTag() ) );

                }
            });


        }
    }
}
