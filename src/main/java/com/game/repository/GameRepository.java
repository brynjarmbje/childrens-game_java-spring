package com.game.repository;

import com.game.entity.Game;
import com.game.entity.Image;
import com.game.entity.Audio;
import com.game.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
	// get the audio for the question
	//public Audio getAudio(){};

	// get the image of the correct answer
	//public Image getCorrectAnswer(){};

	// save session to repository
	//public void saveSession(Session sessionToSave){
    }
}
