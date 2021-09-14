package com.oleg.surveyapi.service;

import com.oleg.surveyapi.converter.QuestionEntityToQuestionDtoConverter;
import com.oleg.surveyapi.data.entity.QuestionEntity;
import com.oleg.surveyapi.data.entity.SurveyEntity;
import com.oleg.surveyapi.data.repository.QuestionRepository;
import com.oleg.surveyapi.data.repository.SurveyRepository;
import com.oleg.surveyapi.dto.MultipleQuestionsDto;
import com.oleg.surveyapi.dto.QuestionDto;
import com.oleg.surveyapi.dto.SurveyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;
    private final QuestionEntityToQuestionDtoConverter converter;

    public MultipleQuestionsDto getQuestions(Long id) {
        SurveyEntity survey = surveyRepository.getById(id);
        return new MultipleQuestionsDto().setQuestions(survey
                .getQuestions()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList()));
    }

    public QuestionDto addQuestion(QuestionDto questionDto, Long id) {
        SurveyEntity survey = surveyRepository.getById(id);
        QuestionEntity question = questionRepository.save(converter.convert(questionDto).setSurvey(survey));
        questionDto.setQuestionId(question.getQuestionId());
        questionDto.setSurveyId(survey.getSurveyId());
        return questionDto;
    }

    public QuestionDto updateQuestion(QuestionDto questionDto, Long id) {

        Optional<QuestionEntity> questionOptional = questionRepository.findById(id);
        if (questionOptional.isPresent()) {

            QuestionEntity question = questionOptional.get();

            question.setText(questionDto.getText());
            question.setType(questionDto.getType());

            return converter.convert(questionRepository.save(question));
        }
        else return new QuestionDto();
    }

    public void deleteQuestion(Long id) {
        Optional<QuestionEntity> questionEntity = questionRepository.findById(id);
        questionEntity.ifPresent(questionRepository::delete);
    }
}
