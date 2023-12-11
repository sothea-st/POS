package com.example.pos.controller;

import com.example.pos.components.JavaResponse;
import com.example.pos.entity.Product;
import com.example.pos.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/product")
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

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") int id , @RequestParam("status") int status){
        service.deleteProduct(id,status);
        return JavaResponse.success("delete success");
    }

}
