package com.if5b.komik_kamikaze;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.if5b.komik_kamikaze.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Result result = getIntent().getParcelableExtra("Data");
        binding.tvName.setText(result.getNm_komik());
        binding.tvNameAuthor.setText(result.getId_komik());
        binding.tvTC.setText(result.getGenre());
        Glide.with(DetailActivity.this).load(result.getGbr_komik()).into(binding.imgPhoto);
    }
}