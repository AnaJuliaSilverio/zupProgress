package com.zup.zupProgress.controllers;

import com.zup.zupProgress.dto.LeadershipDTO;
import com.zup.zupProgress.services.LeadershipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leadership")
public class LeadershipController {
    @Autowired
    private LeadershipService leadershipService;
    @PostMapping
    public ResponseEntity<LeadershipDTO> createLeadership(@RequestBody @Valid LeadershipDTO leadershipDTO){
        LeadershipDTO leadership = leadershipService.createLeadership(leadershipDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(leadership);
    }
    @GetMapping
    public ResponseEntity<List<LeadershipDTO>> getAllLeadership(){
        List<LeadershipDTO> allLeadership = leadershipService.getAllLeadership();
        return ResponseEntity.ok(allLeadership);
    }

}
