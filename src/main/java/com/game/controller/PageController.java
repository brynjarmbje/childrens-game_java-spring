package com.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/one")
    public String showOnePage() {
        return "one"; // This maps to src/main/resources/templates/one.html
    }
}
