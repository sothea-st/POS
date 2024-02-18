package com.example.demotest.service;

import org.springframework.stereotype.Service;
 
 
import jakarta.servlet.http.HttpSession;
import net.bytebuddy.utility.JavaConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.example.demotest.entity.Product;
import com.example.demotest.repository.ProductRepo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;
   
    public Product addProduct(Product p, MultipartFile file, MultipartFile flagFile) throws IOException {

        

        // Object idUser = session.getAttribute(JavaConstant.userId);

        Product pro = new Product();
        pro.setCatId(p.getCatId());
        // pro.setUnitTypeId(p.getUnitTypeId());
        pro.setProNameKh(p.getProNameKh());
        pro.setProNameEn(p.getProNameEn());
        pro.setCost(p.getCost());
        pro.setPrice(p.getPrice());
        // pro.setCostKhr(p.getCostKhr());
        // pro.setPriceKhr(p.getPriceKhr());
        pro.setNote(p.getNote());
        pro.setCreateBy(p.getCreateBy());
        pro.setWeight(p.getWeight());
        pro.setBarcode(p.getBarcode());
        pro.setDiscount(p.getDiscount());
        pro.setBrandId(p.getBrandId());
        // pro.setDiscountPercentag(p.getDiscountPercentag().isEmpty() ? "0" :
        // p.getDiscountPercentag());
        pro.setProductStatus(p.getProductStatus()); // for detail product in or out stock
     //    if (file == null || file.isEmpty()) {
     //        pro.setProImageName("default.jpg");
     //    } else {
     //        // JavaStorage.storeImage(file); for save image to path assests/product in
     //        // project
     //        // String fileName = JavaStorage.setFileName(file.getOriginalFilename());
     //        String fileName = file.getOriginalFilename();

     //        // save information image to table pos_file
     //        FileStore f = new FileStore(fileName, fileName, file.getContentType(), file.getBytes());
     //        fileStore.save(f);
     //        pro.setProImageName(fileName);
     //    }

     //    if (flagFile == null || flagFile.isEmpty()) {
     //        pro.setFlag("default-flag.jpg");
     //    } else {
     //        // String flagName = JavaStorage.setFileName(flagFile.getOriginalFilename());
     //        String flagName = flagFile.getOriginalFilename();

     //        pro.setFlag(flagName);
     //        FileStore f = new FileStore(flagName, flagName, flagFile.getContentType(), flagFile.getBytes());
     //        fileStore.save(f);
     //    }

        repo.save(pro);
        return pro;
    }

    public List<Product> getProd(){
     return repo.getAll();
    }

//     public Product readData(int id) {
//         Product data = repo.getProductById(id);
//         if (data == null)
//             throw new JavaNotFoundByIdGiven();
//         return data;
//     }

    public byte[] getImage(String imageName) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("assets\\product\\" + imageName)); // convert imageName to byte[]
        String base64 = Base64.getEncoder().encodeToString(bytes); // encode byte[] to string base64
        return Base64.getDecoder().decode(base64); // decode string base64
    }

  
    public void deleteProduct(int id, Product p) {
        Optional<Product> data = repo.findById(id);
        Product obj = data.get();
        obj.setStatus(p.isStatus());
        obj.setDeleted(p.isDeleted());
        repo.save(obj);
    }
 
}
