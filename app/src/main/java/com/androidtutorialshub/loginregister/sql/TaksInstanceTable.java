package com.androidtutorialshub.loginregister.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androidtutorialshub.loginregister.model.TaskInstance;
import com.androidtutorialshub.loginregister.model.WorkflowInstance;

import java.util.ArrayList;
import java.util.List;

public class TaksInstanceTable {
    private static final String TABLE_NAME = "task_instance";
    private static final String COLUMN_TASK_INSTANCE_ID = "task_i_id"; // Primary key
    private static final String COLUMN_TASK_INSTANCE_WORKFLOW_INSTANCE_ID= "task_i_workflow_id";
    private static final String COLUMN_TASK_INSTANCE_STATUS= "task_i_status";
    private static final String COLUMN_TASK_INSTANCE_ACTOR= "task_i_actor";
    private static final String COLUMN_TASK_INSTANCE_TASK_INSTANCE_ID= "task_i_task_i_id";

    private static final   String[] ALL_CCOLUMN = {COLUMN_TASK_INSTANCE_ID, COLUMN_TASK_INSTANCE_WORKFLOW_INSTANCE_ID,  COLUMN_TASK_INSTANCE_STATUS
            , COLUMN_TASK_INSTANCE_ACTOR, COLUMN_TASK_INSTANCE_TASK_INSTANCE_ID};

    public static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_TASK_INSTANCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TASK_INSTANCE_WORKFLOW_INSTANCE_ID + " INTEGER, "
            + COLUMN_TASK_INSTANCE_STATUS + " text, " + COLUMN_TASK_INSTANCE_ACTOR + " INTEGER, " + COLUMN_TASK_INSTANCE_TASK_INSTANCE_ID + "INTEGER, " + ")";

    public static void add(TaskInstance instance, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK_INSTANCE_ID, instance.getTaskInstanceID());
        values.put(COLUMN_TASK_INSTANCE_WORKFLOW_INSTANCE_ID, instance.getTaskInstanceWorkflowinstanceID());
        values.put(COLUMN_TASK_INSTANCE_STATUS, instance.getTaskInstanceStatus());
        values.put(COLUMN_TASK_INSTANCE_ACTOR, instance.getTaskInstanceActor());
        values.put(COLUMN_TASK_INSTANCE_TASK_INSTANCE_ID, instance.getTaskInstanceTaskID());
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        //db.close();
    }

    public static List<WorkflowInstance> getAll(SQLiteDatabase db) {
        String sortOrder = COLUMN_TASK_INSTANCE_ID + " ASC";
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
