package com.ce.spring.sms.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String uploadFile(MultipartFile file, String key);
    byte[] downloadFile(String fileName);
    String deleteFile(String fileName);
}
