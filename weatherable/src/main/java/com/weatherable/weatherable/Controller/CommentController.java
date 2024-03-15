package com.weatherable.weatherable.Controller;

import com.weatherable.weatherable.DTO.CommentDTO;
import com.weatherable.weatherable.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @DeleteMapping("")
    public String deleteSingleComment(@RequestBody CommentDTO commentDTO) {
        Long id = commentDTO.getId();
        String result = commentService.deleteComment(id);
        return result;
    }


}
