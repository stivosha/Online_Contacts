package com.example.stivosha.contacts;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by stivosha on 22.05.2018.
 */

public interface RestApiAdd {
    @POST("add_contact")
    Call<String> addContact(@Body Contact contact);
}
