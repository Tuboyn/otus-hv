package com.example.survey.model.dto;

import java.util.Map;

public record AnswersRequest(
        Map<Integer, String> answers
) {
}
