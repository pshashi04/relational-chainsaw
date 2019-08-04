package com.androidtutorialshub.loginregister.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androidtutorialshub.loginregister.model.User;
import com.androidtutorialshub.loginregister.model.UserRole;

import java.util.ArrayList;
import java.util.List;

public class UserRoleTable {
    private static final String TABLE_NAME = "user_role";
    private static final String COLUMN_USER_ID = "user_id"; // Primary key
    private static final String COLUMN_ROLE_ID = "role_id";

    private static final   String[] ALL_CCOLUMN = {COLUMN_USER_ID, COLUMN_ROLE_ID };

    private static List<UserRole> mUserRoleList;

    public static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_USER_ID + " INTEGER, " + COLUMN_ROLE_ID + " INTEGER" + ")";


    public static void add(UserRole userRole, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, userRole.getUserID());
        values.put(COLUMN_ROLE_ID, userRole.getRoleID());
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        //db.close();
    }

    public static List<UserRole> getAll(SQLiteDatabase db) {
        String sortOrder = COLUMN_USER_ID + " ASC";
        mUserRoleList = new ArrayList<UserRole>();
        Cursor cursor = db.query(TABLE_NAME, ALL_CCOLUMN,  null, null,null,null,sortOrder);
        if (cursor.moveToFirst()) {
            do {
                UserRole userRole = new UserRole();
                userRole.setUserID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                userRole.setRoleID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ROLE_ID))));
                mUserRoleList.add(userRole);
            } while (cursor.moveToNext());
        }
        cursor.close();
        //db.close();
        return mUserRoleList;
    }
}

