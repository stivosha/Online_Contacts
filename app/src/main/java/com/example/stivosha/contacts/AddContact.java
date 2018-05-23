package com.example.stivosha.contacts;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by stivosha on 22.05.2018.
 */

public class AddContact implements Runnable {
    public String url;
    private Gson gson = new GsonBuilder().create();
    Call<Contacts> call;
    Contact contact;
    AddContact(Contact contact){
        this.contact = contact;

    }
    @Override
    public void run() {
        url = "http://192.168.1.69:8085/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        RestApiAdd service = retrofit.create(RestApiAdd.class);
        Call<String> s = service.addContact(contact);
        s.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("1","add");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
