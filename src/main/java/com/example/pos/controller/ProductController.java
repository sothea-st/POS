package com.example.pos.controller;

import com.example.pos.components.JavaResponse;
import com.example.pos.entity.Product;
import com.example.pos.entity.models.ProductModel;
import com.example.pos.repository.productProjection.ProductProjection;
import com.example.pos.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService service;


    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @ModelAttribute Product product ,
                                        @RequestParam(value = "flagFile",required = false)MultipartFile flagFile,
                                        @RequestParam(value = "file",required = false)MultipartFile file
                                        ) throws IOException {

        Product data = service.addProduct(product,file,flagFile);
        return JavaResponse.success(data);
    }
    // get image from path assets\\product\\imageName
    @GetMapping(value = "/image/{imageName}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable("imageName") String imageName) throws IOException {
        return service.getImage(imageName);
    }

    @GetMapping("/getProductByCatId")
    public ResponseEntity<?> getProductByCatId(@RequestParam("catId") int catId ,@RequestParam("limit") int limit) {
        List<ProductModel> data = service.getProductByCatId(catId,limit);
        int count = service.count(catId);
        return  ResponseEntity.ok().body(Map.of("msg","success","data",data,"count",count));
    }

    @GetMapping
    public ResponseEntity<?> getProduct(@RequestParam("limit") int limit){
        Map<String, Object> data = service.getProduct(limit);
        return JavaResponse.success(data);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> editProduct(@PathVariable("id") int id , @ModelAttribute Product p,
                                         @RequestParam(value = "file",required = false) MultipartFile file,
                                         @RequestParam(value = "flagFile" ,required = false) MultipartFile flagFile
                                         ) throws IOException {
        Product pro =  service.editProduct(id,p,file,flagFile);
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

    @GetMapping("/getProductByBrandId")
    public ResponseEntity<?> getProductByBrandId(@RequestParam("brandId") int brandId ,@RequestParam("limit") int limit) {
        List<ProductModel> data = service.getListProductByBrandId(brandId,limit);
        int count = service.countProductByBrandId(brandId);
        return  ResponseEntity.ok().body(Map.of("msg","success","data",data,"count",count));
    }

}
