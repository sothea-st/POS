package com.example.pos.service.cashierReport;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

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
import com.example.pos.entity.models.SummeryCashierReport;
import com.example.pos.repository.EmployeeRepository;
import com.example.pos.repository.SaleDetailsRepository;
import com.example.pos.repository.SaleRepository;
import com.example.pos.repository.companyRepository.CompanyRepository;
import com.example.pos.repository.paymentRepository.PaymentRepository;
import com.example.pos.repository.shiftRepository.CloseShiftRepository;
import com.example.pos.repository.shiftRepository.OpenShiftRepository;
import java.util.*;
import jakarta.servlet.http.HttpSession;

@Service
public class CashierReportService {
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
     public HashMap<String, Object> cashierReport() {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        HashMap<String, Object> map = new HashMap<>();
        var userId = session.getAttribute(JavaConstant.userId);

        // get company info
        Company company = repoCompany.getInfoCompany();
        map.put("companyName", company.getCompanyName());
        map.put("companyContact", company.getContact());
        map.put("companyAddress", company.getAddress());
        map.put("companyLogo", company.getPhoto());

        // get user name
        User user = repoUser.getUserById((Integer) userId);
        Employee employee = repoEmp.getEmployeeById(user.getEmpId());
        map.put("userName", employee.getNameEn());

        // get posId, openDate , openCash from openShift
        OpenShift openShift = reposOpenShift.getDataOpenShift((Integer) userId, currentDate);
        map.put("posId", openShift.getPosId());
        map.put("openDate", openShift.getOpenTime());
        map.put("openCash", openShift.getReserveUsd());

        // get closeCash, closeDate from CloseShift
        CloseShift closeShift = closeShiftRepo.getCloseShift((Integer) userId, currentDate);

        // double closeCash = openShift.getReserveUsd().doubleValue() + closeShift.getCashUsd().doubleValue();
        map.put("closeCash", closeShift.getCashUsd());
        map.put("closeDate", closeShift.getCloseTime());

        List<Sale> listId = repoSale.getSale(user.getEmpId(), currentDate);
        ArrayList<Integer> listInt = new ArrayList<>();
        for (int i = 0; i < listId.size(); i++) {
            int ind = listId.get(i).getId();
            listInt.add(ind);
        }

        List<Integer> listIdUsd = repoSale.getSaleIdByUsd(user.getEmpId(), currentDate);
     
        List<Integer> listIdKhr = repoSale.getSaleIdByKhr(user.getEmpId(), currentDate);
        // Sale summery
        SummeryCashierReport(listId, currentDate, map, listInt);
        // payment summery
        paymentSummery(listIdUsd,listIdKhr,map);
        // discount summery
        discountSummery(listInt, map);
        return map;
    }

    public void discountSummery(List<Integer> listInt, HashMap<String, Object> map) {
        List<Integer> listDiscount = new ArrayList<>();
        listDiscount.add(10);
        listDiscount.add(15);
        listDiscount.add(20);
        listDiscount.add(30);
        listDiscount.add(50);
        List<SummeryCashierReport> discount = new ArrayList<>();
        for (int i = 0; i < listDiscount.size(); i++) {
            int sumQtyDiscountPercentag = 0;
            String sumQtyDiscountPercentagStr = repoSaleDetail.sumQtyDiscountPercentag(listInt, listDiscount.get(i));
            if (sumQtyDiscountPercentagStr != null)
                sumQtyDiscountPercentag = Integer.valueOf(sumQtyDiscountPercentagStr);

            double discountAmount = 0;
            String sumQtyDiscountAmountStr = repoSaleDetail.sumAmountDiscountPercentag(listInt, listDiscount.get(i));
            if (sumQtyDiscountAmountStr != null)
                discountAmount = Double.valueOf(sumQtyDiscountAmountStr);

            discount.add(new SummeryCashierReport(listDiscount.get(i) + "%", sumQtyDiscountPercentag,
                    BigDecimal.valueOf(discountAmount)));
        }

        map.put("discountSummery", discount);
    }

