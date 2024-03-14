package com.weatherable.weatherable.Service;

import com.weatherable.weatherable.Repository.LookbookRepository;
import com.weatherable.weatherable.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LookbookService {

    @Autowired
    LookbookRepository lookbookRepository;

    public void deleteLookbook(Long id) {
        lookbookRepository.deletelookbook(id);
    }

}
