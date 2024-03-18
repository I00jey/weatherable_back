package com.weatherable.weatherable.Controller;

import com.weatherable.weatherable.DTO.ClosetDTO;
import com.weatherable.weatherable.DTO.CodiDTO;
import com.weatherable.weatherable.DTO.CodiDTOWithImage;
import com.weatherable.weatherable.DTO.CodiLikeDTO;
import com.weatherable.weatherable.Service.CodiLikeService;
import com.weatherable.weatherable.Service.CodiService;
import com.weatherable.weatherable.enums.DefaultRes;
import com.weatherable.weatherable.enums.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/codi")
public class CodiController {

    @Autowired
    CodiService codiService;

    @Autowired
    CodiLikeService codiLikeService;


    @DeleteMapping("")
    public ResponseEntity<DefaultRes<String>> deleteCodi(@RequestBody CodiDTO codiDTO) {
        Long id = codiDTO.getId();
        codiService.deleteCodi(id);
        return new ResponseEntity<>(
                DefaultRes.res(StatusCode.OK, "삭제 완료"),
                HttpStatus.OK);
    }

    @GetMapping("")
    public List<CodiDTOWithImage> retrieveAllCodi(@RequestParam Long userIndex) throws Exception {
        List<CodiDTOWithImage> codiDTOList = codiService.retrieveAllCodi(userIndex);
        return codiDTOList;
    }

    @GetMapping("/user/{id}")
    public List<CodiDTOWithImage> retrieveAllSomeonesCodi(@PathVariable Long id) throws Exception {
        List<CodiDTOWithImage> codiDTOList = codiService.retrieveSomeOnesCodi(id);
        return codiDTOList;
    }

    @PostMapping("")
    public String insertCodi(CodiDTO codiDTO) throws AccountNotFoundException {
        codiService.createCodi(codiDTO);
        return "등록 완료";
    }

    @PostMapping("/like")
    public String toggleLike(@RequestBody CodiLikeDTO codiLikeDTO) throws Exception {
        codiLikeService.likeToggle(codiLikeDTO);
        return "좋아요 완료";
    }

    @PutMapping("")
    public String updateCodi(CodiDTO codiDTO) throws AccountNotFoundException {
        codiService.updateCodi(codiDTO);
        return "수정 완료";
    }

    @GetMapping("/{id}")
    public CodiDTOWithImage retrieveSingleCodi(@PathVariable Long id, @RequestParam Long userIndex) throws Exception {
        CodiDTOWithImage codiDTO = codiService.retrieveSingleCodi(id, userIndex);
        return codiDTO;
    }


}
