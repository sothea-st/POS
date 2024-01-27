package com.example.pos.service;

import com.example.pos.authentication.entity.User;
import com.example.pos.authentication.repositories.UserRepository;
import com.example.pos.components.JavaStorage;
import com.example.pos.constant.JavaConstant;
import com.example.pos.constant.JavaValidation;
import com.example.pos.entity.Employee;
import com.example.pos.entity.FileStore;
import com.example.pos.repository.EmployeeRepository;
import com.example.pos.repository.FileStoreRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private UserRepository userRepo;

    private final PasswordEncoder passwordEncoder;

    public EmployeeService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

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
        emp.setCreateBy(e.getCreateBy());

        if (file == null || file.isEmpty()) {
            emp.setImageName(JavaConstant.defaultNameImage);
        } else {
            // JavaStorage.storeImage(file); for save image to path assests/product in project
            String fileName = JavaStorage.setFileName(file.getOriginalFilename());
            emp.setImageName(fileName);

            // save information image to table pos_file
            // String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            FileStore f = new FileStore(fileName, fileName, file.getContentType(), file.getBytes());
            fileStore.save(f);
            emp.setImageName(fileName);
        }

        repo.save(emp);

        int userCount = userRepo.userCount();
        String userCountRow = "";
        userCount++;

        if( userCount < 10 ) {
            userCountRow="000"+userCount;
        } else if ( userCount < 100 ) {
            userCountRow="00"+userCount;
        } else if ( userCount < 1000 ) {
            userCountRow="0"+userCount;
        } else {
            userCountRow="0"+userCount;
        }

        User user = new User();
        user.setFullName(emp.getNameEn());
        JavaConstant password = new JavaConstant();
        user.setPassword(passwordEncoder.encode(password.getDefaultPassword()));
        user.setRole(null);
        user.setUserCode(userCountRow);
        user.setEmpId(emp.getId());
        user.setCreateBy(e.getCreateBy());
        userRepo.save(user);
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
        // var createdBy = session.getAttribute(JavaConstant.userId);
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
        emp.setCreateBy(e.getCreateBy());

        if (file == null) {
            emp.setImageName("default.jpg");
        } else {
            if (file.isEmpty()) {
                emp.setImageName("default.jpg");
            } else {
                JavaStorage.storeImage(file);
                emp.setImageName(JavaStorage.setFileName(Objects.requireNonNull(file.getOriginalFilename())));

                // save information image to table pos_file
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                FileStore f = new FileStore(JavaStorage.setFileName(file.getOriginalFilename()), fileName, file.getContentType(), file.getBytes());
                fileStore.save(f);
                emp.setImageName(JavaStorage.setFileName(file.getOriginalFilename()));

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
