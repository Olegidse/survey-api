package com.oleg.surveyapi.converter;

import com.oleg.surveyapi.data.entity.SurveyEntity;
import com.oleg.surveyapi.dto.SurveyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SurveyEntityToSurveyDtoConverter {
    public SurveyEntity convert(SurveyDto dto) {
        return new SurveyEntity()
                .setName(dto.getName())
                .setDescription(dto.getDescription())
                .setStartDate(dto.getStartDate())
                .setEndDate(dto.getEndDate());
    }


    public SurveyDto convert(SurveyEntity survey) {
        return new SurveyDto()
                .setName(survey.getName())
                .setDescription(survey.getDescription())
                .setStartDate(survey.getStartDate())
                .setEndDate(survey.getEndDate())
                .setSurveyId(survey.getSurveyId());
    }
}
