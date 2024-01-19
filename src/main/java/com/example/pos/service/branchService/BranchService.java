package com.example.pos.service.branchService;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.constant.JavaConstant;
import com.example.pos.entity.Category;
import com.example.pos.entity.branch.Branch;
import com.example.pos.repository.branchRepository.BranchRepository;
import com.example.pos.util.exception.customeException.JavaNotFoundByIdGiven;

import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpSession;

@Service
public class BranchService {
    @Autowired
    private BranchRepository repo;

    @Autowired
    private HttpSession session;

    public Branch addBranch(Branch b) {
        var createBy = session.getAttribute(JavaConstant.userId);
        Branch branch = new Branch();
        branch.setBranchNameEn(b.getBranchNameEn());
        branch.setBranchNameKh(b.getBranchNameKh());
        branch.setCreateBy((Integer)createBy);
        repo.save(branch);
        return branch;
    }


    public List<Branch> getAllBranch(){
        return repo.getAllBranch();
    }

    public Branch getBranchById(int id) {
        Branch branch = repo.getBranchById(id);
        if( branch == null ) throw new JavaNotFoundByIdGiven();
        return branch;
    }

    public void deleteBranch(int id, Branch b){
        Optional<Branch> data = repo.findById(id);
        Branch branch = data.get();
        branch.setStatus(b.isStatus());
        branch.setDeleted(b.isDeleted());
        repo.save(branch);
    }

    public Branch updateBranch(int id, Branch b){
        Optional<Branch> data = repo.findById(id);
        Branch branch = data.get();
        branch.setBranchNameEn(b.getBranchNameEn());
        branch.setBranchNameKh(b.getBranchNameKh());
        repo.save(branch);
        return branch;
    }

}
