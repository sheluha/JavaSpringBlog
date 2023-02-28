package com.example.javaspringblog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{

    private final Path uploadDirectory = Paths.get("userImages");

    @Override
    public String store(MultipartFile file){
        String fileName;
        try(InputStream inputStream = file.getInputStream()){
            fileName = UUID.randomUUID() + file.getOriginalFilename();
            Path filePath = uploadDirectory.resolve(fileName);
            Files.copy(inputStream,filePath,StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return fileName;
    }


    @Override
    public Resource loadAsResource(String filename){
        Path[] searchFile = new Path[1];
        try(Stream<Path> files = Files.list(uploadDirectory)) {
            files.forEach(file -> {
                if (file.getFileName().toString().equals(filename)) {
                    searchFile[0] = file;
                }
            });
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        if(searchFile[0] != null){
            try {
                return new UrlResource(searchFile[0].toUri());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public boolean deleteImage(String filename){
        try {
            return Files.deleteIfExists(Path.of("userImages/"+filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
