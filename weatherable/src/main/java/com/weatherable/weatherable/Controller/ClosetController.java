package com.weatherable.weatherable.Controller;

import com.weatherable.weatherable.DTO.ClosetDTO;
import com.weatherable.weatherable.Service.ClosetService;
import com.weatherable.weatherable.Service.S3Upload;
import com.weatherable.weatherable.enums.DefaultRes;
import com.weatherable.weatherable.enums.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<DefaultRes<List<ClosetDTO>>> getClosetByUserid(@RequestParam Long userIndex) {
        List<ClosetDTO> result = closetService.getAllClothListByUserIndex(userIndex);
        return new ResponseEntity<>(
                DefaultRes.res(StatusCode.OK, "Closet fetch 완료", result),
                HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<DefaultRes<String>> insertCloth(@RequestPart("closetDTO") ClosetDTO closetDTO) throws AccountNotFoundException {  
        String result = closetService.insertCloth(closetDTO);
        return new ResponseEntity<>(
                DefaultRes.res(StatusCode.CREATED, result),
                HttpStatus.CREATED);
    }

    @PostMapping("/image")
    public String uploadImage(@RequestParam("image") MultipartFile imageFile) throws IOException {
        String imageUrl = s3Upload.saveImageFile(imageFile);
        return imageUrl;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefaultRes<ClosetDTO>> getSingleClosetById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        ClosetDTO closetDTO = closetService.retrieveClothById(id);
        return new ResponseEntity<>(
                DefaultRes.res(StatusCode.OK, "Cloth fetch 완료", closetDTO),
                HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<DefaultRes<String>> updateSingleClosetById(@RequestBody ClosetDTO closetDTO) throws AccountNotFoundException {
        String result = closetService.updateCloth(closetDTO);
        return new ResponseEntity<>(
                DefaultRes.res(StatusCode.OK, result),
                HttpStatus.OK);
    }

    @PatchMapping("")
    public ResponseEntity<DefaultRes<String>> toggleLike(@RequestBody ClosetDTO closetDTO) {
        if (closetDTO.isLiked()) {
            closetService.unlikeCloth(closetDTO.getId());
        } else {
            closetService.likeCloth(closetDTO.getId());
        }
        return new ResponseEntity<>(
                DefaultRes.res(StatusCode.OK, "좋아요 토글 완료"),
                HttpStatus.OK);
    }


    @DeleteMapping("")
    public ResponseEntity<DefaultRes<String>> deleteSingleClosetById(@RequestBody ClosetDTO closetDTO) {
        Long id = closetDTO.getId();
        closetService.deleteCloth(id);
        return new ResponseEntity<>(
                DefaultRes.res(StatusCode.OK, "삭제 완료"),
                HttpStatus.OK);
    }
}
