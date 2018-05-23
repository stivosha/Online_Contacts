package com.example.stivosha.contacts;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by stivosha on 22.05.2018.
 */

public interface RestApiChangeContact {
    @POST("change_contact")
    Call<String> changeContact(@Body Contact[] contact);
}
