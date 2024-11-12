package com.game.data;

import com.game.entity.Question;
import com.game.repository.GameRepository;

public class QuestionHandler(){

	public void makeQuestion(String connectionId, int type, int level, String audioQuestionBlobFilePath){
	
	Question dyraSpurning1 = new Question(connectionId, level, type, audioQuestionBlobFilePath);
	GameRepository.saveQuestion(dyraSpurning1);
	}
}
