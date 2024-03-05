package com.weatherable.weatherable.Controller;

import com.weatherable.weatherable.Entity.ClosetEntity;
import com.weatherable.weatherable.Service.ClosetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ClosetController {

    @Autowired
    ClosetService closetService;


}
