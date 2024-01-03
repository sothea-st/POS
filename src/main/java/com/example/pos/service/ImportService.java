package com.example.pos.service;

import com.example.pos.constant.JavaConstant;
import com.example.pos.entity.Import;
import com.example.pos.entity.ImportDetail;
import com.example.pos.repository.ImportDetailRepository;
import com.example.pos.repository.ImportRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportService {
    @Autowired
    private ImportRepository repo;

    @Autowired
    private ImportDetailRepository repoDetail;

    @Autowired
    private HttpSession session;
    public void addImport(Import imp ) {
        var createBy = session.getAttribute(JavaConstant.userId);
        Import data = new Import();
        data.setImpNo(imp.getImpNo());
        data.setEmpId(imp.getEmpId());
        data.setSubId(imp.getSubId());
        data.setImpDate(imp.getImpDate());
        data.setDiscount(imp.getDiscount());
        data.setTotal(imp.getTotal());
        data.setCreateBy((Integer)createBy);
        repo.save(data);

        List<ImportDetail> listDetail = imp.getDetails();

        for( int i = 0 ; i < listDetail.size(); i++ ) {
            var value = listDetail.get(i);
            int productId = value.getProductId();
            int qtyNew = value.getQtyNew();
            ImportDetail details = new ImportDetail();
            ImportDetail getImpDetails = repoDetail.getDataImportDetail(productId);

            if( getImpDetails == null ) {
                details.setQtyOld(qtyNew);
            } else {
                int qtyOld = getImpDetails.getQtyOld();
                int qty = qtyOld + qtyNew;
                details.setQtyOld(qty);
            }
            details.setImpId(data.getId());
            details.setProductId(productId);
            details.setQtyNew(qtyNew);
            details.setCost(value.getCost());
            details.setAmount(value.getAmount());
            details.setExpireDate(value.getExpireDate());
            details.setCreateBy((Integer)createBy);
            repoDetail.save(details);
        }

//        return data;
    }

}
