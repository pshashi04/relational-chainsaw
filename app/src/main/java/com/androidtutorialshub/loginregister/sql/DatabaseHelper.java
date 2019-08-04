package com.androidtutorialshub.loginregister.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.androidtutorialshub.loginregister.model.RoleSchema;
import com.androidtutorialshub.loginregister.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rahul Kumar on 9/07/2019.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Workflow_Model.db";
    private SQLiteDatabase mDataBase;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Rahul", " DatabaseHelper ");
        mDataBase = this.getWritableDatabase();
    }

    public SQLiteDatabase getDataBase() {
        return mDataBase;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Rahul", " creating tables....");
        db.execSQL(UserTable.CREATE_TABLE);
        db.execSQL(RoleSchemaTable.CREATE_TABLE);
        db.execSQL(TaskSchemaTable.CREATE_TABLE);
        db.execSQL(UserRoleTable.CREATE_TABLE);
        db.execSQL(WorkflowRoleSchemaTable.CREATE_TABLE);
        db.execSQL(WorkflowSchemaTable.CREATE_TABLE);
        //db.execSQL(WorkflowInstanceTable.CREATE_TABLE); TODO: @srinivas/@shankar -> Enable them when workflow execution part starts
        //db.execSQL(TaksInstanceTable.CREATE_TABLE);
        addDummyEntries(db);
    }


    private void addDummyEntries(SQLiteDatabase db) {
        Log.d("Rahul", " addDummyEntries start...");
        db.execSQL("insert into User (User_Name, User_Password) values (\"admin\",\"admin\");");
        db.execSQL("insert into Role_Schema (Role_Name) values (\"administrator\");");
        db.execSQL("insert into Role_Schema (Role_Name) values (\"Manager\");");
        db.execSQL("insert into Role_Schema (Role_Name) values (\"Employee\");");
        db.execSQL("insert into User_Role (User_ID, Role_ID) values (1,1);");
        db.execSQL("insert into Workflow_Role_Schema (WF_ID,Role_ID) values (1,1);");
        db.execSQL("insert into Workflow_Schema (WF_Title,WF_Desc) values (\"Leave\",\"Leave request and approval workflow\");");
        db.execSQL("insert into Task_Schema (Task_WF_ID,Task_Title,Task_Desc,Task_Action,Task_Role) values(1,\"Leave Initiate\",\"Initiate leave workflow task\",\"Initiate,Cancel\",1);");
        db.execSQL("insert into Task_Schema (Task_WF_ID,Task_Title,Task_Desc,Task_Action,Task_Role) values(1,\"Leave Approval\",\"Approve leave workflow task\",\"Approve,Reject,Delegate\",1);");
        Log.d("Rahul", " addDummyEntries end...");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(UserTable.DROP_TABLE); // TODO for each table
        // Create tables again
        onCreate(db);
        Log.d("Rahul", " onUpgrade oldVersion: " + oldVersion + "  newver: " + newVersion);

    }


}
