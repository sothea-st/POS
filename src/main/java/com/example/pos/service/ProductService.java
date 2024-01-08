package com.example.pos.service;

import com.example.pos.components.JavaStorage;
import com.example.pos.constant.JavaConstant;
import com.example.pos.constant.JavaValidation;
import com.example.pos.entity.FileStore;
import com.example.pos.entity.Product;
import com.example.pos.repository.FileStoreRepository;
import com.example.pos.repository.ProductRepository;
import com.example.pos.util.exception.customeException.JavaNotFoundByIdGiven;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
    @Autowired
    private FileStoreRepository fileStore;
    @Autowired
    private HttpSession session;
    public Product addProduct(Product p, MultipartFile file) throws IOException {
        boolean proNameKh = repo.existsByProNameKh(p.getProNameKh());
        JavaValidation.checkDataAlreadyExists(proNameKh);

        boolean proNameEn = repo.existsByProNameEn(p.getProNameEn());
        JavaValidation.checkDataAlreadyExists(proNameEn);

        Object idUser = session.getAttribute(JavaConstant.userId);

        Product pro = new Product();
        pro.setCatId(p.getCatId());
        pro.setUnitTypeId(p.getUnitTypeId());
        pro.setProNameKh(p.getProNameKh());
        pro.setProNameEn(p.getProNameEn());
        pro.setCost(p.getCost());
        pro.setPrice(p.getPrice());
        pro.setNote(p.getNote());
        pro.setCreateBy((Integer) idUser);

        if (file == null || file.isEmpty()) {
            pro.setImage(JavaConstant.defaultNameImage);
        } else {
             // JavaStorage.storeImage(file); for save image to path assests/product in project
            String fileName = JavaStorage.setFileName(file.getOriginalFilename());
            pro.setImage(fileName);
            // save information image to table pos_file
            // String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            FileStore f = new FileStore(fileName, fileName, file.getContentType(), file.getBytes());
            fileStore.save(f);
            pro.setFileName(fileName);
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

        Object idUser = session.getAttribute(JavaConstant.userId);

        if (Objects.equals(fileName, "default.jpg"))
            fileName = "";
        if (file != null && !file.isEmpty()) {
            if (!fileName.isEmpty()) {
                Path fileDelete = Paths.get("assets\\product\\" + fileName);
                Files.delete(fileDelete);
            }
            JavaStorage.storeImage(file);
            previousPro.setImage(JavaStorage.setFileName(Objects.requireNonNull(file.getOriginalFilename())));

            // save information image to table pos_file
            String fName = StringUtils.cleanPath(file.getOriginalFilename());
            FileStore f = new FileStore(JavaStorage.setFileName(file.getOriginalFilename()), fName, file.getContentType(), file.getBytes());
            fileStore.save(f);
            previousPro.setFileName(JavaStorage.setFileName(file.getOriginalFilename()));

        }
        previousPro.setProNameKh(editProduct.getProNameKh());
        previousPro.setProNameEn(editProduct.getProNameEn());
        previousPro.setPrice(editProduct.getPrice());
        previousPro.setCost(editProduct.getCost());
        previousPro.setUnitTypeId(editProduct.getUnitTypeId());
        previousPro.setCatId(editProduct.getCatId());
        previousPro.setNote(editProduct.getNote());
//        previousPro.setCreateBy((Integer) idUser);
        repo.save(previousPro);
        return previousPro;
    }

    public void deleteProduct(int id, Product p) {
        Optional<Product> data = repo.findById(id);
        Product obj = data.get();
        obj.setStatus(p.isStatus());
        obj.setDeleted(p.isDeleted());
        repo.save(obj);
    }

    public byte[] getFile(String id) throws IOException {
        Optional<FileStore> fileDB = fileStore.findById(id);
        return fileDB.get().getData();
    }

}