    public void paymentSummery(List<Integer> listIntUsd,List<Integer> listIntKhr, HashMap<String, Object> map) {
        int sumQtyPaymentByUsd = 0;
        String sumQtyPaymentByUsdStr = repoSaleDetail.sumQtyPaymentByUsd(listIntUsd);
        if (sumQtyPaymentByUsdStr != null)
            sumQtyPaymentByUsd = Integer.valueOf(sumQtyPaymentByUsdStr);

        double sumAmountPaymentByUsd = 0;
        String sumAmountPaymentByUsdStr = repoSaleDetail.sumAmountPaymentByUsd(listIntUsd);
        if (sumAmountPaymentByUsdStr != null)
            sumAmountPaymentByUsd = Double.valueOf(sumAmountPaymentByUsdStr);

        int sumQtyPaymentByKhr = 0;
        String sumQtyPaymentByKhrStr = repoSaleDetail.sumQtyPaymentByKhr(listIntKhr);
        if (sumQtyPaymentByKhrStr != null)
            sumQtyPaymentByKhr = Integer.valueOf(sumQtyPaymentByKhrStr);

 
        double sumAmountPaymentByKhr = 0;
        String sumAmountPaymentByKhrStr = repoSaleDetail.sumAmountPaymentByKhr(listIntKhr);
        if (sumAmountPaymentByKhrStr != null)
            sumAmountPaymentByKhr = Double.valueOf(sumAmountPaymentByKhrStr);

        ArrayList<SummeryCashierReport> payment = new ArrayList<>();

        payment.add(new SummeryCashierReport("RED ANT EXPRESS", 0, BigDecimal.valueOf(0)));
        payment.add(new SummeryCashierReport("CASH (USD)", sumQtyPaymentByUsd, BigDecimal.valueOf(sumAmountPaymentByUsd)));
        payment.add( new SummeryCashierReport("CASH (KHR)", sumQtyPaymentByKhr, BigDecimal.valueOf(sumAmountPaymentByKhr)));
        payment.add(new SummeryCashierReport("KHQR-MNK", 0, BigDecimal.valueOf(0)));
        payment.add(new SummeryCashierReport("KHQR-ABA", 0, BigDecimal.valueOf(0)));
        payment.add(new SummeryCashierReport("ABA-CREDIT CART", 0, BigDecimal.valueOf(0)));
        map.put("summeryPayemnt", payment);
    }

    public void SummeryCashierReport(List<Sale> listId, String currentDate, HashMap<String, Object> map,List<Integer> listInt) {
        int lastIndex = listId.size() - 1;
        int firstSaleId = listId.get(0).getId();
        int lastSaleId = listId.get(lastIndex).getId();
        String paymentNoFirst = repoPay.getPaymentNo(firstSaleId);
        String paymentNoLast = repoPay.getPaymentNo(lastSaleId);
        map.put("paymentNoFirst", paymentNoFirst);
        map.put("paymentNoLast", paymentNoLast);

        int qtyDiscount = 0;
        String qtyDiscountStr = repoSaleDetail.sumQtyDiscount(listInt);
        if (qtyDiscountStr != null)
            qtyDiscount = Integer.valueOf(qtyDiscountStr);

        double amountDiscount = 0.00;
        String amountDiscountStr = repoSaleDetail.sumAmontDiscount(listInt);
        if (amountDiscountStr != null)
            amountDiscount = Double.valueOf(amountDiscountStr);

        int qtySale = 0;
        String qtySaleStr = repoSaleDetail.sumQtySaled(listInt);
        if (qtySaleStr != null)
            qtySale = Integer.valueOf(qtySaleStr);

        double amount = 0;
        String amountStr = repoSaleDetail.sumAmount(listInt);
        if (amountStr != null)
            amount = Double.valueOf(amountStr);

        ArrayList<SummeryCashierReport> summery = new ArrayList<>();

        summery.add(new SummeryCashierReport("Sales", qtySale, BigDecimal.valueOf(amount)));
        summery.add(new SummeryCashierReport("Returns/Refunds", 0, BigDecimal.valueOf(0)));
        summery.add(new SummeryCashierReport("Disounts", qtyDiscount, BigDecimal.valueOf(amountDiscount)));
        map.put("SummerySale", summery);
    }
}
