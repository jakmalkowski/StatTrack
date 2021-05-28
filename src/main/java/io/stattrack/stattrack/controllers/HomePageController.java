package io.stattrack.stattrack.controllers;

import io.stattrack.stattrack.models.UserRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomePageController {

    @GetMapping(value = "/")
    public String homePage(){
        return "homepage";
    }



}
