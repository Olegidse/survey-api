package com.oleg.surveyapi.service;

import com.oleg.surveyapi.converter.SurveyEntityToSurveyDtoConverter;
import com.oleg.surveyapi.data.entity.SurveyEntity;
import com.oleg.surveyapi.data.repository.SurveyRepository;
import com.oleg.surveyapi.dto.MultipleSurveysDto;
import com.oleg.surveyapi.dto.SurveyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final SurveyEntityToSurveyDtoConverter converter;

    public SurveyDto createSurvey(SurveyDto surveyDto) {
        SurveyEntity survey = surveyRepository.save(converter.convert(surveyDto));
        surveyDto.setSurveyId(survey.getSurveyId());
        return surveyDto;
    }

    public SurveyDto updateSurvey(SurveyDto surveyDto, Long id) {
        Optional<SurveyEntity> surveyOptional = surveyRepository.findById(id);
        if (surveyOptional.isPresent()) {

            SurveyEntity survey = surveyOptional.get();

            survey.setName(surveyDto.getName());
            survey.setDescription(surveyDto.getDescription());
            survey.setEndDate(surveyDto.getEndDate());

            return converter.convert(surveyRepository.save(survey));
        }
        else return new SurveyDto();
    }

    public void deleteSurvey(Long id) {
        Optional<SurveyEntity> surveyEntity = surveyRepository.findById(id);
        surveyEntity.ifPresent(surveyRepository::delete);
    }

    public boolean isActive(SurveyEntity survey) {
        Long current = System.currentTimeMillis();
        return survey.getStartDate() <= current && survey.getEndDate() >= current;
    }

    public MultipleSurveysDto getActive() {
        return new MultipleSurveysDto().setSurveys(surveyRepository.findAll()
                .stream()
                .filter(this::isActive)
                .map(converter::convert)
                .collect(Collectors.toList()));
    }
}
