package com.InsuranceManagement.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class Helpers {

    private final CloudinaryService cloudinaryService;

    @Autowired
    public Helpers(CloudinaryService cloudinaryService) {
         this.cloudinaryService = cloudinaryService;
    }

    public static LocalDateTime parseDateTime(String dateTimeString) {
         LocalDate date = LocalDate.parse(dateTimeString);
            return date.atStartOfDay();
    }

    private String uploadFile(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            byte[] fileBytes = inputStream.readAllBytes();
            return cloudinaryService.uploadFile(fileBytes);
        }
    }

}
