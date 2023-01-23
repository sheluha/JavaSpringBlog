package com.example.javaspringblog.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageServiceImpl implements ImageService{

    private final Path uploadDirectory = Paths.get("userImages");

    @Override
    public void store(MultipartFile file) throws IOException{
        try(InputStream inputStream = file.getInputStream()){
            Path filePath = uploadDirectory.resolve(file.getOriginalFilename());
            Files.copy(inputStream,filePath,StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            throw new IOException("File erroe",e);
        }
    }

    private Path searchFile;
    @Override
    public Resource loadAsResource(String filename) throws IOException{
        searchFile = null;
        Files.list(uploadDirectory).forEach(file ->{
            if(file.getFileName().toString().equals(filename)){
                searchFile = file;
                return;
            }
        });
        if(searchFile != null){
            return new UrlResource(searchFile.toUri());
        }
        return null;
    }
}
