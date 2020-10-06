package com.example.exeprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

public class CompanyProvider extends ContentProvider {
    SQLiteDatabase myDb;
    public static final String LOG_TAG = com.example.exeprovider.CompanyProvider.class.getSimpleName();


    public CompanyProvider() {
    }

    //Define the uri for contentProvider
    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String AUTHORITY="com.example.companyProvider";
    public static final Uri CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/emp");

    /** URI matcher code for the content URI for the pets table */
    public static final int EMP = 100;

    /** URI matcher code for the content URI for a single pet in the pets table */
    public static final int EMP_ID = 101;

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    static UriMatcher myUri=new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.
        myUri.addURI(AUTHORITY,"emp",EMP);
        myUri.addURI(AUTHORITY,"emp/#",EMP_ID);
    }




    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        final int match=myUri.match(uri);
        switch (match){
            case EMP:
                long row=myDb.insert(Contract.DB_TABLE,null,values);
                if (row == -1) {
                    Log.e(LOG_TAG, "Failed to insert row for " + uri);
                    return null;
                }else if(row>0){
                    uri= ContentUris.withAppendedId(CONTENT_URI,row);
                    getContext().getContentResolver().notifyChange(uri,null);
                }
                return uri;
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }


    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        MyOwnDatabase myhelper=new MyOwnDatabase(getContext());
        myDb=myhelper.getWritableDatabase();
        if(myDb!=null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        Cursor cursor=null;
        int match=myUri.match(uri);
        switch (match){
            case EMP:
                cursor=myDb.query(
                        Contract.DB_TABLE,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case EMP_ID:
                selection =  "_id"+ "+?";
                /** ContentUsries.parseId() method Converts the last path segment to a number.
                 * String.valueOf() method Returns the string representation of the long argument.
                 * **/
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                cursor = myDb.query(Contract.DB_TABLE, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        return cursor;


    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
