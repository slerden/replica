package ru.itword.replica.model.entity;

import ru.itword.replica.model.enums.FileExtension;

import javax.persistence.*;

/**
 * Created by Itword on 22.07.2017.
 */
@Entity
@Table(name = "files")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "md5_hash")
    private String md5Hash;

    @Column(name = "file_name")
    private String name;

    @Column
    private Byte[] data;

    @Column(name = "file_extension")
    @Enumerated(value = EnumType.STRING)
    private FileExtension fileExtension;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMd5Hash() {
        return md5Hash;
    }

    public void setMd5Hash(String md5Hash) {
        this.md5Hash = md5Hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte[] getData() {
        return data;
    }

    public void setData(Byte[] data) {
        this.data = data;
    }

    public FileExtension getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(FileExtension fileExtension) {
        this.fileExtension = fileExtension;
    }
}
