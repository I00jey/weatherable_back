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
    public String insertCloth(@RequestPart("closetDTO") ClosetDTO closetDTO, @RequestPart("imageFile") MultipartFile imageFile) throws IOException, AccountNotFoundException {
        String imagePath = s3Upload.saveImageFile(imageFile);
        closetDTO.setBigImagePath(imagePath);
        String result = closetService.insertCloth(closetDTO);
        return result;
    }

    @GetMapping("/{id}")
    public ClosetDTO getSingleClosetById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        ClosetDTO closetDTO = closetService.retrieveClothById(id);
        return closetDTO;
    }

    @PutMapping("/{id}")
    public String updateSingleClosetById(@PathVariable Long id, @RequestBody ClosetDTO closetDTO) throws AccountNotFoundException {
        String result = closetService.updateCloth(id, closetDTO);
        return result;
    }

    @PatchMapping("/{id}")
    public String toggleLike(@PathVariable Long id, @RequestBody ClosetDTO closetDTO) {
        if (closetDTO.isLike()) {
            closetService.unlikeCloth(id);
        } else {
            closetService.likeCloth(id);
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
