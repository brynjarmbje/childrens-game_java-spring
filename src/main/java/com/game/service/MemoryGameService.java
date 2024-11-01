package com.game.service;

import com.game.entity.Card;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemoryGameService {
    private List<Card> cards;
    private Card firstFlippedCard;

    public MemoryGameService() {
        initializeGame();
    }

    public void initializeGame() {
        cards = new ArrayList<>();
        firstFlippedCard = null;

        // Add pairs with sample images and letters
        cards.add(new Card(1, "apple.png", 'A'));
        cards.add(new Card(2, "ant.png", 'A'));
        cards.add(new Card(3, "banana.png", 'B'));
        cards.add(new Card(4, "bird.png", 'B'));
        cards.add(new Card(5, "cat.png", 'C'));
        cards.add(new Card(6, "car.png", 'C'));
        // Repeat to reach 12 cards, or randomly assign letters and images

        // Shuffle cards for randomness
        java.util.Collections.shuffle(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public String flipCard(int id) {
        Card selectedCard = cards.stream().filter(card -> card.getId() == id).findFirst().orElse(null);

        if (selectedCard == null || selectedCard.isLocked() || selectedCard.isFlipped()) {
            return "Invalid card selection.";
        }

        selectedCard.setFlipped(true);

        if (firstFlippedCard == null) {
            firstFlippedCard = selectedCard;
            return "First card flipped.";
        } else {
            if (firstFlippedCard.getLetter() == selectedCard.getLetter()) {
                selectedCard.setLocked(true);
                firstFlippedCard.setLocked(true);
                firstFlippedCard = null;
                return "Match found!";
            } else {
                firstFlippedCard.setFlipped(false);
                selectedCard.setFlipped(false);
                firstFlippedCard = null;
                return "No match, try again.";
            }
        }
    }
}
