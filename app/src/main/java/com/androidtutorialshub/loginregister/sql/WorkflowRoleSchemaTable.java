package com.androidtutorialshub.loginregister.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androidtutorialshub.loginregister.model.UserRole;
import com.androidtutorialshub.loginregister.model.WorkflowRoleSchema;

import java.util.ArrayList;
import java.util.List;

public class WorkflowRoleSchemaTable {
    private static final String TABLE_NAME = "workflow_role_schema";
    private static final String COLUMN_WF_ID = "wf_id"; // Primary key
    private static final String COLUMN_ROLE_ID = "role_id";

    private static final   String[] ALL_CCOLUMN = {COLUMN_WF_ID, COLUMN_ROLE_ID };

    public static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_WF_ID + " INTEGER, " + COLUMN_ROLE_ID + " INTEGER" + ")";


    public static void add(WorkflowRoleSchema wrSchema, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_WF_ID, wrSchema.getWorkflowID());
        values.put(COLUMN_ROLE_ID, wrSchema.getRoleID());
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        //db.close();
    }

    public static List<WorkflowRoleSchema> getAll(SQLiteDatabase db) {
        String sortOrder = COLUMN_WF_ID + " ASC";
        List<WorkflowRoleSchema> userList = new ArrayList<WorkflowRoleSchema>();
        Cursor cursor = db.query(TABLE_NAME, ALL_CCOLUMN,  null, null,null,null,sortOrder);
        if (cursor.moveToFirst()) {
            do {
                WorkflowRoleSchema temp = new WorkflowRoleSchema();
                temp.setWorkflowID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_WF_ID))));
                temp.setRoleID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ROLE_ID))));
                userList.add(temp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        //db.close();
        return userList;
    }
}
