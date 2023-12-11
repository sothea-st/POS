package com.example.pos.service;

import com.example.pos.components.JavaStorage;
import com.example.pos.constant.JavaMessage;
import com.example.pos.constant.JavaValidation;
import com.example.pos.entity.Product;
import com.example.pos.repository.ProductRepository;
import com.example.pos.util.exception.customeException.JavaNotFoundByIdGiven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public Product addProduct(Product p, MultipartFile file) throws IOException {
        boolean proName = repo.existsByProName(p.getProName());
        JavaValidation.checkDataAlreadyExists(proName);
        Product pro = new Product();
        pro.setCatId(p.getCatId());
        pro.setProName(p.getProName());
        pro.setQty(p.getQty());
        pro.setUnitPrice(p.getUnitPrice());
        double subTotal = p.getQty() * p.getUnitPrice();
        pro.setSubTotal(subTotal);

        if( file == null ) {
            pro.setImage("default.jpg");
        } else {
            if( file.isEmpty() ) {
                pro.setImage("default.jpg");
            } else {
                JavaStorage.storeImage(file);
                pro.setImage(JavaStorage.setFileName(Objects.requireNonNull(file.getOriginalFilename())));
            }
        }
        repo.save(pro);
        return pro;
    }

    public Product readData(int id){
        Product data = repo.getProductById(id);
        if( data == null ) throw  new JavaNotFoundByIdGiven();
        return data;
    }

    public byte[] getImage(String imageName) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("assets\\product\\"+imageName)); // convert imageName to byte[]
        String base64 = Base64.getEncoder().encodeToString(bytes); // encode byte[] to string base64
        return Base64.getDecoder().decode(base64); // decode string base64
    }

    public Map<String,Object> getProduct(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("count",repo.countRow());
        map.put("result",repo.getProduct());
        return map;
    }

    public Product editProduct(int id ,Product editProduct,MultipartFile file) throws IOException {
        Product previousPro = repo.findById(id).get();
        String fileName = previousPro.getImage();

        if( !Objects.equals(previousPro.getProName(), editProduct.getProName()) ) {
            boolean isExist = repo.existsByProName(editProduct.getProName());
            JavaValidation.checkDataAlreadyExists(isExist);
        }

        if(Objects.equals(fileName, "default.jpg")) fileName="";
        if( file != null &&  !file.isEmpty() ) {
            if( !fileName.isEmpty() ) {
                Path fileDelete = Paths.get("assets\\product\\"+fileName);
                Files.delete(fileDelete);
            }
            JavaStorage.storeImage(file);
            previousPro.setImage(JavaStorage.setFileName(Objects.requireNonNull(file.getOriginalFilename())));
        }
        previousPro.setProName(editProduct.getProName());
        previousPro.setQty(editProduct.getQty());
        previousPro.setCatId(editProduct.getCatId());
        previousPro.setUnitPrice(editProduct.getUnitPrice());
        double unitPrice = editProduct.getQty()*editProduct.getUnitPrice();
        previousPro.setSubTotal(unitPrice);
        previousPro.setStatus(editProduct.getStatus());
        repo.save(previousPro);
        return previousPro;
    }

    public void deleteProduct(int id,int status){
        Product p = repo.findById(id).get();
        p.setStatus(status);
        repo.save(p);
    }

}
