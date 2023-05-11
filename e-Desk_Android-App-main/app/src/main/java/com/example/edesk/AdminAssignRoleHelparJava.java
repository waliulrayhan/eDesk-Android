package com.example.edesk;

public class AdminAssignRoleHelparJava {

    String SelectedRole, Faculty, Department, SelectPosition, UserID;

    public AdminAssignRoleHelparJava() {

    }

    public AdminAssignRoleHelparJava(String selectedRole, String faculty, String department, String selectPosition, String userID) {
        this.SelectedRole = selectedRole;
        this.Faculty = faculty;
        this.Department = department;
        this.SelectPosition = selectPosition;
        this.UserID = userID;
    }

    public String getSelectedRole() {
        return SelectedRole;
    }

    public void setSelectedRole(String selectedRole) {
        SelectedRole = selectedRole;
    }

    public String getFaculty() {
        return Faculty;
    }

    public void setFaculty(String faculty) {
        Faculty = faculty;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getSelectPosition() {
        return SelectPosition;
    }

    public void setSelectPosition(String selectPosition) {
        SelectPosition = selectPosition;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }
}
