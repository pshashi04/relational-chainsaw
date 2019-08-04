package com.androidtutorialshub.loginregister.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androidtutorialshub.loginregister.model.RoleSchema;


import java.util.ArrayList;
import java.util.List;

public class RoleSchemaTable {
    private static final String TABLE_NAME = "role_schema";
    private static final String COLUMN_ROLE_ID = "role_id"; // Primary key
    private static final String COLUMN_ROLE_NAME = "role_name";

    private static final String[] ALL_CCOLUMN = {COLUMN_ROLE_NAME, COLUMN_ROLE_ID };
    private static final String[] ROLE_NAME_COLUMN = {COLUMN_ROLE_NAME};

    public static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ROLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ COLUMN_ROLE_NAME + " TEXT NOT NULL"  + ")";

    public static void add(RoleSchema schema, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ROLE_NAME, schema.getRoleName());
        values.put(COLUMN_ROLE_ID, schema.getRoleID());
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        //db.close();
    }

    public static List<RoleSchema> getAll(SQLiteDatabase db) {
        String sortOrder = COLUMN_ROLE_ID + " ASC";
        List<RoleSchema> userList = new ArrayList<RoleSchema>();
        Cursor cursor = db.query(TABLE_NAME, ALL_CCOLUMN,  null, null,null,null,sortOrder);
        if (cursor.moveToFirst()) {
            do {
                RoleSchema userRole = new RoleSchema();
                userRole.setRoleID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ROLE_ID))));
                userRole.setRoleName(cursor.getString(cursor.getColumnIndex(COLUMN_ROLE_NAME)));
                userList.add(userRole);
            } while (cursor.moveToNext());
        }
        cursor.close();
        //db.close();
        return userList;
    }

    public static List<RoleSchema> getAllRoleNames(SQLiteDatabase db) {
        String sortOrder = COLUMN_ROLE_ID + " ASC";
        List<RoleSchema> userList = new ArrayList<RoleSchema>();
        Cursor cursor = db.query(TABLE_NAME, ROLE_NAME_COLUMN,  null, null,COLUMN_ROLE_NAME,null,sortOrder);
        if (cursor.moveToFirst()) {
            do {
                RoleSchema userRole = new RoleSchema();
                userRole.setRoleID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ROLE_ID))));
                userRole.setRoleName(cursor.getString(cursor.getColumnIndex(COLUMN_ROLE_NAME)));
                userList.add(userRole);
            } while (cursor.moveToNext());
        }
        cursor.close();
        //db.close();
        return userList;
    }
}