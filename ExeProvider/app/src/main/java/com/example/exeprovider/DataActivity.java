package com.example.exeprovider;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {
    ListView emp_list;
    MainActivity activity=new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        emp_list=(ListView)findViewById(R.id.list);

        /**
         * query() argument-----	SELECT keyword/parameter	-------Notes
         * Uri-------	FROM table_name-----	Uri maps to the table in the provider named table_name.
         * projection------	col,col,col,...	--------projection is an array of columns that should be included for each row retrieved.
         * selection-----	WHERE col = value	------selection specifies the criteria for selecting rows.
         * selectionArgs-------	(No exact equivalent. Selection arguments replace ? placeholders in the selection clause.)
         * sortOrder-----	ORDER BY col,col,...	------sortOrder specifies the order in which rows appear in the returned Cursor.
         **/
        Cursor mCursor=getContentResolver().query(
                Contract.CONTENT_URI,
                null,
                null,
                null,
                "_id"
        );

        // Some providers return null if an error occurs, others throw an exception
        if (null == mCursor) {
            /*
             * Insert code here to handle the error. Be sure not to use the cursor! You may want to
             * call android.util.Log.e() to log this error.
             *
             */
            // If the Cursor is empty, the provider found no matches
        } else if (mCursor.getCount() < 1) {

            /*
             * Insert code here to notify the user that the search was unsuccessful. This isn't necessarily
             * an error. You may want to offer the user the option to insert a new row, or re-type the
             * search term.
             */

        } else {
            /**
             * when we call the ContentResolver.query() method it internaly call the  ContentProvider.query() method and pass
             * all the argument from ContentResolver to ContentProvider.
             */
            int idColumnIndex = mCursor.getColumnIndex(Contract.Basic.id);
            int emailColumnIndex = mCursor.getColumnIndex(Contract.Basic.email);
            int nameColumnIndex = mCursor.getColumnIndex(Contract.Basic.name);
            int profileColumnIndex = mCursor.getColumnIndex(Contract.Basic.profile);

            final ArrayList<Employee> emp = new ArrayList<>();

            //StringBuilder stringBuilder=new StringBuilder();

            while (mCursor.moveToNext()) {
                int id = mCursor.getInt(idColumnIndex);
                String email = mCursor.getString(emailColumnIndex);
                String name = mCursor.getString(nameColumnIndex);
                String profile = mCursor.getString(profileColumnIndex);

                //System.out.println(id+" "+email+" "+name+" "+profile);
                //emp.add(""+id+" "+email+" "+name+" "+profile);
                emp.add(new Employee(email, name, profile));
                //stringBuilder.append(id+"  "+email+" "+name+" "+profile+" "+"\n");
            }
            EmployeeAdapter adapter=new EmployeeAdapter(this,R.layout.emp_list,emp);
            if(adapter!=null){
                ListView listView=(ListView)findViewById(R.id.listData);
                listView.setAdapter(adapter);
            }


        }

    }



}