package com.if5b.komik_kamikaze.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.if5b.komik_kamikaze.R;
import com.if5b.komik_kamikaze.databinding.ActivityRegisterBinding;

import java.util.HashMap;
import java.util.Map;

import io.github.muddz.styleabletoast.StyleableToast;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    FirebaseFirestore fstore;
    FirebaseAuth mAuth;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        binding.RBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.edtFullname.getText().toString();
                String email = binding.edtEmail.getText().toString();
                String notelephone = binding.edtNotelp.getText().toString();
                String password = binding.edtPassword.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    binding.edtFullname.setError("UserName jangan Kosong");
                    binding.edtFullname.requestFocus();
                }
                if (TextUtils.isEmpty(email)) {
                    binding.edtEmail.setError("Email jangan Kosong");
                    binding.edtEmail.requestFocus();
                }
                if (TextUtils.isEmpty(notelephone)) {
                    binding.edtNotelp.setError("Nomor Telephone jangan Kosong");
                    binding.edtNotelp.requestFocus();
                }
                if (TextUtils.isEmpty(password)) {
                    binding.edtPassword.setError("Password jangan Kosong");
                    binding.edtPassword.requestFocus();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    StyleableToast.makeText(RegisterActivity.this, "Register Berhasil", R.style.kamikazetoast).show();
                                    userID = mAuth.getCurrentUser().getUid();
                                    DocumentReference documentReference = fstore.collection("Users").document(userID);
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("username", username);
                                    user.put("notelephone", notelephone);
                                    user.put("email", email);
                                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(RegisterActivity.this, "Username Telah di buat", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    StyleableToast.makeText(RegisterActivity.this, "Register Gagal", R.style.kamikazetoast).show();
                                }
                            });
                }
            }
        });

        binding.tvLogin.setOnClickListener(view -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }
}