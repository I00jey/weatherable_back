package com.weatherable.weatherable.Service;

import com.weatherable.weatherable.Repository.CommentRepository;
import com.weatherable.weatherable.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;


    public String deleteComment(Long id) {
        commentRepository.deleteComment(id);
        return "삭제 완료";
    }


}
