package com.example.pos.authentication.dtos;

public class RegisterUserDto {
    private String userCode;
    private String password;
    private String fullName;

    private Integer role;
//    private String phone;

//    public String getPhone(){
//       return phone;
//    }
//     public RegisterUserDto setPhone(String phone) {
//        this.phone = phone;
//        return this;
//    }


    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getUserCode() {
        return userCode;
    }

    public RegisterUserDto setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterUserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public RegisterUserDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @Override
    public String toString() {
        return "RegisterUserDto{" +
                "email='" + userCode + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phone='" + role + '\'' +
                '}';
    }
}