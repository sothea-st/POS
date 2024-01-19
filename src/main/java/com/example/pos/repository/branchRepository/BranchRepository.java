package com.example.pos.repository.branchRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pos.entity.branch.Branch;
import java.util.*;
@Repository
public interface BranchRepository  extends JpaRepository<Branch,Integer>{
    @Query(nativeQuery = true , value = "select * from pos_branch where status = true and is_deleted = false order by id desc")
    List<Branch> getAllBranch();


    @Query(nativeQuery = true , value = "select * from pos_branch where status = true and is_deleted = false and id = ?")
    Branch getBranchById(int id);
}
