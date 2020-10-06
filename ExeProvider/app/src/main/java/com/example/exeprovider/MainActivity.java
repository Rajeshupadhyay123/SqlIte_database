package com.example.exeprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.exeprovider.Contract.Basic;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    EditText edit_id,edit_name,edit_profile;


    ContentValues values=new ContentValues();
    DataActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity=new DataActivity();

        edit_id=(EditText)findViewById(R.id.edit_id);
        edit_name=(EditText)findViewById(R.id.edit_name);
        edit_profile=(EditText)findViewById(R.id.edit_profile);
    }

    public void Save(View view) {
        values.put("emp_email",edit_id.getText().toString());
        values.put("emp_name",edit_name.getText().toString());
        values.put("emp_profile",edit_profile.getText().toString());

        Uri uri=getContentResolver().insert(Contract.CONTENT_URI,values);
        Toast.makeText(this,uri.toString(),Toast.LENGTH_LONG).show();

    }


    public void details(View view) {
        Intent i=new Intent(this,DataActivity.class);
        startActivity(i);
    }

}