package com.weatherable.weatherable.Controller;

import com.weatherable.weatherable.Model.ClosetModel;
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

    @GetMapping("/")
    @ResponseBody
    public List<ClosetModel> getClosetList(@RequestParam String userid) {

        return closetService.getClosetList(userid);
    }
}
