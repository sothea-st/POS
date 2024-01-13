package com.example.pos.components;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.springframework.util.MimeTypeUtils.IMAGE_JPEG_VALUE;

import java.util.Map;

public class JavaResponse {
    public static ResponseEntity<?> success(Object data){
        return ResponseEntity.ok().body(Map.of("msg","success","data",data));
    }

    public static ResponseEntity<?> deleteSuccess(int id){
        return ResponseEntity.ok().body(Map.of("msg","delete success at id: "+id,"status",200));
    }

    public static ResponseEntity<?> imageSuccess(byte[] imageData){
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(IMAGE_JPEG_VALUE)).body(imageData);
    }

    public static ResponseEntity<?> error(Object data){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(data);
    }

}
