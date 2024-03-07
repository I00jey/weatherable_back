package com.weatherable.weatherable.Service;

import com.weatherable.weatherable.Repository.ClosetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClosetService {

    @Autowired
    ClosetRepository closetRepository;


}
