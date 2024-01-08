package com.example.pos.service.paymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.authentication.entity.User;
import com.example.pos.authentication.repositories.UserRepository;
import com.example.pos.constant.JavaConstant;
import com.example.pos.entity.Company;
import com.example.pos.entity.payment.Payment;
import com.example.pos.repository.companyRepository.CompanyRepository;
import com.example.pos.repository.paymentRepository.PaymentRepository;
import java.util.*;

import jakarta.servlet.http.HttpSession;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository repo;

    @Autowired
    private HttpSession session;

    @Autowired
    private CompanyRepository companyRepo;

    @Autowired
    private UserRepository userRepo;

    public Payment payment(Payment p) {
        var createBy = session.getAttribute(JavaConstant.userId);
        int count = repo.countRecord();
        count++;
        String paymentNo = paymentNo(count);
        Payment data = new Payment();
        data.setPaymentNo(paymentNo);
        data.setSaleId(p.getSaleId());
        data.setTotalUsd(p.getTotalUsd());
        data.setTotalKhr(p.getTotalKhr());
        data.setReceiveKhr(p.getReceiveKhr());
        data.setReceiveUsd(p.getReceiveUsd());
        data.setRemainingKhr(p.getRemainingKhr());
        data.setRemainingUsd(p.getRemainingUsd());
        data.setChangeKhr(p.getChangeKhr());
        data.setChangeUsd(p.getChangeUsd());
        data.setPaymentType(p.getPaymentType());
        data.setCustomerTypeId(p.getCustomerTypeId());
        data.setSourceId(p.getSourceId());
        data.setCreateBy((Integer)createBy);
        repo.save(data);
        return data;
    }

    String paymentNo(int count) {
        String paymentNo = "";
        if (count < 10) {
            paymentNo = "00000000" + count;
        } else if (count < 100) {
            paymentNo = "0000000" + count;
        } else if (count < 1000) {
            paymentNo = "000000" + count;
        } else if (count < 10000) {
            paymentNo = "00000" + count;
        } else if (count < 100000) {
            paymentNo = "0000" + count;
        } else if (count < 1000000) {
            paymentNo = "000" + count;
        } else if (count < 10000000) {
            paymentNo = "00" + count;
        } else if (count < 100000000) {
            paymentNo = "0" + count;
        } else if (count < 1000000000) {
            paymentNo = "" + count;
        }
        return paymentNo;
    }


    public HashMap<String,Object> readData(){
        var createBy = session.getAttribute(JavaConstant.userId);
        HashMap<String,Object> map = new HashMap<>();
        Company c = companyRepo.getInfoCompany();
        map.put("companyName", c.getCompanyName());
        map.put("companyAddres", c.getAddress());
        map.put("companyContact", c.getContact());
        map.put("companyLogo", c.getPhoto());

        String empName = userRepo.getNameEmp((Integer)createBy);
        map.put("empName", empName);

        return map;
    }
    
    

}
