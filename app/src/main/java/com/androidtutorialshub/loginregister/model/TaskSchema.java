package com.androidtutorialshub.loginregister.model;

public class TaskSchema {
    private int taskID;
    private int taskWFID;
    private String taskTitle;
    private String taskDesc;
    private String taskAction;
    private int taskRole;

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getTaskWFID() {
        return taskWFID;
    }

    public void setTaskWFID(int taskWFID) {
        this.taskWFID = taskWFID;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskAction() {
        return taskAction;
    }

    public void setTaskAction(String taskAction) {
        this.taskAction = taskAction;
    }

    public int getTaskRole() {
        return taskRole;
    }

    public void setTaskRole(int taskRole) {
        this.taskRole = taskRole;
    }

    public boolean validate() {
        if(taskAction.equals("") || taskDesc.equals("") || taskTitle.equals("")){
            return false;
        }
        return true;
    }
}
