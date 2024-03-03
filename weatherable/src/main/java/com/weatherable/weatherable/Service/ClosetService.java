package com.weatherable.weatherable.Service;

import com.weatherable.weatherable.Model.ClosetModel;
import com.weatherable.weatherable.Repository.ClosetRepository;
import com.weatherable.weatherable.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClosetService {

    @Autowired
    ClosetRepository closetRepository;

    public List<ClosetModel> getClosetList(String userid) {
        List<ClosetModel> closetList = closetRepository.findClosetModelByUserid(userid);
        return closetList;
    }
}
