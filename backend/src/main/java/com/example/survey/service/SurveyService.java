package com.example.survey.service;

import com.example.survey.model.dto.AnswersRequest;
import com.example.survey.model.dto.Question;
import com.example.survey.model.dto.SurveyResponse;
import com.example.survey.model.entity.Answer;
import com.example.survey.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final AnswerRepository answerRepository;

    private static final List<Question> QUESTIONS = List.of(
            new Question(1, "Как вас зовут?"),
            new Question(2, "Ваш возраст?"),
            new Question(3, "Ваш город?"),
            new Question(4, "Ваша профессия?"),
            new Question(5, "Ваше хобби?")
    );

    public List<Question> getQuestions() {
        return QUESTIONS;
    }

    public SurveyResponse saveAnswers(AnswersRequest request) {
        Map<Integer, String> answersMap = request.answers();
        List<Answer> answers = answersMap.entrySet().stream()
                .map(entry -> new Answer(null, entry.getKey(), entry.getValue()))
                .toList();
        answerRepository.saveAll(answers);
        return new SurveyResponse("success", "Answers saved");
    }
}
