package com.example.pos.service.addImageService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.pos.entity.FileStore;
import com.example.pos.repository.FileStoreRepository;
import java.util.*;
@Service
public class AddImageService {
    @Autowired
    private FileStoreRepository repo;

    public String addImage(MultipartFile file) throws IOException {
        String fName = file.getOriginalFilename();
        FileStore f = new FileStore(fName, fName, file.getContentType(), file.getBytes());
        repo.save(f);
        return fName;
    }
    public byte[] getFile(String id) throws IOException {
        Optional<FileStore> fileDB = repo.findById(id);
        return fileDB.get().getData();
    }
}
