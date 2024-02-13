package com.example.pos.service.cashierReport;

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
import com.example.pos.entity.projection.CalculateDiscountProjection;
import com.example.pos.repository.EmployeeRepository;
import com.example.pos.repository.SaleDetailsRepository;
import com.example.pos.repository.SaleRepository;
import com.example.pos.repository.companyRepository.CompanyRepository;
import com.example.pos.repository.paymentRepository.PaymentRepository;
import com.example.pos.repository.shiftRepository.CloseShiftRepository;
import com.example.pos.repository.shiftRepository.OpenShiftRepository;
import java.util.*;
import jakarta.servlet.http.HttpSession;
import java.math.*;
import java.text.DecimalFormat;

@Service
public class CashierReportService {

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
 

    private HashMap<String, Object> map = new HashMap<>();

    public HashMap<String, Object> cashierReport(String userCode, int userId, String posId) {
        DecimalFormat dm = new DecimalFormat("#,###0.00");
        // var userId = session.getAttribute(JavaConstant.userId);
        int id = userId;
        // get company info
        Company company = repoCompany.getInfoCompany();
        map.put("companyName", company.getCompanyName());
        map.put("companyContact", company.getContact());
        map.put("companyAddress", company.getAddress());
        map.put("companyLogo", company.getPhoto());

        // get user name
        User user = repoUser.getUserById(id);
        Employee employee = repoEmp.getEmployeeById(user.getEmpId());
        map.put("userName", employee.getNameEn());

        // get posId, openDate , openCash from openShift
        OpenShift openShift = reposOpenShift.getDataOpenShift(userCode, JavaConstant.currentDate, posId);
        double convertKh = openShift.getReserveKhr().doubleValue() / JavaConstant.exchangeRate;
        double totalOpen = convertKh + openShift.getReserveUsd().doubleValue();
        totalOpen = JavaConstant.getTwoPrecision(totalOpen);
        // BigDecimal tOpen = new BigDecimal(totalOpen);
        // tOpen = tOpen.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        map.put("posId", openShift.getPosId());
        map.put("openDate", openShift.getOpenTime());
        map.put("openCash", totalOpen);

        // get closeCash, closeDate from CloseShift
        CloseShift closeShift = closeShiftRepo.getCloseShift(userCode, JavaConstant.currentDate, posId);

        double _cashKhr = closeShift.getCashKhr().doubleValue() / JavaConstant.exchangeRate;
        double _cashUsd = closeShift.getCashUsd().doubleValue();
        double _creditCart = closeShift.getCreditCard().doubleValue();
        double _express = closeShift.getExpress().doubleValue();
        double _khqrAba = closeShift.getKhqrAba().doubleValue();
        double _khqrMnk = closeShift.getKhqrMnk().doubleValue();

        double totalClose = totalOpen + _cashKhr + _cashUsd + _creditCart + _express + _khqrAba + _khqrMnk;
      
        totalClose = JavaConstant.getTwoPrecision(totalClose);
        map.put("closeCash", totalClose);
        map.put("closeDate", closeShift.getCloseTime());

        // Sale summery
        SummeryCashierReport(id, posId,userCode);
        // payment summery
        paymentSummery(id, posId,userCode);
        // discount summery
        discountSummery(id, posId,userCode);
        return map;
    }

    public void discountSummery(int userId, String posId,String userCode) {
        List<Integer> listDiscount = new ArrayList<>();
        listDiscount.add(10);
        listDiscount.add(15);
        listDiscount.add(20);
        listDiscount.add(30);
        listDiscount.add(50);
        List<SummeryCashierReport> discount = new ArrayList<>();
        for (int i = 0; i < listDiscount.size(); i++) {
            int disQty = 0;
            String disStr = repoSaleDetail.totalQty(userId, JavaConstant.currentDate, listDiscount.get(i), posId,
                    JavaConstant.currentDate,userCode);
            if (disStr != null)
                disQty = Integer.valueOf(disStr);

            double disAmount = 0;
            String disAmountStr = repoSaleDetail.totalAmount(userId, JavaConstant.currentDate, listDiscount.get(i),
                    posId, JavaConstant.currentDate,userCode);
            if (disAmountStr != null)
                disAmount = Double.valueOf(disAmountStr);

            disAmount = JavaConstant.getTwoPrecision(disAmount);
            discount.add(new SummeryCashierReport(listDiscount.get(i) + "%", disQty, BigDecimal.valueOf(disAmount)));
        }

        map.put("discountSummery", discount);
    }

