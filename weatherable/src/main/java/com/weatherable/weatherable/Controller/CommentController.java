package com.weatherable.weatherable.Controller;

import com.weatherable.weatherable.Service.ClosetService;
import com.weatherable.weatherable.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;


}
