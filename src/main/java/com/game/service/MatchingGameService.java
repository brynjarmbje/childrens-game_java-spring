package com.game.service;

import com.game.model.Icon;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class MatchingGameService {

    private List<Icon> icons;
    private List<Character> letters;
    private final List<Icon> fullIconSet; // Store all 16 icons

    public MatchingGameService() {
        // Initialize the full set of 16 icons with image paths
        fullIconSet = List.of(
                new Icon(1, "dyr/fill.jpg.png", 'F'),
                new Icon(2, "dyr/lest.jpg.png", 'L'),
                new Icon(3, "dyr/hundur.jpg.png", 'H'),
                new Icon(4, "dyr/morgaes.jpg.png", 'M'),
                new Icon(5, "dyr/moldvarpa.jpg.png", 'M'),
                new Icon(6, "hlutir/batur.jpg.png", 'B'),
                new Icon(7, "hlutir/bolti.jpg.png", 'B'),
                new Icon(8, "hlutir/flugvel.jpg.png", 'F'),
                new Icon(9, "dyr/kottur.jpg.png", 'K'),
                new Icon(10, "dyr/ugla.jpg.png", 'U'),
                new Icon(11, "hlutir/hattur.jpg.png", 'H'),
                new Icon(12, "hlutir/hjol.jpg.png", 'H'),
                new Icon(13, "dyr/ljon.jpg.png", 'L'),
                new Icon(14, "hlutir/glas.jpg.png", 'G'),
                new Icon(15, "dyr/tigrisdyr.jpg.png", 'T'),
                new Icon(16, "dyr/snakur.jpg.png", 'S')
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

