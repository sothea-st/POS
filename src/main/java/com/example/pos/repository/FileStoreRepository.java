package com.example.pos.repository;

import com.example.pos.entity.FileStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileStoreRepository extends JpaRepository<FileStore, String> {


}
