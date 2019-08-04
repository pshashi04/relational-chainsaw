package com.androidtutorialshub.loginregister.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.model.User;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;
import com.androidtutorialshub.loginregister.sql.UserTable;

import java.util.List;

public class LoginActivity extends Activity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private EditText mUsername;
    private EditText mPassword;
    private Button mLoginButton;
    private TextView mSignUpButton;
    private DatabaseHelper mDBHelper;
    private List<User> mUserList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUsername = (EditText) findViewById(R.id.user_name);
        mPassword = (EditText) findViewById(R.id.input_password);
        mLoginButton = (Button) findViewById(R.id.btn_login);
        mSignUpButton = (TextView) findViewById(R.id.link_signup);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        mSignUpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "coming soon...", Toast.LENGTH_SHORT).show();
            }
        });
        mDBHelper = new DatabaseHelper(this);

        populateUserInfo();
    }

    private void populateUserInfo() {
        Log.d("Rahul", "populateUserInfo");

        mUserList = UserTable.getAll(mDBHelper.getDataBase());
        for (User u : mUserList) {
            Log.d("Rahul", " " + u);
        }
    }


    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        mLoginButton.setEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String username = mUsername.getText().toString();
        final String password = mPassword.getText().toString();

        // TODO: Implement your own authentication logic here.
        final User selectedUser = UserTable.getUserDetailsForUserName(mDBHelper.getDataBase(), username);
        Log.d("dm-shashi","selected user = " + selectedUser);
        //final User finalSelectedUser;
//        if(selectedUser != null && selectedUser.getPassword().equals(password)) {
//            finalSelectedUser = selectedUser;
//            Log.d("dm-shashi","selected User Success " + selectedUser.getName());
//        }else{
//            finalSelectedUser = selectedUser;
//            Log.d("dm-shashi","selected User Fail " + selectedUser.getName());
//        }

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if (selectedUser != null && selectedUser.getPassword() != null) {
                            if(selectedUser.getPassword().equals(password)) {
                                onLoginSuccess(selectedUser);
                            }else{
                                onLoginFailed();
                            }
                        } else {
                            onLoginFailed();
                        }
                        progressDialog.dismiss();
                    }
                }, 1000);
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess(User user) {
        Toast.makeText(getBaseContext(), "Login Success", Toast.LENGTH_SHORT).show();
        mLoginButton.setEnabled(true);
        Intent workflowActivityIntent = new Intent();
        Log.d("Rahul", " selected ....startin...: " + user);
        workflowActivityIntent.putExtra("userId", user.getId());
        workflowActivityIntent.putExtra("userName", user.getName());
        workflowActivityIntent.setComponent(new ComponentName(this, WorkflowActivityScreen.class));
        startActivity(workflowActivityIntent);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_SHORT).show();
        mLoginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        if (username.isEmpty()) {
            mUsername.setError("enter a valid username address");
            valid = false;
        } else {
            mUsername.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            mPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            mPassword.setError(null);
        }

        return valid;
    }
}