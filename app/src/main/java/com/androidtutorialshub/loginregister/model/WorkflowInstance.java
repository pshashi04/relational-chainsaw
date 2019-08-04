package com.androidtutorialshub.loginregister.model;

public class WorkflowInstance extends WorkflowSchema {

    private int workflowInstanceID;
    private String workflowInstanceStatus;
    private String workflowInstanceCreator;
    private int workflowInstanceWorkflowID;

    public int getWorkflowInstanceID() {
        return workflowInstanceID;
    }

    public void setWorkflowInstanceID(int workflowInstanceID) {
        this.workflowInstanceID = workflowInstanceID;
    }

    public String getWorkflowInstanceStatus() {
        return workflowInstanceStatus;
    }

    public void setWorkflowInstanceStatus(String workflowInstanceStatus) {
        this.workflowInstanceStatus = workflowInstanceStatus;
    }

    public String getWorkflowInstanceCreator() {
        return workflowInstanceCreator;
    }

    public void setWorkflowInstanceCreator(String workflowInstanceCreator) {
        this.workflowInstanceCreator = workflowInstanceCreator;
    }

    public int getWorkflowInstanceWorkflowID() {
        return workflowInstanceWorkflowID;
    }

    public void setWorkflowInstanceWorkflowID(int workflowInstanceWorkflowID) {
        this.workflowInstanceWorkflowID = workflowInstanceWorkflowID;
    }
}
