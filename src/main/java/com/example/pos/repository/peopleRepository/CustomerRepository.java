package com.example.pos.repository.peopleRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.pos.entity.people.Customer;
import com.example.pos.projections.customerProjection.CustomerProjection;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query(nativeQuery = true , value = "select count(*) from pos_customer")
    int countRecord();

    @Query(nativeQuery = true , value = "select pc.id,pc.cus_name ,pc.customer_id ,pc.email ,\r\n" + //
            "  pc.earning ,pc.contact ,pc.gender ,pc.nationality,pct.name,pc.customer_type_id \r\n" + //
            "  from pos_customer pc  \r\n" + //
            "  inner join pos_customer_type pct on pct.id = pc.customer_type_id \r\n" + //
            "  where pc.status = true and pc.is_deleted = false and\r\n" + //
            "  pct.status = true and pct.is_deleted = false")
    List<CustomerProjection> readCustomer();

}
