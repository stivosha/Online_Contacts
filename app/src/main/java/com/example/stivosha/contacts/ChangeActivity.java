package com.example.stivosha.contacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.stivosha.contacts.MainActivity.contentValues;
import static com.example.stivosha.contacts.MainActivity.database;
import static com.example.stivosha.contacts.MainActivity.list_of_contacts;

public class ChangeActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editLastName;
    private EditText editNumber;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        pos = getIntent().getIntExtra("position",0);
        editName = findViewById(R.id.enterName);
        editLastName = findViewById(R.id.enterLastName);
        editNumber = findViewById(R.id.enterNumber);
        editName.setText(list_of_contacts.get(pos).getName());
        editLastName.setText(list_of_contacts.get(pos).getLastName());
        editNumber.setText(list_of_contacts.get(pos).getNumber());

        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeActivity.this.finish();
            }
        });
        Button change = findViewById(R.id.create);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //list_of_contacts.add(new Contact(editName.getText().toString(),editLastName.getText().toString(),editNumber.getText().toString()));
                //AddPointActivity.this.getParent().
                ChangeContact a = new ChangeContact(list_of_contacts.get(pos),new Contact(editName.getText().toString(),editLastName.getText().toString(),editNumber.getText().toString()));
                a.run();
                contentValues.put(DBHelper.KEY_NAME,editName.getText().toString());
                contentValues.put(DBHelper.KEY_LAST_NAME,editLastName.getText().toString());
                contentValues.put(DBHelper.KEY_NUMBER,editNumber.getText().toString());
                database.update(DBHelper.TABLE_CONTACTS, contentValues,DBHelper.KEY_NUMBER+"= "+list_of_contacts.get(pos).getNumber(),null);
                    list_of_contacts.get(pos).setName(editName.getText().toString());
                    list_of_contacts.get(pos).setLastName(editLastName.getText().toString());
                    list_of_contacts.get(pos).setNumber(editNumber.getText().toString());
                ChangeActivity.this.finish();
            }
        });
    }
}
