package com.example.pos.constant;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.example.pos.util.exception.customeException.FieldIsRequiredException;
import com.example.pos.util.exception.customeException.InValidEmail;
import com.example.pos.util.exception.customeException.JavaDataAlreadyExists;

import lombok.val;

public class JavaValidation {

    public static String checkField(String field, String key) {
        if (field.trim().isEmpty())
            return "The field " + key + " is requred!";
        if (field.trim().length() < 4)
            return "The field " + key + " at least 4 charater!";
        return "";
    }

    public static String checkEmail(String field) {
        if (field.trim().isBlank() || field.trim().isEmpty())
            return "The field email is requred!";
        if (!field.trim().isEmpty() && !field.trim().contains("@"))
            return "invalid email!";
        if (field.trim().length() < 15)
            return "The field email at least 15 charater!";
        return "";
    }

    public static String checkPhone(String field) {
        if (field.trim().isBlank() || field.trim().isEmpty())
            return "The field email is requred!";
        if (!field.trim().isEmpty() && field.length() < 9 || field.length() > 10)
            return "The field phone must be 9 or 10 charaters!";
        return "";
    }

    public static String checkPassword(String field) {
        if (field.trim().isEmpty())
            return "The field password is requred!";
        if (!field.trim().isEmpty() && !isValid(field))
            return "The field password must be 8 charater and one specail character , one uppercase character , one lowercase character , one digit!";
        return "";
    }

    public static boolean isValid(String passwordhere) {

        Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        // errorList.clear();

        boolean flag = true;

        // if (!passwordhere.equals(confirmhere)) {
        // errorList.add("password and confirm password does not match");
        // flag=false;
        // }
        if (passwordhere.length() < 8) {
            // errorList.add("Password lenght must have alleast 8 character !!");
            flag = false;
        }
        if (!specailCharPatten.matcher(passwordhere).find()) {
            // errorList.add("Password must have atleast one specail character !!");
            flag = false;
        }
        if (!UpperCasePatten.matcher(passwordhere).find()) {
            // errorList.add("Password must have atleast one uppercase character !!");
            flag = false;
        }
        if (!lowerCasePatten.matcher(passwordhere).find()) {
            // errorList.add("Password must have atleast one lowercase character !!");
            flag = false;
        }
        if (!digitCasePatten.matcher(passwordhere).find()) {
            // errorList.add("Password must have atleast one digit character !!");
            flag = false;
        }
        return flag;
    }

    public static void requiredData(HashMap<String, Object> value) {
        throw new FieldIsRequiredException(value);
    }

    public static void checkDataAlreadyExists(boolean value) {
        if (value)
            throw new JavaDataAlreadyExists(JavaMessage.nameAlreadyExits());
    }

    public static void emailAlreadyExist(boolean value) {
        if (value)
            throw new JavaDataAlreadyExists(JavaMessage.emailAlreadyExits);
    }

    public static void phoneAlreadyExist(boolean value) {
        if (value)
            throw new JavaDataAlreadyExists(JavaMessage.phoneAlreadyExist);
    }

    public static void inValidEmail(String email) {
        if (!email.contains("@"))
            throw new InValidEmail(JavaMessage.invalidEmail);
    }

}
