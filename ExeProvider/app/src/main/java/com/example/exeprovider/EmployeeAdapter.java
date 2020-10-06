package com.example.exeprovider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EmployeeAdapter extends ArrayAdapter<Employee> {

    ArrayList<Employee> empList;

    public EmployeeAdapter(Context context, int textViewResourceId, ArrayList<Employee> employees) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, textViewResourceId, employees);
        empList=employees;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        LayoutInflater inflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v=inflater.inflate(R.layout.list_item,null);
        TextView name=(TextView) v.findViewById(R.id.name_text);
        name.setText(empList.get(position).getEmp_name());

        TextView email=(TextView) v.findViewById(R.id.email_text);
        email.setText(empList.get(position).getEmp_email());

        TextView profile=(TextView) v.findViewById(R.id.profile_text);
        profile.setText(empList.get(position).getEmp_profile());

        return v;
    }
}
