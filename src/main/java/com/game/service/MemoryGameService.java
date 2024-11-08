package com.game.service;

import com.game.model.Card;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class MemoryGameService {

    private List<Card> cards;
    private Card firstSelectedCard;
    private Card secondSelectedCard;

    public MemoryGameService() {
        initializeGame();
    }

    public void initializeGame() {
        cards = new ArrayList<>();
        cards.addAll(Arrays.asList(
                new Card(1, "fa-apple", 'A'), new Card(2, "fa-ambulance", 'A'),
                new Card(3, "fa-bug", 'B'), new Card(4, "fa-bus", 'B'),
                new Card(5, "fa-cat", 'C'), new Card(6, "fa-carrot", 'C'),
                new Card(7, "fa-dog", 'D'), new Card(8, "fa-dove", 'D'),
                new Card(9, "fa-elephant", 'E'), new Card(10, "fa-envelope", 'E'),
                new Card(11, "fa-fish", 'F'), new Card(12, "fa-frog", 'F'),
                new Card(13, "fa-ghost", 'G'), new Card(14, "fa-guitar", 'G'),
                new Card(15, "fa-horse", 'H'), new Card(16, "fa-hat-cowboy", 'H')
                // ... include 16 unique icons here ...
        ));
        Collections.shuffle(cards);
        firstSelectedCard = null;
        secondSelectedCard = null;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void flipCard(int id) {
        Card card = cards.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (card != null && !card.isMatched()) {
            card.setFlipped(!card.isFlipped());
            if (firstSelectedCard == null) {
                firstSelectedCard = card;
            } else if (secondSelectedCard == null && card != firstSelectedCard) {
                secondSelectedCard = card;
                checkForMatch();
            }
        }
    }

    private void checkForMatch() {
        if (firstSelectedCard != null && secondSelectedCard != null) {
            if (firstSelectedCard.getLetter() == secondSelectedCard.getLetter()) {
                firstSelectedCard.setMatched(true);
                secondSelectedCard.setMatched(true);
            } else {
                firstSelectedCard.setFlipped(false);
                secondSelectedCard.setFlipped(false);
            }
            firstSelectedCard = null;
            secondSelectedCard = null;
        }
    }

    public boolean isGameComplete() {
        return cards.stream().allMatch(Card::isMatched);
    }

    public void resetGame() {
        initializeGame();
    }
}

