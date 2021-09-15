package com.oleg.surveyapi.service;

import com.oleg.surveyapi.converter.QuestionEntityToQuestionDtoConverter;
import com.oleg.surveyapi.data.entity.QuestionEntity;
import com.oleg.surveyapi.data.entity.SurveyEntity;
import com.oleg.surveyapi.data.repository.QuestionRepository;
import com.oleg.surveyapi.data.repository.SurveyRepository;
import com.oleg.surveyapi.dto.MultipleQuestionsDto;
import com.oleg.surveyapi.dto.QuestionDto;
import com.oleg.surveyapi.exception.NotFoundException;
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

        Optional<SurveyEntity> optionalSurvey = surveyRepository.findById(id);
        if (optionalSurvey.isEmpty()) throw new NotFoundException("Survey with such id does not exist");

        return new MultipleQuestionsDto().setQuestions(optionalSurvey
                .get()
                .getQuestions()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList()));
    }

    public QuestionDto addQuestion(QuestionDto questionDto, Long id) {

        Optional<SurveyEntity> optionalSurvey = surveyRepository.findById(id);
        if (optionalSurvey.isEmpty()) throw new NotFoundException("Survey with such id does not exist");

        QuestionEntity question = questionRepository.save(converter.convert(questionDto).setSurvey(optionalSurvey.get()));
        questionDto.setQuestionId(question.getQuestionId());
        questionDto.setSurveyId(optionalSurvey.get().getSurveyId());
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
        else throw new NotFoundException("Question with such id does not exist");
    }

    public void deleteQuestion(Long id) {
        Optional<QuestionEntity> questionEntity = questionRepository.findById(id);
        if (questionEntity.isEmpty()) throw new NotFoundException("Question with such id does not exist");
        questionRepository.delete(questionEntity.get());
    }
}
