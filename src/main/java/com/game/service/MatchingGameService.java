package com.game.service;

import com.game.model.Icon;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class MatchingGameService {

    private List<Icon> icons;
    private List<Character> letters;
    private final List<Icon> fullIconSet; // Store all 16 icons

    public MatchingGameService() {
        // Initialize the full set of 16 icons
        fullIconSet = List.of(
                new Icon(1, "fa-apple", 'A'),
                new Icon(2, "fa-ambulance", 'A'),
                new Icon(3, "fa-bug", 'B'),
                new Icon(4, "fa-bus", 'B'),
                new Icon(5, "fa-cat", 'C'),
                new Icon(6, "fa-carrot", 'C'),
                new Icon(7, "fa-dog", 'D'),
                new Icon(8, "fa-dove", 'D'),
                new Icon(9, "fa-elephant", 'E'),
                new Icon(10, "fa-envelope", 'E'),
                new Icon(11, "fa-fish", 'F'),
                new Icon(12, "fa-frog", 'F'),
                new Icon(13, "fa-ghost", 'G'),
                new Icon(14, "fa-guitar", 'G'),
                new Icon(15, "fa-horse", 'H'),
                new Icon(16, "fa-hat-cowboy", 'H')
        );

        initializeGame();
    }

    public void initializeGame() {
        icons = new ArrayList<>();
        letters = new ArrayList<>();

        // Randomly select 8 icons with unique starting letters
        List<Character> usedLetters = new ArrayList<>();
        Random random = new Random();

        while (icons.size() < 8) {
            // Randomly pick an icon from the full set
            Icon candidate = fullIconSet.get(random.nextInt(fullIconSet.size()));
            // Ensure the letter is unique in this game instance
            if (!usedLetters.contains(candidate.getLetter())) {
                icons.add(candidate);
                letters.add(candidate.getLetter());
                usedLetters.add(candidate.getLetter());
            }
        }

        // Shuffle icons and letters to randomize order
        Collections.shuffle(icons);
        Collections.shuffle(letters);
    }

    public List<Icon> getIcons() {
        return icons;
    }

    public List<Character> getLetters() {
        return letters;
    }

    public boolean checkMatch(int iconId, char letter) {
        Icon selectedIcon = icons.stream().filter(icon -> icon.getId() == iconId).findFirst().orElse(null);
        if (selectedIcon != null && selectedIcon.getLetter() == letter) {
            selectedIcon.setMatched(true);
            return true;
        }
        return false;
    }
}
