package com.smallcc.rest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class VersionController {
    @GetMapping("/version")
    public String getVersion(@Value("${mobile.version}") String version){
        return version;
    }

}
