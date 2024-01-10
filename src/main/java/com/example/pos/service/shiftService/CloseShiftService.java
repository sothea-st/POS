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
import com.example.pos.entity.models.SummerySale;
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
    private  CloseShiftRepository repo;

    @Autowired
    private HttpSession session;

    @Autowired
    private CompanyRepository repoCompany;

    @Autowired
    private EmployeeRepository repoEmp;

    @Autowired
    private UserRepository repoUser;

    @Autowired
    private OpenShiftRepository reposOpenShift;

    @Autowired
    private CloseShiftRepository closeShiftRepo;

    @Autowired
    private SaleRepository repoSale;

    @Autowired
    private PaymentRepository repoPay;

    @Autowired
    private SaleDetailsRepository repoSaleDetail;

    public CloseShift closeShift(CloseShift c) {
        var createBy = session.getAttribute(JavaConstant.userId);
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a").format(Calendar.getInstance().getTime());
        String closeDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        CloseShift data = new CloseShift();
        data.setUserId((Integer)createBy);
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


    public HashMap<String,Object> cashierReport(){
        String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        HashMap<String,Object> map = new HashMap<>();
        var userId = session.getAttribute(JavaConstant.userId);

        Company company = repoCompany.getInfoCompany();
        map.put("companyName", company.getCompanyName());
        map.put("companyContact", company.getContact());
        map.put("companyAddress", company.getAddress());
        map.put("companyLogo", company.getPhoto());
       
        User user = repoUser.getUserById((Integer)userId);  
        Employee employee = repoEmp.getEmployeeById(user.getEmpId());
        map.put("userName", employee.getNameEn());

        OpenShift openShift = reposOpenShift.getDataOpenShift((Integer)userId, currentDate);
        map.put("posId", openShift.getPosId());
        map.put("openDate", openShift.getOpenTime());
        map.put("openCash", openShift.getReserveUsd());

        CloseShift closeShift = closeShiftRepo.getCloseShift((Integer)userId, currentDate);
        map.put("closeCash", closeShift.getCashUsd());
        map.put("closeDate", closeShift.getCloseTime());

        
        List<Sale> listId = repoSale.getSale(user.getEmpId(), currentDate);
        int lastIndex = listId.size()-1;
        int firstSaleId = listId.get(0).getId();
        int lastSaleId = listId.get(lastIndex).getId();
        String paymentNoFirst = repoPay.getPaymentNo(firstSaleId);
        String paymentNoLast = repoPay.getPaymentNo(lastSaleId);
        map.put("paymentNoFirst", paymentNoFirst);
        map.put("paymentNoLast", paymentNoLast);

        int qtySale = 0;
        double amount = 0;
        double discount = 0;

        for( int i = 0 ; i < listId.size() ; i++) {
            int ind = listId.get(i).getId();

            int sumQty = repoSaleDetail.sumQty(ind);
            qtySale += sumQty;

            double amountSum = repoSaleDetail.sumAmount(ind).doubleValue();
            amount += amountSum;

            double discountSum = repoSaleDetail.sumDiscount(ind).doubleValue();
            discount += discountSum;
        }

 

 
        ArrayList<SummerySale> summery = new ArrayList<>();
        summery.add(new SummerySale("Sales", qtySale,BigDecimal.valueOf(amount)));
        summery.add(new SummerySale("Returns/Refunds", 0 ,BigDecimal.valueOf(0)));
        summery.add(new SummerySale("Disounts", 10 ,BigDecimal.valueOf(10)));
        map.put("summerySale", summery);


        // map.put("express", closeShift.getExpress());
        // map.put("cashUsd", closeShift.getCashUsd());
        // map.put("cashKhr", closeShift.getCashKhr());
        // map.put("khqrMnk", closeShift.getKhqrMnk());
        // map.put("khqrAba", closeShift.getKhqrAba());
        // map.put("creditCart", closeShift.getCreditCard());


        return map;
    }

}
