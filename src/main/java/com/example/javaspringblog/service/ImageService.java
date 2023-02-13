package com.example.javaspringblog.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface ImageService {
    String store(MultipartFile file) throws IOException;
    Resource loadAsResource(String filename) throws IOException;

    boolean deleteImage(String filename) throws IOException;
}
