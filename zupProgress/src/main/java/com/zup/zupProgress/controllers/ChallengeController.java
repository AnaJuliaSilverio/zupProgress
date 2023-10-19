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

@RestController
@RequestMapping("/challenge")
public class ChallengeController {
    @Autowired
    private ChallengeService repository;
    @PostMapping
    public ResponseEntity<ChallengeDTO> create(@RequestBody @Valid ChallengeDTO dto, UriComponentsBuilder uriBuilder){
        ChallengeDTO challenge = repository.createChallenge(dto);
        URI address = uriBuilder.path("/challenge").buildAndExpand().toUri();
        return ResponseEntity.created(address).body(challenge);
    }
    @GetMapping("/{name}")
    public ResponseEntity<ChallengeDTO> searchChallenge(@PathVariable @NotNull String name){
        ChallengeDTO challenge = repository.getByName(name);
        return ResponseEntity.ok(challenge);
    }
}
