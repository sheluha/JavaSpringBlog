package com.example.javaspringblog.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageService {
    String store(MultipartFile file);
    Resource loadAsResource(String filename);

    boolean deleteImage(String filename);
}
