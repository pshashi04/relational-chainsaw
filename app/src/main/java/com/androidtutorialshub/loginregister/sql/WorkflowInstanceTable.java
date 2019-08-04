package com.androidtutorialshub.loginregister.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androidtutorialshub.loginregister.model.WorkflowInstance;
import com.androidtutorialshub.loginregister.model.WorkflowSchema;

import java.util.ArrayList;
import java.util.List;

public class WorkflowInstanceTable {
    private static final String TABLE_NAME = "workflow_instance";
    private static final String COLUMN_WORKFLOW_INSTANCE_ID = "workflow_i_id"; // Primary key
    private static final String COLUMN_WORKFLOW_INSTANCE_STATUS = "workflow_i_status";
    private static final String COLUMN_WORKFLOW_INSTANCE_CREATOR= "workflow_i_creator";
    private static final String COLUMN_WORKFLOW_INSTANCE_WORKFLOW_ID= "workflow_i_workflow_id";

    private static final   String[] ALL_CCOLUMN = {COLUMN_WORKFLOW_INSTANCE_ID, COLUMN_WORKFLOW_INSTANCE_WORKFLOW_ID,  COLUMN_WORKFLOW_INSTANCE_STATUS, COLUMN_WORKFLOW_INSTANCE_CREATOR};

    public static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_WORKFLOW_INSTANCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_WORKFLOW_INSTANCE_STATUS + " text, "
            + COLUMN_WORKFLOW_INSTANCE_CREATOR +" INTEGER, " + COLUMN_WORKFLOW_INSTANCE_WORKFLOW_ID +" INTEGER "+")";

    public static void add(WorkflowInstance instance, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_WORKFLOW_INSTANCE_ID, instance.getWorkflowInstanceID());
        values.put(COLUMN_WORKFLOW_INSTANCE_WORKFLOW_ID, instance.getWorkflowInstanceWorkflowID());
        values.put(COLUMN_WORKFLOW_INSTANCE_STATUS, instance.getWorkflowInstanceStatus());
        values.put(COLUMN_WORKFLOW_INSTANCE_CREATOR, instance.getWorkflowInstanceCreator());
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        //db.close();
    }

    public static List<WorkflowInstance> getAll(SQLiteDatabase db) {
        String sortOrder = COLUMN_WORKFLOW_INSTANCE_ID + " ASC";
        List<WorkflowInstance> userList = new ArrayList<WorkflowInstance>();
        Cursor cursor = db.query(TABLE_NAME, ALL_CCOLUMN,  null, null,null,null,sortOrder);
        //TODO take care of inheritance...
       /* if (cursor.moveToFirst()) {
            do {
                WorkflowInstance temp = new WorkflowInstance();
                temp.setWorkflowInstanceID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_WORKFLOW_INSTANCE_ID))));
                temp.setWorkflowInstanceWorkflowID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_WORKFLOW_INSTANCE_WORKFLOW_ID))));
                temp.setWorkflowInstanceStatus(cursor.getString(cursor.getColumnIndex(COLUMN_WORKFLOW_INSTANCE_STATUS)));
                temp.setWorkflowInstanceCreator(cursor.getString(cursor.getColumnIndex(COLUMN_WORKFLOW_INSTANCE_CREATOR)));
                userList.add(temp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();*/
        return userList;
    }
}
