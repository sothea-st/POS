package com.example.pos.service.shiftService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.authentication.entity.User;
import com.example.pos.authentication.repositories.UserRepository;
import com.example.pos.constant.JavaConstant;
import com.example.pos.entity.CloseShift;
import com.example.pos.entity.Company;
import com.example.pos.entity.Employee;
import com.example.pos.entity.OpenShift;
import com.example.pos.entity.Sale;
import com.example.pos.entity.SaleDetail;
import com.example.pos.entity.models.SummeryCashierReport;
import com.example.pos.entity.projection.CashierReportProjection;

import com.example.pos.repository.EmployeeRepository;
import com.example.pos.repository.SaleDetailsRepository;
import com.example.pos.repository.SaleRepository;
import com.example.pos.repository.companyRepository.CompanyRepository;
import com.example.pos.repository.paymentRepository.PaymentRepository;
import com.example.pos.repository.shiftRepository.CloseShiftRepository;
import com.example.pos.repository.shiftRepository.OpenShiftRepository;

import java.util.*;
import java.math.BigDecimal;
import java.text.*;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;

@Service
public class CloseShiftService {
    @Autowired
    private CloseShiftRepository repo;

    @Autowired
    private HttpSession session;

    public CloseShift closeShift(CloseShift c) {
        var createBy = session.getAttribute(JavaConstant.userId);
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a").format(Calendar.getInstance().getTime());
        String closeDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        CloseShift data = new CloseShift();
        data.setUserId((Integer) createBy);
        data.setCloseTime(timeStamp);
        data.setCloseDate(closeDate);
        data.setExpress(c.getExpress());
        data.setCashKhr(c.getCashKhr());
        data.setCashUsd(c.getCashUsd());
        data.setKhqrMnk(c.getKhqrMnk());
        data.setKhqrAba(c.getKhqrAba());
        data.setCreditCard(c.getCreditCard());
        data.setCreateBy((Integer) createBy);
        repo.save(data);
        return data;
    }

   

}
