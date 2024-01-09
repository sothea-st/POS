package com.example.pos.repository.peopleRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.entity.people.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query(nativeQuery = true , value = "select count(*) from pos_customer")
    int countRecord();
}
