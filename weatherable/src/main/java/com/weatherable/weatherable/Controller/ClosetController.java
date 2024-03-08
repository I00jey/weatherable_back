package com.weatherable.weatherable.Controller;

import com.weatherable.weatherable.DTO.ClosetDTO;
import com.weatherable.weatherable.Entity.ClosetEntity;
import com.weatherable.weatherable.Service.ClosetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/closet")
public class ClosetController {

    @Autowired
    ClosetService closetService;



    @GetMapping("")
    public List<ClosetDTO> getClosetByUserid(@RequestParam Long userIndex) {
        List<ClosetDTO> result = closetService.getAllClothListByUserIndex(userIndex);
        System.out.println(result);
        return result;
    }

    @PostMapping("")
    public String insertCloth(@RequestBody ClosetEntity closetEntity) {
        String result = closetService.insertCloth(closetEntity);
        return result;
    }
}
