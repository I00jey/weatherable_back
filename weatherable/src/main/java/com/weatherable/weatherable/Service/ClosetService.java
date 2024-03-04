package com.weatherable.weatherable.Service;

import com.weatherable.weatherable.Entity.ClosetEntity;
import com.weatherable.weatherable.Repository.ClosetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClosetService {

    @Autowired
    ClosetRepository closetRepository;

    public List<ClosetEntity> getClosetList(String userid) {
        List<ClosetEntity> closetList = closetRepository.findClosetModelByUserid(userid);
        return closetList;
    }
}
