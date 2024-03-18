package com.weatherable.weatherable.Controller;

import com.weatherable.weatherable.DTO.ClosetDTO;
import com.weatherable.weatherable.Service.ClosetService;
import com.weatherable.weatherable.Service.S3Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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
        return result;
    }

    @PostMapping("")
    public String insertCloth(@RequestPart("closetDTO") ClosetDTO closetDTO) throws AccountNotFoundException {
        String result = closetService.insertCloth(closetDTO);
        return result;
    }

    @PostMapping("/image")
    public String uploadImage(@RequestParam("image") MultipartFile imageFile) throws IOException {
        String imageUrl = s3Upload.saveImageFile(imageFile);
        return imageUrl;
    }

    @GetMapping("/{id}")
    public ClosetDTO getSingleClosetById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        ClosetDTO closetDTO = closetService.retrieveClothById(id);
        return closetDTO;
    }

    @PutMapping("")
    public String updateSingleClosetById(@RequestBody ClosetDTO closetDTO, @RequestPart("imageFile") MultipartFile imageFile) throws AccountNotFoundException, IOException {
        if (!imageFile.isEmpty()) {
            String imagePath = s3Upload.saveImageFile(imageFile);
            closetDTO.setBigImagePath(imagePath);
        }
        String result = closetService.updateCloth(closetDTO);
        return result;
    }

    @PatchMapping("")
    public String toggleLike(@RequestBody ClosetDTO closetDTO) {
        if (closetDTO.isLiked()) {
            closetService.unlikeCloth(closetDTO.getId());
        } else {
            closetService.likeCloth(closetDTO.getId());
        }
        return "좋아요 토글 완료";
    }


    @DeleteMapping("")
    public String deleteSingleClosetById(@RequestBody ClosetDTO closetDTO) {
        Long id = closetDTO.getId();
        closetService.deleteCloth(id);
        return "삭제 완료";
    }
}
