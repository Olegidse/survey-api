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
import com.oleg.surveyapi.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final PersonRepository personRepository;
    private final QuestionRepository questionRepository;

    public void saveAnswers(MultipleAnswersDto multipleAnswersDto) {
        Optional<PersonEntity> optionalPerson = personRepository.findPersonEntitiesByExternalId(multipleAnswersDto.getPersonId());
        PersonEntity person = new PersonEntity();
        if (optionalPerson.isPresent()) { person = optionalPerson.get();}
        else {
            person.setExternalId(multipleAnswersDto.getPersonId());
            person = personRepository.save(person);
        }


        PersonEntity finalPerson = person;
        multipleAnswersDto.getAnswers().forEach(answerDto -> {
            Optional<QuestionEntity> optionalQuestion = questionRepository.findById(answerDto.getQuestionId());
            if (optionalQuestion.isEmpty())
                throw new NotFoundException(String.format("Question with id = %d does not exist", answerDto.getQuestionId()));
            else {
                AnswerEntity answer = new AnswerEntity();
                answer.setAnswer(answerDto.getAnswer());
                answer.setPerson(finalPerson);
                answer.setQuestion(optionalQuestion.get());
                answerRepository.save(answer);
            }
        }
        );
    }


    public AnswerDetalizationDto getAllAnswers(Long id) {
        Optional<PersonEntity> optionalPerson = personRepository.findPersonEntitiesByExternalId(id);
        if (optionalPerson.isPresent()) {
            PersonEntity person = optionalPerson.get();
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
        } else {throw new NotFoundException("User with such id does not exist");}
    }
}
