package com.weatherable.weatherable.Controller;

import com.weatherable.weatherable.Service.BoardService;
import com.weatherable.weatherable.Service.ClosetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;


}
