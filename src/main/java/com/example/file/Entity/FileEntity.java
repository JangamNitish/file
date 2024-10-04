package com.example.file.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    Long fileSize;
    String fileName;
    Long TotalSpace;
    String type;
    String fileLocation;
    Date creationDate;
    Date lastModifiedDate;

    @Lob
    byte[] fileData;


}
