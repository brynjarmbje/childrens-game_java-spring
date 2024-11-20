package com.game.service;

import com.game.entity.Child;
import com.game.entity.Session;
import com.game.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public void updateProgress(Session session) {
        session.setProgress(session.getProgress() + 1);

        // Level up every 5 games
        if (session.getProgress() % 5 == 0) {
            session.setLevel(Math.min(session.getLevel() + 1, 3)); // Max level is 3
            session.setProgress(0); // Reset progress for new level
        }

        sessionRepository.save(session);
    }

    /**
     * Finds an existing session for the child or creates a new one.
     */
    public Session findOrCreateSessionForChild(Child child) {
        // Check if a session already exists for this child
        Optional<Session> existingSession = sessionRepository.findByChildId(child.getId());

        if (existingSession.isPresent()) {
            return existingSession.get();
        }

        // Create a new session if none exists
        Session newSession = new Session();
        newSession.setId(UUID.randomUUID().toString());
        newSession.setChild(child);
        newSession.setLevel(1); // Start at level 1
        newSession.setProgress(0); // No progress yet

        return sessionRepository.save(newSession);
    }

    public List<Long> getQuestionIdsForLevel(int level, List<Long> allIds) {
        int partitionSize = allIds.size() / 3;
        switch (level) {
            case 1:
                return allIds.subList(0, partitionSize);
            case 2:
                return allIds.subList(0, 2 * partitionSize);
            case 3:
            default:
                return allIds;
        }
    }
}
