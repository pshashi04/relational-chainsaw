package com.androidtutorialshub.loginregister.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androidtutorialshub.loginregister.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserTable {
    private static final String TABLE_NAME = "user";
    private static final String COLUMN_USER_ID = "user_id"; // Primary key
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    private static List<User> mUserList ;

    private static final   String[] ALL_CCOLUMN = {COLUMN_USER_ID, COLUMN_USER_NAME, COLUMN_USER_PASSWORD };

    public static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT NOT NULL UNIQUE,"
            + COLUMN_USER_PASSWORD + " TEXT NOT NULL" + ")";

    public static String  DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    public static void add(User user,SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        //db.close();
    }

    public static List<User> getAll(SQLiteDatabase db) {
        String sortOrder = COLUMN_USER_NAME + " ASC";
        mUserList = new ArrayList<User>();
        Cursor cursor = db.query(TABLE_NAME, ALL_CCOLUMN,  null, null,null,null,sortOrder);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                mUserList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        //db.close();
        return mUserList;
    }

    public static User getUserDetailsForUserName(SQLiteDatabase db, String userName) {
        String whereClause = COLUMN_USER_NAME + " = ?";
        String[] whereArgs = new String[] {userName};
        Cursor cursor = db.query(TABLE_NAME, ALL_CCOLUMN,  whereClause, whereArgs,null,null,null);
        User user = new User();
        if (cursor.moveToFirst()) {
            do {
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
            } while (cursor.moveToNext());
        }
        return user;
    }
}
