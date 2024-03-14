package com.weatherable.weatherable.Controller;

import com.weatherable.weatherable.DTO.LookBookDTO;
import com.weatherable.weatherable.Service.LookbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lookbook")
public class LookbookController {

    @Autowired
    LookbookService lookbookService;


    @DeleteMapping("")
    public String deleteSingleLookbook(@RequestBody LookBookDTO lookBookDTO) {
        Long id = lookBookDTO.getId();
        lookbookService.deleteLookbook(id);
        return "삭제 완료";
    }

}
