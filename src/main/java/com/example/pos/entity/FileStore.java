package com.example.pos.entity;



import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "pos_file")
public class FileStore {
//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Id
    private String id;

    private String name;

    private String type;

    @Lob
    private byte[] byteData;

    public FileStore() {
    }

    public FileStore(String id ,String name, String type, byte[] data) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.byteData = data;
    }

    public String getId() {
        return id;
    }
    public void setId(String id){this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return byteData;
    }

    public void setData(byte[] data) {
        this.byteData = data;
    }


}
