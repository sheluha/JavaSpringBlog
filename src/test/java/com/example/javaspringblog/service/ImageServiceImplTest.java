package com.example.javaspringblog.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {

    ImageService imageService = new ImageServiceImpl();

    private boolean findImage(String fileName){
        boolean[] result = {false};
        try(Stream<Path> files = Files.list(Paths.get("userImages"))) {
            files.forEach(f->{
                if(f.getFileName().toString().endsWith(fileName)){
                    result[0] = true;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result[0];
    }

    @Test
    void storeFileTest() throws IOException {
        String testFileName = "TestFile.jpg";
        String resultFileName = imageService.store(new MockMultipartFile("File name", testFileName,"text/plain",new byte[1]));
        assertTrue(findImage(testFileName));
        Files.deleteIfExists(Path.of("userImages/"+resultFileName));
    }

    @Test
    void loadTestFile() {
        assertNotNull(imageService.loadAsResource("TestLoadFile.jpg"));

    }

    @Test
    void deleteFileTest() throws IOException {
        File file = Files.createTempFile(Path.of("userImages"),"TestDeleteFile",".jpg").toFile();
        imageService.deleteImage(file.getName());
        assertFalse(findImage(file.getName()));
        assertThrows(RuntimeException.class,()->imageService.deleteImage(""));
    }
}