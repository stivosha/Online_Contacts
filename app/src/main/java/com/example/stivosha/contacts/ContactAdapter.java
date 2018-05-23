package com.example.stivosha.contacts;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;
import static com.example.stivosha.contacts.MainActivity.adapter;
import static com.example.stivosha.contacts.MainActivity.database;
import static com.example.stivosha.contacts.MainActivity.dbHelper;
import static com.example.stivosha.contacts.MainActivity.listView;
import static com.example.stivosha.contacts.MainActivity.list_of_contacts;

/**
 * Created by stivosha on 16.05.2018.
 */

public class ContactAdapter extends BaseAdapter {

    private List<Contact> list;
    private LayoutInflater layoutInflater;
    private MainActivity mainActivity;
    private Context context;
    public ContactAdapter(Context context, List<Contact> list, MainActivity mainActivity) {
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mainActivity = mainActivity;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public void removeItem(int i) {
        list.remove(i);
    }
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        //Log.e("Main",String.valueOf(i));
        View view = convertView;
        if(view == null){
            view = layoutInflater.inflate(R.layout.item_lay, viewGroup,false);
        }
        TextView textView = (TextView) view.findViewById(R.id.textView);
        Button btn = view.findViewById(R.id.remove);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View parent_row = (View) view.getParent();
                ListView lv = (ListView) parent_row.getParent();
                final int position = lv.getPositionForView(parent_row);
                RemoveContact a= new RemoveContact(list_of_contacts.get(position));
                a.run();
                Log.e("Main",list_of_contacts.get(position).getName());
                //Toast.makeText(context,String.valueOf(position_),Toast.LENGTH_LONG);
                database.delete(DBHelper.TABLE_CONTACTS,DBHelper.KEY_NUMBER + "= "+list_of_contacts.get(position).getNumber(),null);
                removeItem(position);
                adapter.notifyDataSetChanged();
            }
        });
        Button btn1 = view.findViewById(R.id.change);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View parent_row = (View) view.getParent();
                ListView lv = (ListView) parent_row.getParent();
                final int position = lv.getPositionForView(parent_row);
                Intent intent = new Intent(mainActivity, ChangeActivity.class);
                intent.putExtra("position",position);
                mainActivity.startActivityForResult(intent,2);
                //listView.setAdapter(adapter);
            }
        });

        textView.setText(getContact(i).getName()+"\n"+getContact(i).getNumber());
        return view;
    }

    private Contact getContact(int position){
        return (Contact) getItem(position);
    }
    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
