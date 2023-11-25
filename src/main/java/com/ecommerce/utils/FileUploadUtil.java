package com.ecommerce.utils;

import java.io.*;
import java.nio.file.*;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            System.out.println(fileName+filePath);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            //throw defined business exception if you want to throw IOException try to catch it in the above layer and then throw your own exception [abdelkarim]
            //use logger to log the exception very useful for tracing on the production env to know the issue
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
}