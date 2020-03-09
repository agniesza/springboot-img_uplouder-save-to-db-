package com.agacorporation.springbootimage_uplouder;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageUploader {
    private Cloudinary cloudinary;
    public ImageUploader(){
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dcchvnsqf",
                "api_key", "174487827877899",
                "api_secret", "uP5O2sWhCsW7eMvSeA9xIYe2Vyk"));
    }
    public static void main(String[] args) throws IOException {

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dcchvnsqf",
                "api_key", "174487827877899",
                "api_secret", "uP5O2sWhCsW7eMvSeA9xIYe2Vyk"));

        File file = new File("C:\\stream\\img.jpg");
        Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());

    }


}
