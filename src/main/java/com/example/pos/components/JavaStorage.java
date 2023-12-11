package com.example.pos.components;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;

public class JavaStorage {
    public static String setFileName(String fileName){
        String timeName = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
        String ext =   fileName.split("\\.")[1];
        return timeName+"."+ext;
    }


    public static void storeImage(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes(); // convert file to byte[]
        String base64 = Base64.getEncoder().encodeToString(bytes); // convert byte[] to base64 as String
        String base64String = "data:image/jpeg;base64,"+base64; // connect "data:image/jpeg;base64," with base64
        String[] strings = base64String.split(","); // convert base64String to array
//        switch (strings[0]) {//check image's extension
//            case "data:image/jpeg;base64":
//                extension = "jpeg";
//                break;
//            case "data:image/png;base64":
//                extension = "png";
//                break;
//            default://should write cases for more images types
//                extension = "jpg";
//                break;
//        }
        //convert base64 string to binary data
        byte[] data =  Base64.getDecoder().decode(strings[1]); // decode strings[1] to byte[]
        String path = "assets\\product\\"+setFileName(file.getOriginalFilename()); // path for store file
        File p = new File(path); // create object File with path
        try  {
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(p));
            outputStream.write(data);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
