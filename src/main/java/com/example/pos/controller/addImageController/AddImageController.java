package com.example.pos.controller.addImageController;

import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.pos.components.JavaResponse;
import com.example.pos.service.addImageService.AddImageService;

@RestController
@RequestMapping("/api/public/addImageForBackground")
public class AddImageController {
    @Autowired
    private AddImageService service;

    @PostMapping
    public ResponseEntity<?> addImage(@RequestParam("file") MultipartFile file) throws IOException {
        service.addImage(file);
        return JavaResponse.success(file.getOriginalFilename());
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) throws IOException {
        byte[] imageData = service.getFile(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(IMAGE_PNG_VALUE))
                .body(imageData);
    }

}
