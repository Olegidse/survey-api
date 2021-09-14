package com.oleg.surveyapi.converter;

import com.oleg.surveyapi.data.entity.QuestionEntity;
import com.oleg.surveyapi.data.entity.SurveyEntity;
import com.oleg.surveyapi.dto.QuestionDto;
import com.oleg.surveyapi.dto.SurveyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionEntityToQuestionDtoConverter {
    public QuestionEntity convert(QuestionDto dto) {
        return new QuestionEntity()
                .setText(dto.getText())
                .setType(dto.getType());
    }

    public QuestionDto convert(QuestionEntity question) {
        return new QuestionDto()
                .setText(question.getText())
                .setType(question.getType())
                .setQuestionId(question.getQuestionId())
                .setSurveyId(question.getSurvey().getSurveyId());
    }
}

