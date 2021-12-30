package com.example.votingpro.Costume_Classes;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class MyApp extends Application {

    public static String TAG = "mt";

    // DB related code

    // user authentication code
    public static FirebaseAuth myAuth;

    // FirebaseStore text data store
    public static FirebaseFirestore myFireStore;

    // FirebaseStorage image data store
    public static FirebaseStorage myStorage;


    @Override
    public void onCreate() {
        super.onCreate();

        myAuth = FirebaseAuth.getInstance();

        myFireStore = FirebaseFirestore.getInstance();

        myStorage = FirebaseStorage.getInstance();

    }
}
