package com.example.pos.repository;

import com.example.pos.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    boolean existsByContact(String contact);
    @Query(nativeQuery = true,value = "select * from pos_employee pe where status = true and is_deleted = false order by id desc")
    List<Employee> getEmployee();

    @Query(nativeQuery = true,value = "select * from pos_employee where status = true and is_deleted =  false and id =?")
    Employee getEmployeeById(int id);
}
