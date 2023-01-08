package com.if5b.komik_kamikaze.retrofit;

import com.if5b.komik_kamikaze.komikmodel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIEnpoint {
    @GET("komik.php")
    Call<komikmodel> getkomik();
}
