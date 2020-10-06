package com.example.exeprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public class Contract {

    public static final String AUTHORITY="com.example.companyProvider";
    public static final Uri CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/emp");

    public static final String DB_NAME="ram";
    public static final String DB_TABLE="profile";
    public static final int DB_VER=1;

    public class Basic implements BaseColumns {
        public static final String email="emp_email";
        public static final String id=BaseColumns._ID;
        public static final String name="emp_name";
        public static final String profile="emp_profile";
    }
}
