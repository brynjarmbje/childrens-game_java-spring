package com.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/one")
    public String showOnePage() {
        return "one"; // This maps to src/main/resources/templates/one.html
    }

    @GetMapping("/numbers")
    public String showNumbersGamePage() {
        return "numbers"; // This should match the name of your Thymeleaf HTML template (numbers.html)
    }
}
