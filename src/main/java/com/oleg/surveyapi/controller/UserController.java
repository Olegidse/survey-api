package com.oleg.surveyapi.controller;

import com.oleg.surveyapi.dto.AnswerDetalizationDto;
import com.oleg.surveyapi.dto.MultipleAnswersDto;
import com.oleg.surveyapi.dto.MultipleQuestionsDto;
import com.oleg.surveyapi.dto.MultipleSurveysDto;
import com.oleg.surveyapi.service.AnswerService;
import com.oleg.surveyapi.service.QuestionService;
import com.oleg.surveyapi.service.SurveyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/survey-api")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final SurveyService surveyService;
    private final AnswerService answerService;
    private final QuestionService questionService;

    @Operation(summary = "Метод для получения активных опросов")
    @GetMapping(value = "/survey/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public MultipleSurveysDto getActiveSurveys() {
        return surveyService.getActive();
    }

    @Operation(summary = "Метод для занесения ответов внутри опроса")
    @PostMapping(value = "/answer/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveAnswers(@RequestBody MultipleAnswersDto multipleAnswersDto) {
        answerService.saveAnswers(multipleAnswersDto);
    }

    @Operation(summary = "Метод для получения вопросов внутри опроса")
    @GetMapping(value = "/survey/{surveyId}/questions", produces = MediaType.APPLICATION_JSON_VALUE)
    public MultipleQuestionsDto getQuestions(@PathVariable("surveyId") Long id) {
        return questionService.getQuestions(id);
    }

    @Operation(summary = "Метод для получения детализации по овтетам пользователя")
    @GetMapping(value = "/answer/all/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AnswerDetalizationDto getAllAnswers(@PathVariable("personId") Long id) {
        return answerService.getAllAnswers(id);
    }


}
