package com.vlive.retrofit;

import com.vlive.pojo.Town;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Georges on 28/11/2016.
 */

public interface  VliveAPI {
    @GET("http://localhost/vlive-api/index.php/api/v1/towns")
    Call<ArrayList<Town>> getTowns();
}
