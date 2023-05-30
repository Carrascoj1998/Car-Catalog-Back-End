package com.astontech.RestFA.controllers;

import com.astontech.RestFA.exceptions.FieldNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRest {

    @GetMapping("/test")
    public String thisIsATest(){
        return "This Is A Test!";

    }

    @GetMapping("/exception")
    public void exceptionTest(){
        throw new FieldNotFoundException("dummy field");
    }

}
