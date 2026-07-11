package com.example.survey.controller;

import com.example.survey.model.dto.AnswersRequest;
import com.example.survey.model.dto.Question;
import com.example.survey.model.dto.SurveyResponse;
import com.example.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getQuestions() {
        return ResponseEntity.ok(surveyService.getQuestions());
    }

    @PostMapping("/answers")
    public ResponseEntity<SurveyResponse> saveAnswers(@RequestBody AnswersRequest request) {
        return ResponseEntity.ok(surveyService.saveAnswers(request));
    }
}
