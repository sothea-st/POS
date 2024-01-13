package com.example.pos.constant;

public class JavaConstant {
    public static String userId = "idUser";
    private String defaultPassword = "tt@126$kh#";
    public static String defaultNameImage = "default.jpg";
    public static String openShift = "You have to open shift first to processing sale!";
    public static String messageCashierReport = "You need to open shift and close shift first to get report!";
    public static String message = "message";
    public static String msgCloseShift = "You have to open shift first to close shift!";
    public String getDefaultPassword() {
        return defaultPassword;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }


}
