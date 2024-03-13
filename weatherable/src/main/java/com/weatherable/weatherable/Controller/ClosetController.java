package com.weatherable.weatherable.Controller;

import com.weatherable.weatherable.DTO.ClosetDTO;
import com.weatherable.weatherable.Entity.ClosetEntity;
import com.weatherable.weatherable.Service.ClosetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/closet")
public class ClosetController {

    @Autowired
    ClosetService closetService;



    @GetMapping("")
    public List<ClosetDTO> getClosetByUserid(@RequestParam Long userIndex) {
        List<ClosetDTO> result = closetService.getAllClothListByUserIndex(userIndex);
        return result;
    }

    @PostMapping("")
    public String insertCloth(@RequestBody ClosetDTO closetDTO) throws AccountNotFoundException {
        String result = closetService.insertCloth(closetDTO);
        return result;
    }

    @GetMapping("{id}")
    public ClosetDTO getSingleClosetById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        ClosetDTO closetDTO = closetService.retrieveClothById(id);
        return closetDTO;
    }

    @PatchMapping("{id}")
    public String updateSingleClosetById(@PathVariable Long id, @RequestBody ClosetDTO closetDTO) throws AccountNotFoundException {
        String result = closetService.updateCloth(id, closetDTO);
        return result;
    }

    @DeleteMapping("{id}")
    public String deleteSingleClosetById(@PathVariable Long id) {
        closetService.deleteClothById(id);
        return "삭제 완료";
    }
}
