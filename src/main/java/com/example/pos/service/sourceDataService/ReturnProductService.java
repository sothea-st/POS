package com.example.pos.service.sourceDataService;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.constant.JavaConstant;
import com.example.pos.entity.sourceData.ReturnDetails;
import com.example.pos.entity.sourceData.ReturnProduct;
import com.example.pos.repository.sourceDataRepository.ReturnDetailsRepository;
import com.example.pos.repository.sourceDataRepository.ReturnProductRepository;
import java.util.*;
import jakarta.servlet.http.HttpSession;

@Service
public class ReturnProductService {
    @Autowired
    private ReturnProductRepository repo;
    @Autowired
    private HttpSession session;

    @Autowired
    private ReturnDetailsRepository repoDetail;

    public void returnProduct(ReturnProduct re){
        var createBy = session.getAttribute(JavaConstant.userId);

        String time = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a").format(Calendar.getInstance().getTime());
        String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());

        ReturnProduct r = new ReturnProduct();
        r.setCreateBy((Integer)createBy);
        r.setPaymentNo(re.getPaymentNo());
        r.setReturnTime(time);
        r.setReturnDate(date);
        r.setReasonId(re.getReasonId());
        repo.save(r);

        List<ReturnDetails> listDetail = r.getDataDetails();
        System.out.println("data = "+ listDetail);


        // for( int i = 0 ; i < listDetail.size() ; i++ ) {
        //     ReturnDetails obj = new ReturnDetails();
        //     obj.setProId(listDetail.get(i).getProId());
        //     obj.setQty(listDetail.get(i).getQty());
        //     obj.setReturnId(r.getId());
        //     repoDetail.save(obj);
        // }

    }

}
