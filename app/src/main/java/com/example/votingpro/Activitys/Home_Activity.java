package com.example.votingpro.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.votingpro.Adapters.MyRVAMainCategory;
import com.example.votingpro.Costume_Classes.Main_Category;
import com.example.votingpro.Interfaces.MainRVAEvent;
import com.example.votingpro.R;

import java.util.ArrayList;

public class Home_Activity extends AppCompatActivity implements MainRVAEvent {

    private RecyclerView rvList;
    private ArrayList<Main_Category> categoryList;
    private RecyclerView.LayoutManager layoutManager;
    private MyRVAMainCategory myRVAMainCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        rvList = findViewById(R.id.rvList_Home);

        categoryList = new ArrayList<>();

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);

        myRVAMainCategory = new MyRVAMainCategory(this, categoryList);

        rvList.setLayoutManager(layoutManager);

        rvList.setAdapter(myRVAMainCategory);

        Main_Category categoryLocal = new Main_Category();
        Main_Category categoryEntertainment = new Main_Category();
        Main_Category categoryPolitical = new Main_Category();
        Main_Category categorySocial = new Main_Category();

        categoryLocal.setCategory_Name("Local");
        categoryEntertainment.setCategory_Name("Entertainment");
        categoryPolitical.setCategory_Name("Political");
        categorySocial.setCategory_Name("Social");

        categoryList.add(categoryLocal);
        categoryList.add(categoryEntertainment);
        categoryList.add(categoryPolitical);
        categoryList.add(categorySocial);


    }

    @Override
    public void onRVItemClick(int position) {

        Toast.makeText(getApplicationContext(), "Category Choose " + categoryList.get(position).getCategory_Name(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), CategoryList_Activity.class);

        intent.putExtra("Category", categoryList.get(position));

        startActivity(intent);

    }
}