package com.example.stivosha.contacts;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static ListView listView;
    public static List<Contact> list_of_contacts;
    public static ContactAdapter adapter;
    public static DBHelper dbHelper;
    public static SQLiteDatabase database;
    public static ContentValues contentValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        contentValues = new ContentValues();

        list_of_contacts = new ArrayList<Contact>();
        listView = (ListView) findViewById(R.id.list);
        adapter = new ContactAdapter(getApplicationContext(), list_of_contacts,this);
        Updating u = new Updating();
        u.run();
        listView.setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddPointActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }
//    private List<Contact> getData(){
//
//        List<Contact> list = new ArrayList<Contact>();
//        //list.add(new Contact("Гоша","Садовский","+79826231623"));
//        return list;
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        adapter.notifyDataSetChanged();
//        adapter = new ContactAdapter(getApplicationContext(), list_of_contacts,this);
//        listView.setAdapter(adapter);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public MainActivity getActivity(){
        return MainActivity.this;
    }
}