    public void paymentSummery(int userId, String posId,String userCode) {
        CloseShift dCloseShift = closeShiftRepo.getCloseShift(userCode, JavaConstant.currentDate, posId);
        int qtyUsd = 0;
        String qtyUsdStr = repoSale.sumQtySaledByUsd(userId, JavaConstant.currentDate, posId, JavaConstant.currentDate,userCode);
        if (qtyUsdStr != null)
            qtyUsd = Integer.valueOf(qtyUsdStr);


        double amountPayUsd = dCloseShift.getCashUsd().doubleValue();

        int qtyKhr = 0;
        String qtyKhrStr = repoSale.sumQtySaledByKhr(userId, JavaConstant.currentDate, posId, JavaConstant.currentDate,userCode);
        if (qtyKhrStr != null)
            qtyKhr = Integer.valueOf(qtyKhrStr);

        double amountPayKhr = dCloseShift.getCashKhr().doubleValue()/JavaConstant.exchangeRate;

        int qtyAba = 0;
        String qtyAbaStr = repoSale.totalCountQtyABA(userId, JavaConstant.currentDate, posId, JavaConstant.currentDate,userCode);
        if (qtyAbaStr != null)
            qtyAba = Integer.valueOf(qtyAbaStr);

        double amountAba = dCloseShift.getKhqrAba().doubleValue();

        int qtyMnk = 0;
        String qtyMnkStr = repoSale.totalCountQtyMNK(userId, JavaConstant.currentDate, posId, JavaConstant.currentDate,userCode);
        if (qtyMnkStr != null)
            qtyMnk = Integer.valueOf(qtyMnkStr);

        double amountMnk = dCloseShift.getKhqrMnk().doubleValue();
 
        int qtyExpress = 0;
        String qtyExpressStr = repoSale.totalCountQtyExpress(userId, JavaConstant.currentDate, posId,
                JavaConstant.currentDate,userCode);
        if (qtyExpressStr != null)
            qtyExpress = Integer.valueOf(qtyExpressStr);

        double amountExpress =  dCloseShift.getExpress().doubleValue();

        int qtyCredit = 0;
        String qtyCreditStr = repoSale.totalCountQtyCredit(userId, JavaConstant.currentDate, posId,
                JavaConstant.currentDate,userCode);
        if (qtyCreditStr != null)
            qtyCredit = Integer.valueOf(qtyCreditStr);

        double amountCredit = dCloseShift.getCreditCard().doubleValue();
 
        amountPayUsd = JavaConstant.getTwoPrecision(amountPayUsd);
        amountPayKhr = JavaConstant.getTwoPrecision(amountPayKhr);
        amountMnk = JavaConstant.getTwoPrecision(amountMnk);
        amountAba = JavaConstant.getTwoPrecision(amountAba);
        amountExpress = JavaConstant.getTwoPrecision(amountExpress);
        amountCredit = JavaConstant.getTwoPrecision(amountCredit);

        ArrayList<SummeryCashierReport> payment = new ArrayList<>();
        payment.add(new SummeryCashierReport("RED ANT EXPRESS", qtyExpress, BigDecimal.valueOf(amountExpress)));
        payment.add(new SummeryCashierReport("CASH (USD)", qtyUsd, BigDecimal.valueOf(amountPayUsd)));
        payment.add(new SummeryCashierReport("CASH (KHR)", qtyKhr, BigDecimal.valueOf(amountPayKhr)));
        payment.add(new SummeryCashierReport("KHQR-MNK", qtyMnk, BigDecimal.valueOf(amountMnk)));
        payment.add(new SummeryCashierReport("KHQR-ABA", qtyAba, BigDecimal.valueOf(amountAba)));
        payment.add(new SummeryCashierReport("ABA-CREDIT CART", qtyCredit, BigDecimal.valueOf(amountCredit)));
        map.put("summeryPayemnt", payment);
    }

    public void SummeryCashierReport(int userId, String posId,String userCode) {
        String paymentNoFirst = repoPay.getFirstPaymentNumber(userId, JavaConstant.currentDate);
        String paymentNoLast = repoPay.getLastPaymentNumber(userId, JavaConstant.currentDate);
        map.put("paymentNoFirst", paymentNoFirst);
        map.put("paymentNoLast", paymentNoLast);

        int qtyDiscount = 0;
        String qtyDiscountStr = repoSaleDetail.totalQtyDiscount(userId, JavaConstant.currentDate, posId,
                JavaConstant.currentDate,userCode);
        if (qtyDiscountStr != null)
            qtyDiscount = Integer.valueOf(qtyDiscountStr);

        double amountDiscount = 0.00;
        String listDiscountQty = repoSaleDetail.totalAmountDiscount(userId, JavaConstant.currentDate, posId,
                JavaConstant.currentDate,userCode);
        if (listDiscountQty != null)
            amountDiscount = Double.valueOf(listDiscountQty);

        int qtySale = 0;
        String qtySaleStr = repoSaleDetail.totalQtySale(userId, JavaConstant.currentDate, posId,
                JavaConstant.currentDate,userCode);
        if (qtySaleStr != null)
            qtySale = Integer.valueOf(qtySaleStr);

        double amount = 0;
        String amountStr = repoSaleDetail.totalAmount(userId, JavaConstant.currentDate, posId,
                JavaConstant.currentDate,userCode);
        if (amountStr != null)
            amount = Double.valueOf(amountStr);

        int returnQty = 0;
        String returnQtyStr = repoSaleDetail.totalReturnQty(userId, JavaConstant.currentDate, posId,
                JavaConstant.currentDate);
        if (returnQtyStr != null)
            returnQty = Integer.valueOf(returnQtyStr);

        double returnAmount = 0;
        String returnAmountStr = repoSaleDetail.totalReturnAmount(userId, JavaConstant.currentDate, posId,
                JavaConstant.currentDate);
        if (returnAmountStr != null)
            returnAmount = Double.valueOf(returnAmountStr);

        ArrayList<SummeryCashierReport> summery = new ArrayList<>();

    
        amount = JavaConstant.getTwoPrecision(amount);
        returnAmount = JavaConstant.getTwoPrecision(returnAmount);
        amountDiscount = JavaConstant.getTwoPrecision(amountDiscount);
        summery.add(new SummeryCashierReport("Sales", qtySale, BigDecimal.valueOf(amount)));
        summery.add(new SummeryCashierReport("Returns/Refunds", returnQty, BigDecimal.valueOf(returnAmount)));
        summery.add(new SummeryCashierReport("Disounts", qtyDiscount, BigDecimal.valueOf(amountDiscount)));
        map.put("SummerySale", summery);
    }
}
