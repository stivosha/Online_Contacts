package com.example.stivosha.contacts;

import android.graphics.ColorSpace;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by stivosha on 22.05.2018.
 */

public interface RestApi {
    @POST("get_list")
    Call<Contacts> getList();
}
