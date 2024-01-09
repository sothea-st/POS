package com.example.pos.service;

import com.example.pos.authentication.repositories.UserRepository;
import com.example.pos.constant.JavaConstant;
import com.example.pos.entity.Company;
import com.example.pos.entity.Import;
import com.example.pos.entity.ImportDetail;
import com.example.pos.entity.Sale;
import com.example.pos.entity.SaleDetail;
import com.example.pos.entity.payment.Payment;
import com.example.pos.entity.people.Customer;
import com.example.pos.entity.projection.SaleDetailProjection;
import com.example.pos.repository.ImportDetailRepository;
import com.example.pos.repository.SaleDetailsRepository;
import com.example.pos.repository.SaleRepository;
import com.example.pos.repository.companyRepository.CompanyRepository;
import com.example.pos.repository.paymentRepository.PaymentRepository;
import com.example.pos.repository.peopleRepository.CustomerRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    private PaymentRepository payRepo;

    @Autowired
    private CompanyRepository repoCompany;

    @Autowired
    private UserRepository repoUser;

    @Autowired
    private CustomerRepository cusRepo;

    public HashMap<String, Object> saleProduct(Sale s, Customer c) {
        var createBy = session.getAttribute(JavaConstant.userId);
        int userId  = (Integer)createBy;
        HashMap<String, Object> map = new HashMap<>();

        Sale sale = new Sale();
        sale.setEmpId(s.getEmpId());
        sale.setSaleDate(s.getSaleDate());
        sale.setDiscount(s.getDiscount());
        sale.setTotalKhr(s.getTotalKhr());
        sale.setTotalUsd(s.getTotalUsd());
        sale.setCreateBy(userId);
        repo.save(sale);

        int saleId = sale.getId();
        List<SaleDetail> details = s.getDataSale();

        for (int i = 0; i < details.size(); i++) {
            var detail = details.get(i);
            int productId = detail.getProductId();
            int qtyNew = detail.getQty();

            SaleDetail dataDetail = new SaleDetail();
            dataDetail.setSaleId(saleId);
            dataDetail.setProductId(productId);
            dataDetail.setQty(qtyNew);
            dataDetail.setPrice(detail.getPrice());
            dataDetail.setAmount(detail.getAmount());
            dataDetail.setDiscount(detail.getDiscount());
            dataDetail.setCreateBy((Integer) createBy);
            repoDetail.save(dataDetail);

            ImportDetail getQtyOld = repoImp.getDataImportDetail(productId);
            int qtyOld = getQtyOld.getQtyOld();
            int qty = qtyOld - qtyNew;
            Optional<ImportDetail> getImportDetail = repoImp.findByImpId(productId);
            ImportDetail obj = getImportDetail.get();
            obj.setQtyOld(qty);
            repoImp.save(obj);
        }

        // save payment
        Payment p = s.getDataPay();
        int count = payRepo.countRecord();
        count++;
        String paymentNo = paymentNo(count);
        addPayment(paymentNo,s.getId(),p,userId);
        
        Customer cus = s.getCustomer();
        if( cus != null ) {
            addCustomer(cus);
        }

        Company companyInfo = repoCompany.getInfoCompany();
        map.put("companyName", companyInfo.getCompanyName());
        map.put("companyContact", companyInfo.getContact());
        map.put("companyAddress", companyInfo.getAddress());
        map.put("companyLogo", companyInfo.getPhoto());
        String empName = repoUser.getNameEmp((Integer) createBy);
        map.put("empName", empName);
        map.put("saleDate", s.getSaleDate());
        map.put("invoidNO", paymentNo);
        map.put("totalUsd", s.getTotalUsd());
        map.put("totalKhr", s.getTotalKhr());
        map.put("receiveUsd", p.getReceiveUsd());
        map.put("receiveKhr", p.getReceiveKhr());
        map.put("changeUSd", p.getChangeUsd());
        map.put("changeKhr", p.getChangeKhr());
        List<SaleDetailProjection> listProjection = repoDetail.getDataDetail(saleId);
        map.put("saleDetails", listProjection);
        return map;
    }

    public void addCustomer(Customer cus) {
        String cusId = "";
        int countId = cusRepo.countRecord();
        countId++;
        if (countId < 10) {
            cusId = "000" + countId;
        } else if (countId < 100) {
            cusId = "00" + countId;
        } else if (countId < 1000) {
            cusId = "0" + countId;
        } else {
            cusId = "" + countId;
        }

        Customer cusData = new Customer();
        cusData.setCusName(cus.getCusName());
        cusData.setContact(cus.getContact());
        cusData.setEarning(cus.getEarning());
        cusData.setEmail(cus.getEmail());
        cusData.setCoupon(cus.getCoupon());
        cusData.setGender(cus.getGender());
        cusData.setNationality(cus.getNationality());
        cusData.setCustomerId(cusId);
        cusRepo.save(cusData);
    }

    public void addPayment(String paymentNo, int saleId,Payment p , int createBy){
        Payment data = new Payment();
        data.setPaymentNo(paymentNo);
        data.setSaleId(saleId);
        // data.setTotalUsd(p.getTotalUsd());
        // data.setTotalKhr(p.getTotalKhr());
        data.setReceiveKhr(p.getReceiveKhr());
        data.setReceiveUsd(p.getReceiveUsd());
        data.setRemainingKhr(p.getRemainingKhr());
        data.setRemainingUsd(p.getRemainingUsd());
        data.setChangeKhr(p.getChangeKhr());
        data.setChangeUsd(p.getChangeUsd());
        data.setPaymentType(p.getPaymentType());
        data.setCustomerTypeId(p.getCustomerTypeId());
        data.setSourceId(p.getSourceId());
        data.setCreateBy(createBy);
        payRepo.save(data);
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

}
