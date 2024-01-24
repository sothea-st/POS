package com.example.pos.service.searchByBarcodeOrNameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.example.pos.entity.Product;
import com.example.pos.repository.ProductRepository;

@Service
public class SearchByBarcodeOrNameService {
    @Autowired
    private ProductRepository repo;

    public List<Product> search(String code, String nameSearch) {
        List<Product> data = null;
        if (code.equals("barcode")) {
            data = repo.searchProductByBarcode(nameSearch);
        } else {
            data = repo.searchProductByName(nameSearch);
        }
        return data;
    }

}
