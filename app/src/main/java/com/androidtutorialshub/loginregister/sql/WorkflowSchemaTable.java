package com.androidtutorialshub.loginregister.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androidtutorialshub.loginregister.model.WorkflowRoleSchema;
import com.androidtutorialshub.loginregister.model.WorkflowSchema;

import java.util.ArrayList;
import java.util.List;

public class WorkflowSchemaTable {
    private static final String TABLE_NAME = "workflow_schema";
    private static final String COLUMN_WF_ID = "wf_id"; // Primary key
    private static final String COLUMN_WF_TITLE = "wf_title";
    private static final String COLUMN_WF_DESCRIPTION = "wf_desc";

    private static final   String[] ALL_CCOLUMN = {COLUMN_WF_ID, COLUMN_WF_TITLE,  COLUMN_WF_DESCRIPTION};

    public static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_WF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_WF_TITLE + " text, " + COLUMN_WF_DESCRIPTION +" text "+")";


    public static void add(WorkflowSchema wSchema, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_WF_ID, wSchema.getWorkflowID());
        values.put(COLUMN_WF_TITLE, wSchema.getTitle());
        values.put(COLUMN_WF_DESCRIPTION, wSchema.getDescription());
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        //db.close();
    }

    public static List<WorkflowSchema> getAll(SQLiteDatabase db) {
        String sortOrder = COLUMN_WF_ID + " ASC";
        List<WorkflowSchema> userList = new ArrayList<WorkflowSchema>();
        Cursor cursor = db.query(TABLE_NAME, ALL_CCOLUMN,  null, null,null,null,sortOrder);
        if (cursor.moveToFirst()) {
            do {
                WorkflowSchema temp = new WorkflowSchema();
                temp.setWorkflowID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_WF_ID))));
                temp.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_WF_TITLE)));
                temp.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_WF_DESCRIPTION)));
                userList.add(temp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        //db.close();
        return userList;
    }

    public static long insert(SQLiteDatabase db, WorkflowSchema workflowSchema){

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_WF_TITLE, workflowSchema.getTitle());
        cv.put(COLUMN_WF_DESCRIPTION, workflowSchema.getDescription());
        return db.insert(TABLE_NAME,null,cv);

    }
}
