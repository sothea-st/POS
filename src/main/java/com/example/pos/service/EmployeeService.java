package com.example.pos.service;

import com.example.pos.components.JavaStorage;
import com.example.pos.constant.JavaConstant;
import com.example.pos.constant.JavaValidation;
import com.example.pos.entity.Employee;
import com.example.pos.entity.FileStore;
import com.example.pos.repository.EmployeeRepository;
import com.example.pos.repository.FileStoreRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repo;

    @Autowired
    private HttpSession session;

    @Autowired
    private FileStoreRepository fileStore;

    public Employee addEmployee(Employee e , MultipartFile file) throws IOException {
        var createdBy = session.getAttribute(JavaConstant.userId);

        boolean isExistContact = repo.existsByContact(e.getContact());
        JavaValidation.phoneAlreadyExist(isExistContact);

        Employee emp = new Employee();
        emp.setNameKh(e.getNameKh());
        emp.setNameEn(e.getNameEn());
        emp.setGender(e.getGender());
        emp.setDob(e.getDob());
        emp.setAddress(e.getAddress());
        emp.setContact(e.getContact());
        emp.setStartDate(e.getStartDate());
        emp.setCreateBy((Integer)createdBy);

        if (file == null) {
            emp.setFileName("default.jpg");
        } else {
            if (file.isEmpty()) {
                emp.setFileName("default.jpg");
            } else {
                JavaStorage.storeImage(file);
                emp.setFileName(JavaStorage.setFileName(Objects.requireNonNull(file.getOriginalFilename())));

                // save information image to table pos_file
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                FileStore f = new FileStore(JavaStorage.setFileName(file.getOriginalFilename()), fileName, file.getContentType(), file.getBytes());
                fileStore.save(f);
                emp.setFileName(JavaStorage.setFileName(file.getOriginalFilename()));

            }
        }

        repo.save(emp);
        return emp;
    }

    public List<Employee> getEmployee(){
        return repo.getEmployee();
    }

    public Employee getEmployeeById(int id) {
        return repo.getEmployeeById(id);
    }

    public void deleteEmployeeById(int id,Employee e){
        Optional<Employee> op = repo.findById(id);
        Employee emp = op.get();
        emp.setDeleted(e.isDeleted());
        emp.setStatus(e.isStatus());
        repo.save(emp);
    }

    public Employee updateEmployee(int id,Employee e,MultipartFile file) throws IOException {
        Optional<Employee> op = repo.findById(id);
        Employee emp = op.get();
        var createdBy = session.getAttribute(JavaConstant.userId);
        if(!e.getContact().equals(emp.getContact())) {
            boolean isExistContact = repo.existsByContact(e.getContact());
            JavaValidation.phoneAlreadyExist(isExistContact);
        }

        emp.setNameKh(e.getNameKh());
        emp.setNameEn(e.getNameEn());
        emp.setGender(e.getGender());
        emp.setDob(e.getDob());
        emp.setAddress(e.getAddress());
        emp.setContact(e.getContact());
        emp.setStartDate(e.getStartDate());
        emp.setCreateBy((Integer)createdBy);

        if (file == null) {
            emp.setFileName("default.jpg");
        } else {
            if (file.isEmpty()) {
                emp.setFileName("default.jpg");
            } else {
                JavaStorage.storeImage(file);
                emp.setFileName(JavaStorage.setFileName(Objects.requireNonNull(file.getOriginalFilename())));

                // save information image to table pos_file
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                FileStore f = new FileStore(JavaStorage.setFileName(file.getOriginalFilename()), fileName, file.getContentType(), file.getBytes());
                fileStore.save(f);
                emp.setFileName(JavaStorage.setFileName(file.getOriginalFilename()));

            }
        }

        repo.save(emp);
        return emp;
    }

    public byte[] getImageEmployee(String id){
        Optional<FileStore> data = fileStore.findById(id);
        return data.get().getData();
    }


}
