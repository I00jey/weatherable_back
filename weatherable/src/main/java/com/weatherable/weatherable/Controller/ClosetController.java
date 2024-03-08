package com.weatherable.weatherable.Controller;

import com.weatherable.weatherable.Entity.ClosetEntity;
import com.weatherable.weatherable.Repository.ClosetRepository;
import com.weatherable.weatherable.Service.ClosetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClosetController {

    @Autowired
    ClosetService closetService;



    @GetMapping("/")
    List<ClosetEntity> getClosetByUserid(@RequestParam Long userIndex) {
        System.out.println("sfsfsfefe"+userIndex);
        List<ClosetEntity> result = closetService.getAllList(userIndex);
        System.out.println(result);
        return result;
    }

}
