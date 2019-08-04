package com.androidtutorialshub.loginregister.model;

public class TaskInstance extends TaskSchema {
    private int taskInstanceID;
    private int taskInstanceTaskID;
    private int taskInstanceWorkflowinstanceID;
    private int taskInstanceActor;
    private String taskInstanceStatus;

    public int getTaskInstanceID() {
        return taskInstanceID;
    }

    public void setTaskInstanceID(int taskInstanceID) {
        this.taskInstanceID = taskInstanceID;
    }

    public int getTaskInstanceTaskID() {
        return taskInstanceTaskID;
    }

    public void setTaskInstanceTaskID(int taskInstanceTaskID) {
        this.taskInstanceTaskID = taskInstanceTaskID;
    }

    public int getTaskInstanceWorkflowinstanceID() {
        return taskInstanceWorkflowinstanceID;
    }

    public void setTaskInstanceWorkflowinstanceID(int taskInstanceWorkflowinstanceID) {
        this.taskInstanceWorkflowinstanceID = taskInstanceWorkflowinstanceID;
    }

    public int getTaskInstanceActor() {
        return taskInstanceActor;
    }

    public void setTaskInstanceActor(int taskInstanceActor) {
        this.taskInstanceActor = taskInstanceActor;
    }

    public String getTaskInstanceStatus() {
        return taskInstanceStatus;
    }

    public void setTaskInstanceStatus(String taskInstanceStatus) {
        this.taskInstanceStatus = taskInstanceStatus;
    }
}
