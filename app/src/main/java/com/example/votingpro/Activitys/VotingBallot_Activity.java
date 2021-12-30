package com.example.votingpro.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.votingpro.Costume_Classes.Main_Category;
import com.example.votingpro.Costume_Classes.MyApp;
import com.example.votingpro.Costume_Classes.Vote;
import com.example.votingpro.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class VotingBallot_Activity extends AppCompatActivity {

    private LinearLayout votingButtonLayout;

    private TextView tvName, tvStatus, tvSubject, tvMassage;
    private EditText etVotingEmotions;
    private ProgressBar progressBar;

    private String votingEmotions, categoryName;

    private Main_Category category;
    private int rvp;
    private OnSuccessListener<? super Void> votingSuccessListener;
    private OnCompleteListener<Void> addVoteInfoListener;
    private OnFailureListener votingFailureListener;
    private Vote vote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_ballot);

        category = (Main_Category) getIntent().getSerializableExtra("Category");
        rvp = (int) getIntent().getIntExtra("RVP", 0);

        tvName = findViewById(R.id.tvName_VB);
        tvStatus = findViewById(R.id.tvStatus_VB);
        tvSubject = findViewById(R.id.tvSubject_VB);
        tvMassage = findViewById(R.id.tvMassage_VB);
        etVotingEmotions = findViewById(R.id.etVM_VB);

        progressBar = findViewById(R.id.progressBar_VB);

        votingButtonLayout = findViewById(R.id.votingLayout);

        tvName.setText(category.getName());
        tvSubject.setText(category.getSubject());
        tvStatus.setText(category.getStatus());

        Log.d(MyApp.TAG, "Category Name --> " + category.getLocal_Id());


        // select category to voting
        switch (category.getLocal_Id()) {
            case 0: // Local id --> 0

                categoryName = "Local";

                break;

            case 1: // Entertainment id --> 1

                categoryName = "Entertainment";

                break;

            case 2: // Political id --> 2

                categoryName = "Political";

                break;

            case 3: // Social id --> 3

                categoryName = "Social";

                break;
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        // vote are submit to the DB successfully
        votingSuccessListener = new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                progressBar.setVisibility(View.GONE);
                votingButtonLayout.setVisibility(View.VISIBLE);

                Log.d(MyApp.TAG, "Vote Are Submitted");


            }
        };

        // the vote information submit
        addVoteInfoListener = new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {

                    progressBar.setVisibility(View.GONE);
                    votingButtonLayout.setVisibility(View.VISIBLE);

                    Log.d(MyApp.TAG, "Add Vote Information");

                    Intent intent = new Intent(getApplicationContext(), CategoryList_Activity.class);
                    startActivity(intent);
                    finish();

                } else {

                    Log.d(MyApp.TAG, "Add Vote Exception --> " + task.getException());

                }

            }
        };


        // the vote information submit
        votingFailureListener = new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Log.d(MyApp.TAG, "Voting Submit || Exception --> " + e.getMessage().toString());

            }
        };

        // checking the category is already vote or not
        validateForVote();


    }
    // checking the category is already vote or not
    // checking category details the vote is submitted or not
    private void validateForVote() {



        MyApp.myFireStore.collection("Voting_Container")
                .document("Uid")
                .collection(categoryName)
                .whereEqualTo("name", category.getName())
                .whereEqualTo("status", category.getStatus())
                .whereEqualTo("subject", category.getSubject())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Vote vote = document.toObject(Vote.class);

                                if (vote.isVoting()) {

                                    Log.d(MyApp.TAG, "IsVoting --> " + vote.isVoting());
                                    votingButtonLayout.setVisibility(View.GONE);
                                    tvMassage.setVisibility(View.VISIBLE);

                                }


                            }

                        } else {
                            Log.d(MyApp.TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    // this function store vote information on DB
    private void submitVoteInfo() {


        vote = new Vote(rvp, category.getName(), category.getStatus(), category.getSubject(), "Voting_Day", votingEmotions, true);

        Log.d(MyApp.TAG, "Emotions --> "+votingEmotions);

        MyApp.myFireStore.collection("Voting_Container")
                .document("Uid")
                .collection(categoryName)
                .document()
                .set(vote)
                .addOnCompleteListener(addVoteInfoListener);


    }

    // this function check the views are empty or not
    private boolean isViewsEmpty() {

        boolean result = true;

        progressBar.setVisibility(View.VISIBLE);
        votingButtonLayout.setVisibility(View.INVISIBLE);

        votingEmotions = etVotingEmotions.getText().toString().trim();

        if (votingEmotions.isEmpty()) {
            progressBar.setVisibility(View.GONE);

            votingButtonLayout.setVisibility(View.VISIBLE);

            etVotingEmotions.setError("Enter the emotions");

            result = false;

        } else if (etVotingEmotions.length() >= 25) {


            progressBar.setVisibility(View.GONE);

            votingButtonLayout.setVisibility(View.VISIBLE);

            etVotingEmotions.setError("Enter the emotions of 25 characters");

            result = false;


        }

        return result;
    }

    // when the user clicks like button
    public void onLikeButtonClick(View view) {

        if (isViewsEmpty()) {

            submitVoteInfo();

            MyApp.myFireStore.collection(categoryName)
                    .document(category.getDbRef())
                    .update("like_Voting", FieldValue.increment(1))
                    .addOnSuccessListener(votingSuccessListener)
                    .addOnFailureListener(votingFailureListener);

        }

    }

    // when the user clicks neutral button
    public void onNeutralButtonClick(View view) {

        if (isViewsEmpty()) {

            submitVoteInfo();

            MyApp.myFireStore.collection(categoryName)
                    .document(category.getDbRef())
                    .update("neutral_Voting", FieldValue.increment(1))
                    .addOnSuccessListener(votingSuccessListener)
                    .addOnFailureListener(votingFailureListener);

        }
    }

    // when the user clicks dislike button
    public void onDislikeButtonClick(View view) {

        if (isViewsEmpty()) {

            submitVoteInfo();

            MyApp.myFireStore.collection(categoryName)
                    .document(category.getDbRef())
                    .update("dislike_Voting", FieldValue.increment(1))
                    .addOnSuccessListener(votingSuccessListener)
                    .addOnFailureListener(votingFailureListener);




        }


    }
}