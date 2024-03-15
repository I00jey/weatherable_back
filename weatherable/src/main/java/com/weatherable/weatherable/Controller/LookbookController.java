package com.weatherable.weatherable.Controller;

import com.weatherable.weatherable.DTO.CodiDTO;
import com.weatherable.weatherable.Service.CodiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lookbook")
public class LookbookController {

    @Autowired
    CodiService lookbookService;


    @DeleteMapping("")
    public String deleteSingleLookbook(@RequestBody CodiDTO lookBookDTO) {
        Long id = lookBookDTO.getId();
        lookbookService.deleteLookbook(id);
        return "삭제 완료";
    }

}
