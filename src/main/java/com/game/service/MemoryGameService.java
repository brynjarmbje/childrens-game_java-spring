package com.game.service;

import com.game.entity.Question;
import com.game.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class MemoryGameService {

    @Autowired
    private QuestionService questionService;

    private static final int ANIMALS_THINGS_MIN_ID = 73;
    private static final int ANIMALS_THINGS_MAX_ID = 99;

    private List<Card> cards;
    private Card firstSelectedCard;
    private Card secondSelectedCard;

    public void initializeGame() {
        cards = new ArrayList<>();
        List<Question> questions = questionService.getQuestionsInRange(ANIMALS_THINGS_MIN_ID, ANIMALS_THINGS_MAX_ID);
        Map<String, List<Question>> letterGroups = new HashMap<>();

        for (Question question : questions) {
            String firstLetter = question.getCorrectAnswer().getFirstLetter();
            letterGroups.putIfAbsent(firstLetter, new ArrayList<>());
            letterGroups.get(firstLetter).add(question);
        }

        for (Map.Entry<String, List<Question>> entry : letterGroups.entrySet()) {
            List<Question> group = entry.getValue();
            if (group.size() >= 2) {
                for (int i = 0; i < 2; i++) { // Ensure exactly 2 cards per letter
                    Question q = group.get(i);
                    String imageUrl = "/getImage?id=" + q.getId();
                    cards.add(new Card(cards.size(), imageUrl, Character.toUpperCase(entry.getKey().charAt(0)), q.getId())); // Convert to uppercase
                }
            }
        }

        Collections.shuffle(cards);
        firstSelectedCard = null;
        secondSelectedCard = null;
    }


    private long generateUniqueRandomId(Set<Long> usedIds) {
        long randomId;
        do {
            randomId = ANIMALS_THINGS_MIN_ID + new Random().nextInt(ANIMALS_THINGS_MAX_ID - ANIMALS_THINGS_MIN_ID + 1);
        } while (usedIds.contains(randomId));
        usedIds.add(randomId);
        return randomId;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void flipCard(int id) {
        Card card = cards.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (card == null || card.isMatched() || card.isFlipped()) {
            return;
        }

        card.setFlipped(true);
        System.out.println("Card flipped: " + card.getId() + " (" + card.getLetter() + ")");

        if (firstSelectedCard == null) {
            firstSelectedCard = card;
            System.out.println("First card selected: " + firstSelectedCard.getId());
        } else if (secondSelectedCard == null) {
            secondSelectedCard = card;
            System.out.println("Second card selected: " + secondSelectedCard.getId());
            checkForMatch();
        }
    }

    private void checkForMatch() {
        if (firstSelectedCard != null && secondSelectedCard != null) {
            Card card1 = firstSelectedCard;
            Card card2 = secondSelectedCard;

            System.out.println("Checking match: " + card1.getId() + " (" + card1.getLetter() + ") vs "
                    + card2.getId() + " (" + card2.getLetter() + ")");

            if (card1.getLetter() == card2.getLetter()) {
                card1.setMatched(true);
                card2.setMatched(true);
                System.out.println("Match found! Cards locked: " + card1.getId() + ", " + card2.getId());
            } else {
                System.out.println("No match. Flipping back cards: " + card1.getId() + ", " + card2.getId());
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        card1.setFlipped(false);
                        card2.setFlipped(false);
                        System.out.println("Cards reset: " + card1.getId() + ", " + card2.getId());
                    }
                }, 1000);
            }

            firstSelectedCard = null;
            secondSelectedCard = null;
        }
    }

    public boolean isGameComplete() {
        return cards.stream().allMatch(Card::isMatched);
    }

    public void resetGame() {
        cards.clear();
        firstSelectedCard = null;
        secondSelectedCard = null;
        initializeGame();
    }


}
