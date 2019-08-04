package com.androidtutorialshub.loginregister.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.model.RoleSchema;
import com.androidtutorialshub.loginregister.model.UserRole;
import com.androidtutorialshub.loginregister.model.WorkflowRoleSchema;
import com.androidtutorialshub.loginregister.model.WorkflowSchema;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;
import com.androidtutorialshub.loginregister.sql.RoleSchemaTable;
import com.androidtutorialshub.loginregister.sql.UserRoleTable;
import com.androidtutorialshub.loginregister.sql.WorkflowRoleSchemaTable;
import com.androidtutorialshub.loginregister.sql.WorkflowSchemaTable;

import java.util.ArrayList;
import java.util.List;

public class WorkflowActivityScreen extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private static final String ROLE_ADMIN = "administrator";
    private int mUserId;
    private String mUserName;
    private DatabaseHelper mDBHelper;

    private List<UserRole> mRoleIdList;
    private List<RoleSchema> mRoleDetails;
    private List<WorkflowRoleSchema> mWorkflowRoleSchemaList;
    private List<WorkflowSchema> mWorkflowSchemaList;
    private LayoutInflater mInflator;
    private LinearLayout mMainLayout;
    private Spinner mWorkFlowListLayout;
    private Spinner mWorkFlowList;
    private Button mSubmittButton;
    private Button mShowButton;
    private Button  mCreateButton;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workflow_activity_screen);
        mMainLayout = (LinearLayout) findViewById(R.id.main_layout);
        mDBHelper = new DatabaseHelper(this);
        mInflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mUserName = getIntent().getStringExtra("userName");
        mUserId = getIntent().getIntExtra("userId",-1);
        Log.d("Rahul", " workFlow Activity Screen for " + mUserName + " Id: " + mUserId);

        // TODO rest of the code below can be in thead... no efficiency considered here, need to optimize
        mRoleIdList = new ArrayList<>();
        List<UserRole> list = UserRoleTable.getAll(mDBHelper.getDataBase());
        for (UserRole ur : list) {
            if (ur.getUserID() == mUserId) {
                mRoleIdList.add(ur);
            }
        }
        mRoleDetails = new ArrayList<>();
        List<RoleSchema> roleList = RoleSchemaTable.getAll(mDBHelper.getDataBase());
        for (RoleSchema ur : roleList) {
            for (UserRole role : mRoleIdList) {
                if (ur.getRoleID() == role.getRoleID()) {
                    mRoleDetails.add(ur);
                }
            }
        }

        mWorkflowRoleSchemaList = new ArrayList<>();
        List<WorkflowRoleSchema> workFlowRoleList = WorkflowRoleSchemaTable.getAll(mDBHelper.getDataBase());
        for (WorkflowRoleSchema wrs : workFlowRoleList) {
            for (RoleSchema role : mRoleDetails) {
                if (role.getRoleID() == wrs.getRoleID()) {
                    mWorkflowRoleSchemaList.add(wrs);
                }
            }
        }

        mWorkflowSchemaList = new ArrayList<>();
        List<String> workFlowTitleList = new ArrayList<>();
        List<WorkflowSchema> workFlowList = WorkflowSchemaTable.getAll(mDBHelper.getDataBase());
        for (WorkflowSchema wr : workFlowList) {
            for (WorkflowRoleSchema wrs : mWorkflowRoleSchemaList) {
                if (wrs.getWorkflowID() == wr.getWorkflowID()) {
                    mWorkflowSchemaList.add(wr);
                    Log.d("Rahul"," workflow added as role: "+ wrs.getRoleID()+"  wf: "+wr);
                    workFlowTitleList.add(wr.getTitle());
                }
            }
        }

        for (RoleSchema ur : mRoleDetails) {
           if(ur.getRoleName().equals(ROLE_ADMIN)) {
               // TODO add Create workflow Layout
               View createLayout =  mInflator.inflate(R.layout.workflow_create_layout,null);
               mMainLayout.addView(createLayout);
               mCreateButton = (Button) findViewById(R.id.create_button);
               mCreateButton.setOnClickListener(this);

           }
        }
        Log.d("Rahul"," mWorkflowSchemaList size: "+ mWorkflowSchemaList.size());
        if(mWorkflowSchemaList.size() != 0) {
            View startLayout =  mInflator.inflate(R.layout.workflow_start_layout,null);
            mMainLayout.addView(startLayout);
            mWorkFlowListLayout = (Spinner) findViewById(R.id.spinner_list);
            mSubmittButton = (Button) findViewById(R.id.submit_button);
            mWorkFlowListLayout.setOnItemSelectedListener(this);
            mWorkFlowListLayout.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,workFlowTitleList));
        }

        //TODO layout to show pending workflow right now dummy
        Log.d("Rahul"," mWorkflowSchemaList size: "+ mWorkflowSchemaList.size());
        //if(mWorkflowSchemaList.size() != 0) {
            View startLayout =  mInflator.inflate(R.layout.show_workflow_layout,null);
            mMainLayout.addView(startLayout);
            mWorkFlowList = (Spinner) findViewById(R.id.show_workflow_spinner_list);
            mShowButton = (Button) findViewById(R.id.show_button);
            mWorkFlowList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,new String[]{"Select item"}));
        //}

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // TODO store the selected item and on submit start workflow start screen

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.create_button:
                //TODO workflow creation screen
                Intent workflowCreateIntent =  new Intent();
                workflowCreateIntent.setComponent(new ComponentName(this,WorkflowCreationScreen.class));
                startActivity(workflowCreateIntent);
                break;
            case R.id.submit_button:
                //TODO workflow start screen
                break;
        }

    }
}
