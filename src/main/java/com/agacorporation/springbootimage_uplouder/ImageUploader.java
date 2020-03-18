package com.agacorporation.springbootimage_uplouder;

import com.agacorporation.springbootimage_uplouder.model.Image;
import com.agacorporation.springbootimage_uplouder.repo.ImageRepo;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageUploader {

    @Autowired
    private ImageRepo imageRepo;

    private Cloudinary cloudinary;

    private Map uploadResult=null;
    public ImageUploader(){
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dcchvnsqf",
                "api_key", "174487827877899",
                "api_secret", "uP5O2sWhCsW7eMvSeA9xIYe2Vyk"));
    }

    public String uploadFile(String path){
        File file = new File(path);

        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        } catch (IOException e) {
            //todo

        }
        return uploadResult.get("url").toString();

    }
    public void saveToDB(){
        imageRepo.save(new Image(uploadResult.get("url").toString()));

    }



}
