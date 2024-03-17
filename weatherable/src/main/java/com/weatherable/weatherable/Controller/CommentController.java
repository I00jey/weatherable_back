package com.weatherable.weatherable.Controller;

import com.weatherable.weatherable.DTO.CommentDTO;
import com.weatherable.weatherable.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{codiId}")
    public List<CommentDTO> retrieveAllComment(@PathVariable Long codiId) throws Exception {
        return commentService.retrieveAllComment(codiId);
    }

    @GetMapping("/comment/{id}")
    public CommentDTO retrieveSingleComment(@PathVariable Long id) throws Exception {
        return commentService.retrieveSingleComment(id);
    }

    @PostMapping("")
    public String insertComment(@RequestBody CommentDTO commentDTO) throws Exception {
        commentService.insertComment(commentDTO);
        return "댓글 작성 완료";
    }

    @PutMapping("")
    public String updateComment(@RequestBody CommentDTO commentDTO) throws Exception {
        commentService.updateComment(commentDTO);
        return "댓글 수정 완료";
    }


}
