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
    public Import addImport(Import imp ) {
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

        System.out.println("id = " + data.getId());

        List<ImportDetail> listDetail = imp.getDetails();
        for( int i = 0 ; i < listDetail.size(); i++ ) {
            var detail = listDetail.get(i);
            ImportDetail details = new ImportDetail();
            details.setProductId(details.getProductId());
            details.setImpId(data.getId());
            details.setQty(detail.getQty());
            details.setCost(detail.getCost());
            details.setAmount(detail.getAmount());
            details.setExpireDate(detail.getExpireDate());
            details.setCreateBy((Integer)createBy);
            repoDetail.save(details);
        }

        return  data;
    }




}
