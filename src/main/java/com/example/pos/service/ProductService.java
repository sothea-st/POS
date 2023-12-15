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
        boolean proNameKh = repo.existsByProNameKh(p.getProNameKh());
        JavaValidation.checkDataAlreadyExists(proNameKh);

        boolean proNameEn = repo.existsByProNameEn(p.getProNameEn());
        JavaValidation.checkDataAlreadyExists(proNameEn);

        Product pro = new Product();
        pro.setCatId(p.getCatId());
        pro.setUnitTypeId(p.getUnitTypeId());
        pro.setProNameKh(p.getProNameKh());
        pro.setProNameEn(p.getProNameEn());
        pro.setCost(p.getCost());
        pro.setPrice(p.getPrice());

        if (file == null) {
            pro.setImage("default.jpg");
        } else {
            if (file.isEmpty()) {
                pro.setImage("default.jpg");
            } else {
                JavaStorage.storeImage(file);
                pro.setImage(JavaStorage.setFileName(Objects.requireNonNull(file.getOriginalFilename())));
            }
        }
        repo.save(pro);
        return pro;
    }

    public Product readData(int id) {
        Product data = repo.getProductById(id);
        if (data == null)
            throw new JavaNotFoundByIdGiven();
        return data;
    }

    public byte[] getImage(String imageName) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("assets\\product\\" + imageName)); // convert imageName to byte[]
        String base64 = Base64.getEncoder().encodeToString(bytes); // encode byte[] to string base64
        return Base64.getDecoder().decode(base64); // decode string base64
    }

    public Map<String, Object> getProduct() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("count", repo.countRow());
        map.put("result", repo.getProduct());
        return map;
    }

    public Product editProduct(int id, Product editProduct, MultipartFile file) throws IOException {
        Product previousPro = repo.findById(id).get();
        String fileName = previousPro.getImage();

        if (!Objects.equals(previousPro.getProNameKh(), editProduct.getProNameKh())) {
            boolean isExist = repo.existsByProNameKh(editProduct.getProNameKh());
            JavaValidation.checkDataAlreadyExists(isExist);
        }

        if (!Objects.equals(previousPro.getProNameEn(), editProduct.getProNameEn())) {
            boolean isExist = repo.existsByProNameEn(editProduct.getProNameEn());
            JavaValidation.checkDataAlreadyExists(isExist);
        }

        if (Objects.equals(fileName, "default.jpg"))
            fileName = "";
        if (file != null && !file.isEmpty()) {
            if (!fileName.isEmpty()) {
                Path fileDelete = Paths.get("assets\\product\\" + fileName);
                Files.delete(fileDelete);
            }
            JavaStorage.storeImage(file);
            previousPro.setImage(JavaStorage.setFileName(Objects.requireNonNull(file.getOriginalFilename())));
        }
        previousPro.setProNameKh(editProduct.getProNameKh());
        previousPro.setProNameEn(editProduct.getProNameEn());
        previousPro.setPrice(editProduct.getPrice());
        previousPro.setCost(editProduct.getCost());
        previousPro.setUnitTypeId(editProduct.getUnitTypeId());
        previousPro.setCatId(editProduct.getCatId());

        repo.save(previousPro);
        return previousPro;
    }

    public void deleteProduct(int id, boolean status) {
        Product p = repo.findById(id).get();
        p.setStatus(status);
        repo.save(p);
    }

}
