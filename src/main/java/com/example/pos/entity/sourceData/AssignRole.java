package com.example.pos.entity.sourceData;

public class AssignRole {
    private int accountId;
    private int roleId;
    private int assignerId;
    public AssignRole(){}

    public void setAccountId(int accountId){
        this.accountId = accountId;
    }

    public int getAccountId(){
        return accountId;
    }

    public void setRoleId(int roleId) {
        this.roleId =roleId;
    }

    public int getRoleId(){
        return roleId;
    }

    public void setAssignerId(int assignerId) {
        this.assignerId =assignerId;
    }

    public int getAssignerId(){
        return assignerId;
    }

}
