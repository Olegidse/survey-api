package com.oleg.surveyapi.service;

import com.oleg.surveyapi.data.entity.AnswerEntity;
import com.oleg.surveyapi.data.entity.PersonEntity;
import com.oleg.surveyapi.data.entity.QuestionEntity;
import com.oleg.surveyapi.data.entity.SurveyEntity;
import com.oleg.surveyapi.data.repository.AnswerRepository;
import com.oleg.surveyapi.data.repository.PersonRepository;
import com.oleg.surveyapi.data.repository.QuestionRepository;
import com.oleg.surveyapi.dto.AnswerDetalizationDto;
import com.oleg.surveyapi.dto.AnswerWithQuestionDto;
import com.oleg.surveyapi.dto.MultipleAnswersDto;
import com.oleg.surveyapi.dto.MultipleAnswersWithQuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final PersonRepository personRepository;
    private final QuestionRepository questionRepository;

    public void saveAnswers(MultipleAnswersDto multipleAnswersDto) {
        PersonEntity person = personRepository.getById(multipleAnswersDto.getPersonId());
        multipleAnswersDto.getAnswers().forEach(answerDto -> {
            AnswerEntity answer = new AnswerEntity();
            answer.setAnswer(answerDto.getAnswer());
            answer.setPerson(person);
            answer.setQuestion(questionRepository.getById(answerDto.getQuestionId()));
            answerRepository.save(answer);
        }
        );
    }


    public AnswerDetalizationDto getAllAnswers(Long id) {
        PersonEntity person = personRepository.getById(id);
        List<AnswerEntity> answerList = person.getAnswers();
        Map<SurveyEntity, List<AnswerWithQuestionDto>> surveyMap = new HashMap<>();
        answerList.forEach(answerEntity -> {
            QuestionEntity question = answerEntity.getQuestion();
            SurveyEntity survey = question.getSurvey();
            AnswerWithQuestionDto answerWithQuestionDto = new AnswerWithQuestionDto();
            answerWithQuestionDto.setAnswer(answerEntity.getAnswer());
            answerWithQuestionDto.setText(question.getText());
            answerWithQuestionDto.setType(question.getType());
            if (!surveyMap.containsKey(survey)) {
                surveyMap.put(survey, new ArrayList<>());
            }
            List<AnswerWithQuestionDto> answerWithQuestionDtoList = surveyMap.get(survey);
            answerWithQuestionDtoList.add(answerWithQuestionDto);
            surveyMap.put(survey, answerWithQuestionDtoList);
        });

        List<MultipleAnswersWithQuestionDto> multipleAnswersWithQuestionDtoList = new ArrayList<>();
        surveyMap.forEach((key, value) -> {
            MultipleAnswersWithQuestionDto multipleAnswersWithQuestionDto = new MultipleAnswersWithQuestionDto();

            multipleAnswersWithQuestionDto.setAnswers(value);
            multipleAnswersWithQuestionDto.setSurveyName(key.getName());

            multipleAnswersWithQuestionDtoList.add(multipleAnswersWithQuestionDto);
        });


        AnswerDetalizationDto answerDetalizationDto = new AnswerDetalizationDto();
        answerDetalizationDto.setAnswers(multipleAnswersWithQuestionDtoList);
        return answerDetalizationDto;
    }
}
