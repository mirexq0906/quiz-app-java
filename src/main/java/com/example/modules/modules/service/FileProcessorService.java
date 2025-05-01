package com.example.modules.modules.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileProcessorService {

    void readFile(MultipartFile file);

}
