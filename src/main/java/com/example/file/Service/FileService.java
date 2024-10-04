package com.example.file.Service;

import com.example.file.Entity.FileEntity;
import com.example.file.Repo.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    FileRepo fileRepo;



    public FileEntity addFile(String filepath) throws IOException {
        File file = new File(filepath);


        if (!file.exists()) {
            throw new IOException("File not found at the provided path: " + filepath);
        }

        FileEntity fileEntity = new FileEntity();
        int dotIndex = filepath.lastIndexOf('.');
        fileEntity.setFileName(file.getName());
        fileEntity.setFileSize(file.length());
        fileEntity.setFileData(Files.readAllBytes(file.toPath()));
        fileEntity.setTotalSpace((file.getTotalSpace()));
        fileEntity.setType(filepath.substring(dotIndex));
        Path fileNioPath = file.toPath();
        BasicFileAttributes attrs = Files.readAttributes(fileNioPath, BasicFileAttributes.class);

        fileEntity.setCreationDate(new Date(attrs.creationTime().toMillis()));

        fileEntity.setLastModifiedDate(new Date(attrs.lastModifiedTime().toMillis()));

        fileEntity.setFileLocation(file.getAbsolutePath());

        return fileRepo.save(fileEntity);

    }


    public List<FileEntity> getAllFiles() {
        return fileRepo.findAll();
    }

    public FileEntity getFileDetails(int id) {
        Optional<FileEntity> fileEntityOptional = fileRepo.findById(id);
        return fileEntityOptional.orElse(null);
    }

    public String deleteFile(int id) {
        return "File Deleted" + id;
    }


}
