package com.example.pos.service;

import com.example.pos.constant.JavaConstant;
import com.example.pos.entity.Import;
import com.example.pos.entity.ImportDetail;
import com.example.pos.entity.Sale;
import com.example.pos.entity.SaleDetail;
import com.example.pos.repository.ImportDetailRepository;
import com.example.pos.repository.SaleDetailsRepository;
import com.example.pos.repository.SaleRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    @Autowired
    private SaleRepository repo;

    @Autowired
    private HttpSession session;

    @Autowired
    private SaleDetailsRepository repoDetail;

    @Autowired
    private ImportDetailRepository repoImp;

    public Sale saleProduct(Sale s) {
        var createBy = session.getAttribute(JavaConstant.userId);

        Sale data = new Sale();
        data.setEmpId(s.getEmpId());
        data.setSaleDate(s.getSaleDate());
        data.setDiscount(s.getDiscount());
        data.setTotal(s.getTotal());
        data.setCreateBy((Integer)createBy);
        repo.save(data);

        List<SaleDetail> details = s.getDataSale();

        for( int i = 0 ; i < details.size() ; i++ ) {
            var detail = details.get(i);
            int productId = detail.getProductId();
            int qtyNew = detail.getQty();

            SaleDetail dataDetail = new SaleDetail();
            dataDetail.setSaleId(data.getId());
            dataDetail.setProductId(productId);
            dataDetail.setQty(qtyNew);
            dataDetail.setAmount(detail.getAmount());
            dataDetail.setDiscount(detail.getDiscount());
            repoDetail.save(detail);

            ImportDetail getQtyOld = repoImp.getDataImportDetail(productId);
            int qtyOld = getQtyOld.getQtyOld();
            int qty = qtyOld - qtyNew;
            System.out.println("data id = " + data.getId());
//            Optional<ImportDetail> dataDetail =

        }


        return data;
    }

}
