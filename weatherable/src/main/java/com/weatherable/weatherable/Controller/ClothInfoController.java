package com.weatherable.weatherable.Controller;

import com.weatherable.weatherable.Service.ClosetService;
import com.weatherable.weatherable.Service.ClothInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ClothInfoController {

    @Autowired
    ClothInfoService clothInfoService;


}
