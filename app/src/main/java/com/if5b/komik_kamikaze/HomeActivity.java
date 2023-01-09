package com.if5b.komik_kamikaze;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.if5b.komik_kamikaze.activities.ProfileActivity;
import com.if5b.komik_kamikaze.databinding.ActivityHomeBinding;
import com.if5b.komik_kamikaze.retrofit.APIservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;

    private komikadapter komikadapter;
    private List<Result> results = new ArrayList<>();
    private ItemClickListener<Result> itemClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupRecyclerViewKomik();
        getDataFromAPI();

        binding.navigasi.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.profil:
                    Intent intent3 = new Intent(HomeActivity.this, ProfileActivity.class);
                    startActivity(intent3);
                    finish();
                    break;
            };
            return true;
        });
    }
    private void setupRecyclerViewKomik() {
        itemClickListener = new ItemClickListener<Result>() {
            @Override
            public void onClick(Result data) {
              Intent intent = new Intent(HomeActivity.this,DetailActivity.class);
              intent.putExtra("Data",data);
              startActivity(intent);
            }
        };
        komikadapter = new komikadapter(itemClickListener);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 100, RecyclerView.HORIZONTAL, false);
        binding.rvKomik.setLayoutManager(layoutManager);
        binding.rvKomik.setAdapter(komikadapter);
    }
    private void getDataFromAPI()
    {
        APIservice.apiEndpoint().getkomik()
                .enqueue(new Callback<komikmodel>() {
                    @Override
                    public void onResponse(Call<komikmodel> call, Response<komikmodel> response) {
                        if(response.isSuccessful())
                        {
                            List<Result> results = response.body().getResult();
                            komikadapter.setData(results);
                        }
                    }

                    @Override
                    public void onFailure(Call<komikmodel> call, Throwable t) {

                    }
                });
//
    }
}