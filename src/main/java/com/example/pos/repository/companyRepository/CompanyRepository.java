package com.example.pos.repository.companyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.pos.entity.Company;
import java.util.*;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {

 
     @Query(nativeQuery = true , value = "select * from pos_company pc where status = true and is_deleted = false")
     List<Company> getCompany();

     @Query(nativeQuery = true , value = "select * from pos_company pc where status = true and is_deleted = false and id = ?")
     Company getCompanyById(int id);

     @Query(nativeQuery = true , value = "select * from pos_company where status = true and is_deleted = false")
     Company getInfoCompany();

}
