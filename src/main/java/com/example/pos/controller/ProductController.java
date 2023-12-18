package com.example.pos.controller;

import com.example.pos.components.JavaResponse;
import com.example.pos.entity.Product;
import com.example.pos.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService service;


    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @ModelAttribute Product product ,
                                        @RequestParam(value = "file",required = false)MultipartFile file) throws IOException {

        Product data = service.addProduct(product,file);
        return JavaResponse.success(data);
    }
    // get image from path assets\\product\\imageName
    @GetMapping(value = "/image/{imageName}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable("imageName") String imageName) throws IOException {
        return service.getImage(imageName);
    }

    @GetMapping
    public ResponseEntity<?> getProduct(){
        Map<String, Object> data = service.getProduct();
        return JavaResponse.success(data);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> editProduct(@PathVariable("id") int id , @ModelAttribute Product p,
                                         @RequestParam(value = "file",required = false) MultipartFile file) throws IOException {
        Product pro =  service.editProduct(id,p,file);
        return JavaResponse.success(pro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDataById(@PathVariable("id") int id){
        Product data = service.readData(id);
        return JavaResponse.success(data);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id")int id , @RequestBody Product p){
        service.deleteProduct(id,p);
        return JavaResponse.deleteSuccess(id);
    }


    @GetMapping("/readFileById/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) throws IOException {
        byte[] imageData = service.getFile(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(IMAGE_PNG_VALUE))
                .body(imageData);
    }

}
