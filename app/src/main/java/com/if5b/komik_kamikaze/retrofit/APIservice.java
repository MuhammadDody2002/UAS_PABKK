package com.if5b.komik_kamikaze.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIservice {
    private static String BASE_URL_JOB = "https://kkamikaze.000webhostapp.com/";
    private static Retrofit retrofit;

    public static APIEnpoint apiEndpoint()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_JOB)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(APIEnpoint.class);
    }
}
