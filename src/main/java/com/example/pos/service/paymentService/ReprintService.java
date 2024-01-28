package com.example.pos.service.paymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pos.authentication.repositories.UserRepository;
import com.example.pos.constant.JavaConstant;
import com.example.pos.entity.Company;
import com.example.pos.entity.payment.Payment;
import com.example.pos.entity.projection.PaymentProjection;
import com.example.pos.entity.projection.SaleDetailProjection;
import com.example.pos.repository.SaleDetailsRepository;
import com.example.pos.repository.companyRepository.CompanyRepository;
import com.example.pos.repository.paymentRepository.PaymentRepository;
import java.util.*;
import jakarta.servlet.http.HttpSession;

@Service
public class ReprintService {
    @Autowired
    private PaymentRepository repo;

    @Autowired
    private CompanyRepository companyRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SaleDetailsRepository saleDetailRepo;
 
    public HashMap<String, Object> readData(String paymentNo,int userId) {
     
        HashMap<String, Object> map = new HashMap<>();
        Company c = companyRepo.getInfoCompany();
        map.put("companyName", c.getCompanyName());
        map.put("companyAddres", c.getAddress());
        map.put("companyContact", c.getContact());
        map.put("companyLogo", c.getPhoto());
        map.put("vattin", c.getVattin());
        PaymentProjection paymentData=null;
        if( paymentNo.isEmpty() ) {
            paymentData = repo.getPaymentDataWithoutPaymentNo(JavaConstant.currentDate);
        } else {
            paymentData = repo.getPaymentDataWithPaymentNo(paymentNo);
        }
        map.put("total", paymentData.getTotal());
        map.put("receiveKhr", paymentData.getReceive_khr());
        map.put("receiveUsd", paymentData.getReceive_usd());
        map.put("changeUsd", paymentData.getChange_usd());
        map.put("changeKhr", paymentData.getChange_khr());
        map.put("remainingUsd", paymentData.getRemaining_usd());
        map.put("remainingKhr", paymentData.getRemaining_khr());
        map.put("paymentNo", paymentData.getPayment_no());  
        map.put("saleDate", paymentData.getSale_date());
        map.put("customerType", paymentData.getCustomer_type());
        List<SaleDetailProjection> dataSaleDetails = saleDetailRepo.getDataDetail(userId,JavaConstant.currentDate,paymentData.getSale_id());
        map.put("saleDetails", dataSaleDetails);
        String empName = userRepo.getNameEmp(userId);
        map.put("empName", empName);
        return map;
    }

}
