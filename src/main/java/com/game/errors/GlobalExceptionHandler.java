package com.game.errors;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {


    // Meðhöndlun fyrir 404 Not Found
    @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(Exception ex, Model model) {
        model.addAttribute("errorMessage", "Síða fannst ekki.");
        return "error"; // Skilar á error.html
    }



    // Meðhöndlun fyrir ófyrirséðar villur
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGeneralError(Exception ex, Model model) {
        model.addAttribute("errorMessage", "Villa kom upp. Vinsamlegast reyndu aftur.");
        return "error"; // Skilar á error.html
    }
}
