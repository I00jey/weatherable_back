package com.weatherable.weatherable.Controller;

import com.weatherable.weatherable.DTO.CodiDTO;
import com.weatherable.weatherable.Service.CodiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/codi")
public class CodiController {

    @Autowired
    CodiService codiService;


    @DeleteMapping("")
    public String deleteCodi(@RequestBody CodiDTO codiDTO) {
        Long id = codiDTO.getId();
        codiService.deleteCodi(id);
        return "삭제 완료";
    }

    @GetMapping("")
    public List<CodiDTO> retrieveAllCodi() throws Exception {
        List<CodiDTO> codiDTOList = codiService.retrieveAllCodi();
        return codiDTOList;
    }

    @PostMapping("")
    public String insertCodi(CodiDTO codiDTO) throws AccountNotFoundException {
        codiService.createCodi(codiDTO);
        return "등록 완료";
    }

    @PutMapping("")
    public String updateCodi(CodiDTO codiDTO) throws AccountNotFoundException {
        codiService.updateCodi(codiDTO);
        return "수정 완료";
    }

    @GetMapping("/{id}")
    public CodiDTO retrieveSingleCodi(@PathVariable Long id) throws Exception {
        CodiDTO codiDTO = codiService.retrieveSingleCodi(id);
        return codiDTO;
    }


}
