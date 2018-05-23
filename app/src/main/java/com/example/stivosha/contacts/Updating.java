package com.example.stivosha.contacts;


import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.stivosha.contacts.MainActivity.adapter;
import static com.example.stivosha.contacts.MainActivity.contentValues;
import static com.example.stivosha.contacts.MainActivity.database;
import static com.example.stivosha.contacts.MainActivity.dbHelper;
import static com.example.stivosha.contacts.MainActivity.list_of_contacts;

/**
 * Created by stivosha on 22.05.2018.
 */

public class Updating implements Runnable {
    public String url;
    private Gson gson = new GsonBuilder().create();
    Call<Contacts> call;
    Updating(){
    }
    @Override
    public void run() {
            url = "http://192.168.1.69:8085/";
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            RestApi service = retrofit.create(RestApi.class);
        call = service.getList();
        call.enqueue(new Callback<Contacts>() {
            @Override
            public void onResponse(Call<Contacts> call, Response<Contacts> response) {
                dbHelper.onUpgrade(database,1,1);
                for(int i = 0; i < response.body().contacts.size(); i++){
                    contentValues.put(DBHelper.KEY_NAME,response.body().getContact(i).getName());
                    contentValues.put(DBHelper.KEY_LAST_NAME,response.body().getContact(i).getLastName());
                    contentValues.put(DBHelper.KEY_NUMBER,response.body().getContact(i).getNumber());
                    database.insert(DBHelper.TABLE_CONTACTS,null,contentValues);
                    list_of_contacts.add(response.body().getContact(i));
                    Log.d("1",response.body().getContact(i).getName());
                    //Log.i("names",response.body().getContact(i).getName());
                }
                adapter.notifyDataSetChanged();
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS,null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                    int lastNameIndex = cursor.getColumnIndex(DBHelper.KEY_LAST_NAME);
                    int numberIndex = cursor.getColumnIndex(DBHelper.KEY_NUMBER);
                    do{
                        Log.d("log","ID = "+ cursor.getInt(idIndex)+
                                ",name = " + cursor.getString(nameIndex)+
                                ",last name = "+ cursor.getString(lastNameIndex)+
                                ",number = "+ cursor.getString(numberIndex));
                    }while (cursor.moveToNext());
                    cursor.close();
                }else {
                    Log.d("log","0 column");
                }
            }

            @Override
            public void onFailure(Call<Contacts> call, Throwable t) {

            }
        });
    }
}
