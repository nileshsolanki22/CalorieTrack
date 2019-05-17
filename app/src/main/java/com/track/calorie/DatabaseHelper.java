package com.track.calorie;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(MainActivity context) {
        super(context, DatabaseOptions.DB_NAME, null, DatabaseOptions.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create table
        sqLiteDatabase.execSQL(DatabaseOptions.CREATE_USERS_TABLE_);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DatabaseOptions.USERS_TABLE);
        onCreate(sqLiteDatabase);
    }


    public User queryUser(String email, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        User user = null;

        Cursor cursor = db.query(DatabaseOptions.USERS_TABLE,new String[]{ DatabaseOptions.ID,
                DatabaseOptions.EMAIL,DatabaseOptions.PASSWORD},DatabaseOptions.EMAIL+ "=? and " + DatabaseOptions.PASSWORD +
                new String [] {email, password}, null, null , null, "1");

        if (cursor != null)
            cursor.moveToFirst();
        if (cursor !=null && cursor.getCount() > 0 ) {
            user = new User(cursor.getString(1),cursor.getString(2));
        }



        return user;
    }

    public User addUser(User user){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseOptions.EMAIL,user.getEmail());
        values.put(DatabaseOptions.PASSWORD,user.getPassword());

        //Inserting Row
        db.insert(DatabaseOptions.USERS_TABLE,null,values);
        db.close();

        return user;
    }

}
