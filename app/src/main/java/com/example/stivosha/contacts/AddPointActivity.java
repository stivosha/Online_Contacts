package com.example.stivosha.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.stivosha.contacts.MainActivity.contentValues;
import static com.example.stivosha.contacts.MainActivity.database;
import static com.example.stivosha.contacts.MainActivity.list_of_contacts;

public class AddPointActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editLastName;
    private EditText editNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_point);

        editName = findViewById(R.id.enterName);
        editLastName = findViewById(R.id.enterLastName);
        editNumber = findViewById(R.id.enterNumber);


        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddPointActivity.this.finish();
            }
        });
        Button create = findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list_of_contacts.add(new Contact(editName.getText().toString(),editLastName.getText().toString(),editNumber.getText().toString()));
                AddContact a = new AddContact(new Contact(editName.getText().toString(),editLastName.getText().toString(),editNumber.getText().toString()));
                a.run();
                contentValues.put(DBHelper.KEY_NAME,editName.getText().toString());
                contentValues.put(DBHelper.KEY_LAST_NAME,editLastName.getText().toString());
                contentValues.put(DBHelper.KEY_NUMBER,editNumber.getText().toString());
                database.insert(DBHelper.TABLE_CONTACTS,null,contentValues);

                //AddPointActivity.this.getParent().
                AddPointActivity.this.finish();
            }
        });
    }
}
