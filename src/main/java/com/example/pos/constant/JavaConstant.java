package com.example.pos.constant;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JavaConstant {
    public static String success = "success";
    public static String userId = "idUser";
    public static String admin = "Admin";
    public static String statusCode = "statusCode";
    public static long status = 405;
    public static String userCode = "userCode";
    private String defaultPassword = "TT@126$kh#";
    public static String defaultNameImage = "default.jpg";
    public static String defaultFlagNameImage = "default-flag.jpg";
    public static String openShift = "You have to open shift first to processing sale!";
    public static String messageCashierReport = "You need to open shift and close shift first to get report!";
    public static String message = "msg";
    public static String msgCloseShift = "You have to close shift first to get report cashier!";
    public static String closeOpenShfitFirst = "You have to open shift first to close shift!";
    public static double exchangeRate=4150;
    public String getDefaultPassword() {
        return defaultPassword;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    public static String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());

    public static double getTwoPrecision(double amount){
        String value = String.valueOf(amount);
        if (value.length() > 5) {
            String data = value.substring(0, 5);
            double d = Double.parseDouble(data);
            return d;
       }
       return Double.parseDouble(value);
        // double value = new BigDecimal(amount).setScale(2, RoundingMode.DOWN).doubleValue();
        // return value;
    }
 

}
