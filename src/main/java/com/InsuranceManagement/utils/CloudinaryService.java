package com.InsuranceManagement.utils;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Component;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Service
public class CloudinaryService {
    private static Dotenv dotenv = Dotenv.load();
    private static final String CLOUD_NAME = dotenv.get("CLOUD_NAME");
    private static final String API_KEY = dotenv.get("API_KEY");
    private static final String API_SECRET = dotenv.get("API_SECRET");

    private Cloudinary cloudinary;

    public CloudinaryService () {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", CLOUD_NAME, "api_key", API_KEY, "api_secret", API_SECRET));
    }

    public String uploadFile ( byte[] fileBytes ) throws IOException {
        Map uploadParams = ObjectUtils.asMap("resource_type", "raw", "overwrite", true);

        Map uploadResult = cloudinary.uploader().upload(fileBytes, uploadParams);
        return (String) uploadResult.get("secure_url");
    }

    public String uploadFile ( InputStream inputStream, String publicId ) throws IOException {
        Map uploadParams = ObjectUtils.asMap("resource_type", "raw", "public_id", publicId, "overwrite", true);

        Map uploadResult = cloudinary.uploader().upload(inputStream, uploadParams);
        return (String) uploadResult.get("secure_url");
    }
}