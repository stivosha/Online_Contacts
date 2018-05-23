package com.example.stivosha.contacts;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by stivosha on 22.05.2018.
 */

public interface RestApiRmove {
    @POST("remove_contact")
    Call<String> removeContact(@Body Contact contact);
}
