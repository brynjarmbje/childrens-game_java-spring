package com.game.controller;

import com.game.entity.Child;
import com.game.entity.Session;
import com.game.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/letters")
public class LettersGameController {

    @Autowired
    private SessionService sessionService;

    @PostMapping("/answer")
    public ResponseEntity<Void> processAnswer(@RequestParam boolean correct, HttpSession httpSession) {
        Child selectedChild = (Child) httpSession.getAttribute("selectedChild");
        if (selectedChild == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Session session = sessionService.findOrCreateSessionForChild(selectedChild);

        if (correct) {
            // Update progress only for correct answers
            sessionService.updateProgress(session);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/current-level")
    public ResponseEntity<Map<String, Integer>> getCurrentLevel(HttpSession httpSession) {
        Child selectedChild = (Child) httpSession.getAttribute("selectedChild");
        if (selectedChild == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Session session = sessionService.findOrCreateSessionForChild(selectedChild);
        Map<String, Integer> response = new HashMap<>();
        response.put("level", session.getLevel());

        return ResponseEntity.ok(response);
    }
}
