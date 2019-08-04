package com.androidtutorialshub.loginregister.model;

public class WorkflowSchema {
    private int workflowID;
    private String title;
    private String description;

    public int getWorkflowID() {
        return workflowID;
    }

    public void setWorkflowID(int workflowID) {
        this.workflowID = workflowID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Title: "+title+"  Desc: "+description+" ID: "+workflowID;
    }

    public boolean validate() {
        if(title.equals("") || description.equals("")){
            return false;
        }
        return true;
    }
}
