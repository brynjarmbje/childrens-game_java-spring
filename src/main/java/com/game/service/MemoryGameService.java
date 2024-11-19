package com.game.service;

import com.game.entity.Question;
import com.game.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        Map<String, List<Question>> letterGroups = new HashMap<>();
        Set<Long> usedIds = new HashSet<>();

        // Create pairs for cards using random IDs
        while (cards.size() < 16) {
            long randomId = generateUniqueRandomId(usedIds);
            Question question = questionService.getQuestionById(randomId);

            if (question != null && question.getCorrectAnswer() != null) {
                String firstLetter = question.getCorrectAnswer().getFirstLetter();
                letterGroups.putIfAbsent(firstLetter, new ArrayList<>());
                letterGroups.get(firstLetter).add(question);

                // Only add if we have a pair (2 cards)
                if (letterGroups.get(firstLetter).size() == 2 || letterGroups.get(firstLetter).size() == 4) {
                    for (Question q : letterGroups.get(firstLetter)) {
                        String imageUrl = "/getImage?id=" + q.getId(); // Generate image URL
                        cards.add(new Card(cards.size(), imageUrl, firstLetter.charAt(0)));
                    }
                    // Reset list for this letter
                    letterGroups.remove(firstLetter);
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
        if (card != null && !card.isMatched() && !card.isFlipped()) {
            card.setFlipped(true);

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
                // Match found
                firstSelectedCard.setMatched(true);
                secondSelectedCard.setMatched(true);
            } else {
                // No match, flip back after delay
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        firstSelectedCard.setFlipped(false);
                        secondSelectedCard.setFlipped(false);
                        firstSelectedCard = null;
                        secondSelectedCard = null;
                    }
                }, 1000); // 1-second delay
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
