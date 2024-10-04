package com.example.file.Controller;

import com.example.file.Entity.FileEntity;

import com.example.file.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController


public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping("/addFile")
    public FileEntity addFile(@RequestParam("filepath") String filepath) throws IOException {
        return fileService.addFile(filepath);
    }

    @GetMapping("/getFile/{id}")
    public FileEntity getFileById(@PathVariable int id) {
        return fileService.getFileDetails(id);
    }

    @GetMapping("/getFiles")
    public List<FileEntity> getFiles() {
        return fileService.getAllFiles();
    }

    @DeleteMapping("/deleteFile/{id}")
    public String deleteFile(@PathVariable int id) {
        return fileService.deleteFile(id);
    }


}
