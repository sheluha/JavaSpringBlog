package com.example.javaspringblog.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface ImageService {
    void store(MultipartFile file) throws IOException;
    Resource loadAsResource(String filename) throws IOException;
}
