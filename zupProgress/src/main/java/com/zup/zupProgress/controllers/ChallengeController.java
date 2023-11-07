package com.zup.zupProgress.controllers;

import com.zup.zupProgress.dto.ChallengeDTO;
import com.zup.zupProgress.services.ChallengeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/challenge")
@CrossOrigin("*")
public class ChallengeController {
    @Autowired
    private ChallengeService challengeService;

    @PostMapping
    public ResponseEntity<ChallengeDTO> create(@RequestBody @Valid ChallengeDTO dto, UriComponentsBuilder uriBuilder){
        ChallengeDTO challenge = challengeService.createChallenge(dto);
        URI address = uriBuilder.path("/challenge").buildAndExpand().toUri();
        return ResponseEntity.created(address).body(challenge);
    }
    @GetMapping("/{name}")
    public ResponseEntity<ChallengeDTO> searchChallenge(@PathVariable @NotNull String name){
        ChallengeDTO challenge = challengeService.getByName(name);
        return ResponseEntity.ok(challenge);
    }
    @GetMapping
    public List<String> getChallengeTitle(){
        return challengeService.getAllChallengeTitle();
    }
}
