package com.finartz.technicaltask.flightagency;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class HystrixController {

    @GetMapping("/message")
    public String message() {

        return "Flight Agency service is not available at the moment. Please try again later.";

    }

}
