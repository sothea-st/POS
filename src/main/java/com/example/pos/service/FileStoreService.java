package com.example.pos.service;

import com.example.pos.entity.FileStore;

import com.example.pos.repository.FileStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;


@Service
public class FileStoreService {
    @Autowired
    private FileStoreRepository fileStoreRepository;

//    public FileStore store(MultipartFile file) throws IOException {
////        File f = new File("asset\\product\\pic.txt");
////        byte[] bytes = Files.readAllBytes(f.toPath());
////        String fileName = StringUtils.cleanPath(f.getName());
////        FileStore FileStore = new FileStore(fileName, "text/plain", bytes);
//
////
////        Path path = Paths.get("assets\\product\\pic.txt");
////        String name = "pic.txt";
////        String originalFileName = "pic.txt";
////        String contentType = "text/plain";
////        byte[] content = Files.readAllBytes(path);
////        try {
////            content = Files.readAllBytes(path);
////        } catch (final IOException e) {
////        }
////        MultipartFile result = new MockMultipartFile(name,originalFileName, contentType, content);
////
////        String _name = StringUtils.cleanPath(result.getOriginalFilename());
////        FileStore f = new FileStore(_name,result.getContentType(),result.getBytes());
//
//
//
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        FileStore FileStore = new FileStore(fileName, file.getContentType(), file.getBytes());
//        return fileStoreRepository.save(FileStore);
//    }

    public byte[] getFile(String id) throws IOException {
        Optional<FileStore> fileDB = fileStoreRepository.findById(id);
        return fileDB.get().getData();
    }


    public Stream<FileStore> getAllFiles() {
        return fileStoreRepository.findAll().stream();
    }
}
