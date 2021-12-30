package com.example.votingpro.Activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.votingpro.Adapters.MyRVAMainCategoryList;
import com.example.votingpro.Costume_Classes.Main_Category;
import com.example.votingpro.Costume_Classes.MyApp;
import com.example.votingpro.Interfaces.MainRVAEvent;
import com.example.votingpro.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CategoryList_Activity extends AppCompatActivity implements MainRVAEvent {

    private RecyclerView rvList;
    private ArrayList<Main_Category> categoryList;
    private RecyclerView.LayoutManager layoutManager;
    private MyRVAMainCategoryList myAdapter;
    private OnCompleteListener<QuerySnapshot> setCompleteListener;
    private Main_Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        category = (Main_Category) getIntent().getSerializableExtra("Category");

        rvList = findViewById(R.id.rvList_Category);

        categoryList = new ArrayList<>();

        layoutManager = new LinearLayoutManager(this);

        myAdapter = new MyRVAMainCategoryList(this, categoryList);

        rvList.setLayoutManager(layoutManager);
        rvList.setAdapter(myAdapter);

        // getting all list items
        setCompleteListener = new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Main_Category mainCategory = document.toObject(Main_Category.class);

                        categoryList.add(mainCategory);

                        myAdapter.notifyDataSetChanged();
                    }

                } else {
                    Log.d(MyApp.TAG, "Error getting documents: ", task.getException());
                }
            }
        };



    }

    @Override
    public void onRVItemClick(int position) {

        Intent intent = new Intent(getApplicationContext(), VotingBallot_Activity.class);

        intent.putExtra("Category", categoryList.get(position));
        intent.putExtra("RVP", position);

        startActivityForResult(intent, 1);

    }

    @Override
    protected void onPause() {
        super.onPause();

        categoryList.clear();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {

            if (requestCode == RESULT_OK) {

                MyApp.myFireStore.collection(category.getCategory_Name()).get().addOnCompleteListener(setCompleteListener);

            }
            if (resultCode == RESULT_CANCELED) {

            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();


        MyApp.myFireStore.collection(category.getCategory_Name()).get().addOnCompleteListener(setCompleteListener);

    }
}