package com.if5b.komik_kamikaze.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.if5b.komik_kamikaze.HomeActivity;
import com.if5b.komik_kamikaze.R;
import com.if5b.komik_kamikaze.RegisterActivity;
import com.if5b.komik_kamikaze.databinding.ActivityLoginBinding;

import io.github.muddz.styleabletoast.StyleableToast;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        binding.LBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.edtEmail.getText().toString();
                String password = binding.edtPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    binding.edtEmail.setError("Email jangan Kosong");
                    binding.edtEmail.requestFocus();
                }
                if (TextUtils.isEmpty(password)) {
                    binding.edtPassword.setError("Password jangan Kosong");
                    binding.edtPassword.requestFocus();
                } else {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    StyleableToast.makeText(LoginActivity.this, "Login Berhasil", R.style.kamikazetoast).show();
                                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    StyleableToast.makeText(LoginActivity.this, "Login Gagal", R.style.kamikazetoast).show();
                                }
                            });
                }
            }
        });
        binding.tvRegister.setOnClickListener(view ->{
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }
}
