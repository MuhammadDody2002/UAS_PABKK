package com.if5b.komik_kamikaze.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.if5b.komik_kamikaze.R;

import io.github.muddz.styleabletoast.StyleableToast;

public class ProfileActivity extends AppCompatActivity {
    TextView username, notelephone, email;
    Button btnLogOut;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        username = findViewById(R.id.tv_username);
        notelephone = findViewById(R.id.tv_notelephone);
        email = findViewById(R.id.tv_email);
        btnLogOut = findViewById(R.id.btnLogout);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = mAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("Users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                username.setText(documentSnapshot.getString("username"));
                notelephone.setText(documentSnapshot.getString("notelephone"));
                email.setText(documentSnapshot.getString("email"));
            }
        });
        btnLogOut.setOnClickListener(view ->{
            mAuth.signOut();
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            StyleableToast.makeText(this, "Logout Berhasil", R.style.kamikazetoast).show();
        }
    }
}
