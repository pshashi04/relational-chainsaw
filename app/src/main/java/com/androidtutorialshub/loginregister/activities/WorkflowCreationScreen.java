package com.androidtutorialshub.loginregister.activities;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.model.RoleSchema;
import com.androidtutorialshub.loginregister.model.TaskSchema;
import com.androidtutorialshub.loginregister.model.WorkflowSchema;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;
import com.androidtutorialshub.loginregister.sql.RoleSchemaTable;
import com.androidtutorialshub.loginregister.sql.TaskSchemaTable;
import com.androidtutorialshub.loginregister.sql.WorkflowSchemaTable;

import java.util.ArrayList;
import java.util.List;

public class WorkflowCreationScreen extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText mTitleView;
    private EditText mDescriptionView;
    private Button mAddTaskButton;
    private LinearLayout mTaskPannel;
    private LayoutInflater mInflator;
    private List<View> mTaskListView;
    private Button mSubmitButton;
    private DatabaseHelper mDBHelper;
    private List<RoleSchema> mRoleList;
    private String[] mRoleNameArray;
    private ArrayAdapter mRolesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workflow_create_screen);
        mTitleView = (EditText) findViewById(R.id.title_textview);
        mDescriptionView = (EditText) findViewById(R.id.desc_edit_text);
        mTaskPannel = (LinearLayout) findViewById(R.id.task_list_pannel);
        mAddTaskButton = (Button) findViewById(R.id.add_task_btn);
        mSubmitButton = (Button) findViewById(R.id.submit_btn);
        mSubmitButton.setOnClickListener(this);
        mAddTaskButton.setOnClickListener(this);
        mTaskListView = new ArrayList<>();
        mInflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mDBHelper = new DatabaseHelper(this);
        mRoleList = populateAllRoles(mDBHelper.getDataBase());
        mRolesAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, mRoleNameArray);
        mRolesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_task_btn:
                View taskLayout = mInflator.inflate(R.layout.task_schema_layout, null);
                mTaskPannel.addView(taskLayout);
                mTaskListView.add(taskLayout);

                // @p.shashi -> Populating the spinner drop down from role schema db
                Spinner roleListSpinner = taskLayout.findViewById(R.id.task_role_spinner);
                roleListSpinner.setAdapter(mRolesAdapter);
                roleListSpinner.setOnItemSelectedListener(this);

                break;
            case R.id.submit_btn:
                // @p.shashi -> getting the wf information
                String newWfTitle = mTitleView.getText().toString();
                String newWfDesc = mDescriptionView.getText().toString();
                Log.d("dm-shashi", "WF Title = " + newWfTitle + " WF Desc = " + newWfDesc);
                WorkflowSchema workflowSchema = new WorkflowSchema();
                workflowSchema.setTitle(newWfTitle);
                workflowSchema.setDescription(newWfDesc);

                TaskSchema[] taskSchema = new TaskSchema[mTaskListView.size()];

                // @p.shashi -> reading task information from list
                for (int i = 0; i < mTaskListView.size(); i++) {
                    View taskLayoutChild = mTaskListView.get(i);
                    EditText taskTitleText = taskLayoutChild.findViewById(R.id.task_title_edit_text);
                    EditText taskDescText = taskLayoutChild.findViewById(R.id.task_des_edit_text);
                    EditText taskActionsText = taskLayoutChild.findViewById(R.id.task_action_edit_text);
                    roleListSpinner = taskLayoutChild.findViewById(R.id.task_role_spinner);

                    String taskTitle = taskTitleText.getText().toString();
                    String taskDesc = taskDescText.getText().toString();
                    String taskActions = taskActionsText.getText().toString();
                    Log.d("dm-shashi", "Task Title = " + taskTitle + " Task Desc = " + taskDesc);
                    taskSchema[i] = new TaskSchema();
                    taskSchema[i].setTaskTitle(taskTitle);
                    taskSchema[i].setTaskDesc(taskDesc);
                    taskSchema[i].setTaskAction(taskActions);
                    taskSchema[i].setTaskRole(mRoleList.get(roleListSpinner.getSelectedItemPosition()).getRoleID());
                }

                // @p.shashi-> validate all input data before inserting into DB
                if (!validateInputData(workflowSchema, taskSchema)) {
                    long wf_id = WorkflowSchemaTable.insert(mDBHelper.getDataBase(), workflowSchema);
                    for (int i = 0; i < taskSchema.length; i++) {
                        taskSchema[i].setTaskWFID((int) wf_id);
                        TaskSchemaTable.insert(mDBHelper.getDataBase(), taskSchema[i]);
                    }
                } else {
                    Toast.makeText(WorkflowCreationScreen.this, "Enter Complete Workflow and Task Details", Toast.LENGTH_SHORT).show();
                }

                // @p.shashi -> Validating the data after entering into DB
                List wf = WorkflowSchemaTable.getAll(mDBHelper.getDataBase());
                List task = TaskSchemaTable.getAll(mDBHelper.getDataBase());
                Log.d("dm-shashi", "wf" + wf + task);
                finish();
                break;
        }

    }

    private boolean validateInputData(WorkflowSchema workflowSchema, TaskSchema[] taskSchema) {
        boolean error = false;
        if (!workflowSchema.validate()) {
            error = true;
        }
        for (int i = 0; i < taskSchema.length; i++) {
            if (!taskSchema[i].validate()) {
                error = true;
            }
        }
        return error;
    }

    private List<RoleSchema> populateAllRoles(SQLiteDatabase db) {
        mRoleList = RoleSchemaTable.getAll(db);
        mRoleNameArray = new String[mRoleList.size()];
        for (int i = 0; i < mRoleList.size(); i++) {
            mRoleNameArray[i] = mRoleList.get(i).getRoleName();
        }
        return mRoleList;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("dm-shashi","mRoleList.get(position).getRoleName() + \" \" + mRoleList.get(position).getRoleID()");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
