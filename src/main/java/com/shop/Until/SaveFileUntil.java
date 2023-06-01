package com.shop.Until;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class SaveFileUntil {
    private final Path root1 = Paths.get("src/main/resources/static/DefaultImg");

    public void save(MultipartFile file) {
        try {
            if (!Files.exists(root1)) {
                Files.createDirectory(root1);
            }
            Files.copy(file.getInputStream(), this.root1.resolve(file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final Path root = Paths.get(System.getProperty("user.dir")+"\\1_thucHanhLab\\lab\\src\\main\\resources\\static\\");
    //"D:\\STUDYYYYY\\Java5\\Project_Java5\\An_Phuc\\1_thucHanhLab\\1_thucHanhLab\\lab
    public void save(MultipartFile file, String path) {
        Path filePath = root.resolve(path);
        System.out.println("filePath : "+filePath.toString());
        try {
            if (!Files.exists(filePath)) {
                Files.createDirectory(filePath);
            }
            Files.copy(file.getInputStream(), filePath.resolve(file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
