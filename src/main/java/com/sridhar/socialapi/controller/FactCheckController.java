package com.sridhar.socialapi.controller;

import com.sridhar.socialapi.service.FackCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/social")
public class FactCheckController {

    private final FackCheckService fackCheckService;

    // check if the post is fake or not.
    @PostMapping("/factCheck/{postId}")
    public ResponseEntity<String> getTheFactChecked(@PathVariable Long postId) {
        return ResponseEntity.ok(fackCheckService.aiFactCheck(postId));
    }
}
