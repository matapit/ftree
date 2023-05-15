package com.example.famille.app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.famille.modelle.User;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 2;

    public DatabaseManager( Context context ) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String strSql = "create table user ("
                + "    id integer primary key autoincrement,"
                + "    name text not null,"
                + "    password text not null"
                + ")";
        db.execSQL( strSql );
        Log.i( "DATABASE", "onCreate invoked" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //String strSql = "alter table T_Scores add column ...";
        //String strSql = "drop table user";
        //db.execSQL( strSql );
        //this.onCreate( db );
        Log.d( "APP", "onUpgrade invoked" );
    }
    public void drop() {
        //String strSql = "alter table T_Scores add column ...";
        String strSql = "drop table user";
        this.getWritableDatabase().execSQL( strSql );
        Log.d( "APP", "onUpgrade invoked" );
        strSql = "create table user ("
                + "    id integer primary key autoincrement,"
                + "    name text not null,"
                + "    password text not null"
                + ")";
        this.getWritableDatabase().execSQL( strSql );
        Log.i( "DATABASE", "onCreate invoked" );
    }

    public void insertUser( String name, String password ) {
        name = name.replace( "'", "''" );
        password = password.replace( "'", "''" );
        String strSql = "insert into user (name, password) values ('"
                + name + "', '" + password + "')";
        Log.d( "APP", strSql );
        this.getWritableDatabase().execSQL( strSql );
        Log.i( "DATABASE", "insertScore invoked" );
    }

    public User getUser() {
        User user = null;
        // 1ère technique : SQL
        String strSql = "select * from user ";
        Cursor cursor = this.getReadableDatabase().rawQuery( strSql, null );

        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            user = new User( cursor.getInt( 0 ), cursor.getString( 1 ),
                    cursor.getString( 2 ) );

            cursor.moveToNext();
        }
        cursor.close();
        Log.d( "APP", "onUpgrade invoked" );
        return user;
    }
}
