package com.example.pos.service;

import com.example.pos.components.JavaStorage;
import com.example.pos.constant.JavaConstant;
import com.example.pos.constant.JavaValidation;
import com.example.pos.entity.FileStore;
import com.example.pos.entity.Product;
import com.example.pos.entity.models.ProductModel;
import com.example.pos.repository.FileStoreRepository;
import com.example.pos.repository.ImportDetailRepository;
import com.example.pos.repository.ProductRepository;
import com.example.pos.repository.productProjection.ProductProjection;
import com.example.pos.util.exception.customeException.JavaNotFoundByIdGiven;
import jakarta.servlet.http.HttpSession;
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
    @Autowired
    private FileStoreRepository fileStore;
    @Autowired
    private HttpSession session;

    @Autowired
    private ImportDetailRepository repoImp;

    public Product addProduct(Product p, MultipartFile file, MultipartFile flagFile) throws IOException {

        boolean proNameKh = repo.existsByProNameKh(p.getProNameKh());
        JavaValidation.checkDataAlreadyExists(proNameKh);

        boolean proNameEn = repo.existsByProNameEn(p.getProNameEn());
        JavaValidation.checkDataAlreadyExists(proNameEn);

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
        if (file == null || file.isEmpty()) {
            pro.setProImageName(JavaConstant.defaultNameImage);
        } else {
            // JavaStorage.storeImage(file); for save image to path assests/product in
            // project
            // String fileName = JavaStorage.setFileName(file.getOriginalFilename());
            String fileName = file.getOriginalFilename();

            // save information image to table pos_file
            FileStore f = new FileStore(fileName, fileName, file.getContentType(), file.getBytes());
            fileStore.save(f);
            pro.setProImageName(fileName);
        }

        if (flagFile == null || flagFile.isEmpty()) {
            pro.setFlag(JavaConstant.defaultFlagNameImage);
        } else {
            // String flagName = JavaStorage.setFileName(flagFile.getOriginalFilename());
            String flagName = flagFile.getOriginalFilename();

            pro.setFlag(flagName);
            FileStore f = new FileStore(flagName, flagName, flagFile.getContentType(), flagFile.getBytes());
            fileStore.save(f);
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

    public List<ProductModel> getProduct(int limit) {
     
        List<ProductModel> list = new ArrayList<>();
        List<ProductProjection> lPro = repo.getProduct(limit);
        for (int i = 0; i < lPro.size(); i++) {
            var data = lPro.get(i);
            Integer qty = repoImp.getQty(data.getId());
            if (qty == null)
                qty = 0;
            ProductModel p = proModel(data, qty);
            list.add(p);
        }

        // map.put("count", repo.countRow());
        // map.put("result", repo.getProduct());
        // map.put("result", list);
        return list;
    }

    public Product editProduct(int id, Product editProduct, MultipartFile file, MultipartFile flag) throws IOException {
        Product previousPro = repo.findById(id).get();
        String fileName = previousPro.getProImageName();
        String flagName = previousPro.getFlag();

        if (!Objects.equals(previousPro.getProNameKh(), editProduct.getProNameKh())) {
            boolean isExist = repo.existsByProNameKh(editProduct.getProNameKh());
            JavaValidation.checkDataAlreadyExists(isExist);
        }

        if (!Objects.equals(previousPro.getProNameEn(), editProduct.getProNameEn())) {
            boolean isExist = repo.existsByProNameEn(editProduct.getProNameEn());
            JavaValidation.checkDataAlreadyExists(isExist);
        }

        Object idUser = session.getAttribute(JavaConstant.userId);

        if (Objects.equals(fileName, JavaConstant.defaultNameImage))
            fileName = "";
        if (file != null && !file.isEmpty()) {
            // save information image to table pos_file
            // String imgName = JavaStorage.setFileName(file.getOriginalFilename());
            String imgName = file.getOriginalFilename();
            FileStore f1 = new FileStore(imgName, imgName, file.getContentType(), file.getBytes());
            fileStore.save(f1);
            previousPro.setProImageName(imgName);
        }

        if (Objects.equals(flagName, JavaConstant.defaultFlagNameImage))
            flagName = "";

        if (flag != null && !flag.isEmpty()) {
            // String fName = JavaStorage.setFileName(flag.getOriginalFilename());
            String fName = flag.getOriginalFilename();
            FileStore f2 = new FileStore(fName, fName, flag.getContentType(), flag.getBytes());
            fileStore.save(f2);
            previousPro.setFlag(fName);
        }

        previousPro.setProNameKh(editProduct.getProNameKh());
        previousPro.setProNameEn(editProduct.getProNameEn());
        // previousPro.setCostKhr(editProduct.getCostKhr());
        previousPro.setCost(editProduct.getCost());
        // previousPro.setPriceKhr(editProduct.getPriceKhr());
        previousPro.setPrice(editProduct.getPrice());
        previousPro.setWeight(editProduct.getWeight());
        previousPro.setBarcode(editProduct.getBarcode());
        previousPro.setDiscount(editProduct.getDiscount());
        previousPro.setBrandId(editProduct.getBrandId());
        previousPro.setProductStatus(editProduct.getProductStatus()); // for detail product in or out stock
        // previousPro.setUnitTypeId(editProduct.getUnitTypeId());
        previousPro.setCatId(editProduct.getCatId());
        previousPro.setNote(editProduct.getNote());
        // previousPro.setDiscountPercentag(editProduct.getDiscountPercentag());
        // previousPro.setCreateBy((Integer) idUser);
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

    public List<ProductModel> getProductByCatId(int catId, int limit) {
        List<ProductProjection> listData = repo.getProductByCatId(catId, limit);

        List<ProductModel> list = new ArrayList<>();
        if (limit == 10) {
            for (int i = 0; i < listData.size(); i++) {
                var data = listData.get(i);
                Integer qty = repoImp.getQty(data.getId());
                if (qty == null)
                    qty = 0;
                ProductModel p = proModel(data, qty);
                list.add(p);
            }
            return list;
        }

        for (int i = 0; i < listData.size(); i++) {

            if (i >= limit - 10) {
                var data = listData.get(i);
                Integer qty = repoImp.getQty(data.getId());
                if (qty == null)
                    qty = 0;
                ProductModel p = proModel(data, qty);
                list.add(p);
            }
        }
        return list;
    }

    public int count(int catId) {
        return repo.countProduct(catId);
    }

    public int countProductByBrandId(int brandId) {
        return repo.countProductByBrandId(brandId);
    }

    public List<ProductModel> getListProductByBrandId(int brandId, int limit) {
    
        List<ProductProjection> listD = repo.getProductByBrandId(brandId, limit);

        List<ProductModel> listModel = new ArrayList<>();

        if (limit == 10) {
            for (int i = 0; i < listD.size(); i++) {
                var data = listD.get(i);
                Integer qty = repoImp.getQty(data.getId());
                if (qty == null)
                    qty = 0;
                ProductModel p = proModel(data, qty);
                listModel.add(p);
            }
            return listModel;
        }

        for (int i = 0; i < listD.size(); i++) {
            if (i >= limit - 10) {
                var data = listD.get(i);
                Integer qty = repoImp.getQty(data.getId());
                if (qty == null)
                    qty = 0;
                ProductModel p = proModel(data, qty);
                listModel.add(p);
            }
        }
        return listModel;
    }

    public ProductModel proModel(ProductProjection data, int qty) {
        ProductModel p = new ProductModel(
                data.getBrand_id(),
                data.getPro_name_kh(),
                data.getPro_image_name(),
                data.getProduct_status(),
                data.getPro_name_en(),
                data.getId(),
                data.getFlag(),
                data.getDiscount(),
                data.getCost(),
                data.getPrice(),
                data.getWeight(),
                data.getBarcode(),
                data.getCat_id(),
                data.getCode_expired(),
                data.getCode_out_stock(),
                qty);
        return p;
    }


    public Product updateDiscount(int id,int discount){
        Optional<Product> p = repo.getProductByOptionalId(id);
        Product data = p.get();
        data.setDiscount(discount);
        repo.save(data);
        return data;
    }
}
