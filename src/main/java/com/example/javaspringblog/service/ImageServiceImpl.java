package com.example.javaspringblog.service;

import lombok.RequiredArgsConstructor;
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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{

    private final Path uploadDirectory = Paths.get("userImages");

    @Override
    public String store(MultipartFile file) throws IOException{

        String fileName;
        try(InputStream inputStream = file.getInputStream()){
            fileName = UUID.randomUUID() + file.getOriginalFilename();
            Path filePath = uploadDirectory.resolve(fileName);


            Files.copy(inputStream,filePath,StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            throw new IOException("File erroe",e);
        }
        return fileName;
    }

    private Path searchFile;
    
    @Override
    public Resource loadAsResource(String filename) throws IOException{
        searchFile = null;
        Files.list(uploadDirectory).forEach(file ->{
            if(file.getFileName().toString().equals(filename)){
                searchFile = file;
            }
        });
        if(searchFile != null){
            return new UrlResource(searchFile.toUri());
        }
        return null;
    }

    @Override
    public boolean deleteImage(String filename) throws IOException {
       return Files.deleteIfExists(Path.of("userImages/"+filename));
    }
}
