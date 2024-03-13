package com.weatherable.weatherable.Controller;

import com.weatherable.weatherable.DTO.ClosetDTO;
import com.weatherable.weatherable.Entity.ClosetEntity;
import com.weatherable.weatherable.Service.ClosetService;
import com.weatherable.weatherable.Service.S3Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/closet")
public class ClosetController {

    @Autowired
    ClosetService closetService;

    @Autowired
    S3Upload s3Upload;


    @GetMapping("")
    public List<ClosetDTO> getClosetByUserid(@RequestParam Long userIndex) {
        List<ClosetDTO> result = closetService.getAllClothListByUserIndex(userIndex);
        System.out.println(result);
        return result;
    }

    @PostMapping("")
    public String insertCloth(@RequestPart("closetEntity") ClosetEntity closetEntity, @RequestPart("imageFile") MultipartFile imageFile) throws IOException {
        String imagePath = s3Upload.saveImageFile(imageFile);
        closetEntity.setBigImagePath(imagePath);
        String result = closetService.insertCloth(closetEntity);
        System.out.println(result);
        return result;
    }
}
