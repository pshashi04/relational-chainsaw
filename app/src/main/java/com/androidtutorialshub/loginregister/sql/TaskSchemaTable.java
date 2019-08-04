package com.androidtutorialshub.loginregister.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androidtutorialshub.loginregister.model.TaskSchema;
import com.androidtutorialshub.loginregister.model.WorkflowSchema;

import java.util.ArrayList;
import java.util.List;

public class TaskSchemaTable {
    private static final String TABLE_NAME = "task_schema";
    private static final String COLUMN_TASK_ID = "task_id"; // Primary key
    private static final String COLUMN_TASK_WF_ID = "task_wf_id";
    private static final String COLUMN_TASK_TITLE= "task_title";
    private static final String COLUMN_TASK_DESC = "task_desc";
    private static final String COLUMN_TASK_ACTION = "task_action";
    private static final String COLUMN_TASK_ROLE = "task_role";

    private static final   String[] ALL_CCOLUMN = {COLUMN_TASK_ID, COLUMN_TASK_WF_ID,  COLUMN_TASK_TITLE, COLUMN_TASK_DESC
                                                    , COLUMN_TASK_ACTION, COLUMN_TASK_ROLE};

    public static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TASK_WF_ID + " INTEGER, " + COLUMN_TASK_TITLE+" text, "
            + COLUMN_TASK_DESC +" text, "+ COLUMN_TASK_ACTION+" text, "+ COLUMN_TASK_ROLE+" integer "+")";


    public static void add(TaskSchema schema, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK_ID, schema.getTaskID());
        values.put(COLUMN_TASK_WF_ID, schema.getTaskWFID());
        values.put(COLUMN_TASK_TITLE, schema.getTaskTitle());
        values.put(COLUMN_TASK_DESC, schema.getTaskDesc());
        values.put(COLUMN_TASK_ACTION, schema.getTaskAction());
        values.put(COLUMN_TASK_ROLE, schema.getTaskRole());
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        //db.close();
    }

    public static List<TaskSchema> getAll(SQLiteDatabase db) {
        String sortOrder = COLUMN_TASK_ID + " ASC";
        List<TaskSchema> list = new ArrayList<TaskSchema>();
       Cursor cursor = db.query(TABLE_NAME, ALL_CCOLUMN,  null, null,null,null,sortOrder);
        if (cursor.moveToFirst()) {
            do {
                TaskSchema temp = new TaskSchema();
                temp.setTaskID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TASK_ID))));
                temp.setTaskWFID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TASK_WF_ID))));
                temp.setTaskTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TASK_TITLE)));
                temp.setTaskDesc(cursor.getString(cursor.getColumnIndex(COLUMN_TASK_DESC)));
                temp.setTaskAction(cursor.getString(cursor.getColumnIndex(COLUMN_TASK_ACTION)));
                temp.setTaskRole(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TASK_ROLE))));//TODO link to role name how check
                list.add(temp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        //db.close();
        return list;
    }

    public static long insert(SQLiteDatabase db, TaskSchema taskSchema){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TASK_WF_ID, taskSchema.getTaskWFID());
        cv.put(COLUMN_TASK_TITLE, taskSchema.getTaskTitle());
        cv.put(COLUMN_TASK_DESC, taskSchema.getTaskDesc());
        cv.put(COLUMN_TASK_ACTION, taskSchema.getTaskAction());
        cv.put(COLUMN_TASK_ROLE, taskSchema.getTaskRole());
        return db.insert(TABLE_NAME,null,cv);

    }
}
