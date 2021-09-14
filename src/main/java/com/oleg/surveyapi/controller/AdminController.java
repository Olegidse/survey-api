package com.oleg.surveyapi.controller;

import com.oleg.surveyapi.dto.QuestionDto;
import com.oleg.surveyapi.dto.SurveyDto;
import com.oleg.surveyapi.service.QuestionService;
import com.oleg.surveyapi.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("/admin")
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final SurveyService surveyService;
    private final QuestionService questionService;

    @Operation(summary = "Метод для cоздания опроса")
    @PostMapping(value = "/survey", consumes = MediaType.APPLICATION_JSON_VALUE)
    public SurveyDto createSurvey(@RequestBody SurveyDto surveyDto) {
        return surveyService.createSurvey(surveyDto);
    }

    @Operation(summary = "Метод для изменения опроса")
    @PutMapping(value = "/survey/{surveyId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public SurveyDto updateSurvey(@PathVariable("surveyId") Long id, @RequestBody SurveyDto surveyDto) {
        return surveyService.updateSurvey(surveyDto, id);
    }

    @Operation(summary = "Метод для удаления опроса")
    @DeleteMapping(value = "/survey/{surveyId}")
    public void deleteSurvey(@PathVariable("surveyId") Long id) {
        surveyService.deleteSurvey(id);
    }

    @Operation(summary = "Метод для добавления вопроса в опрос")
    @PostMapping(value = "/question/{surveyId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public QuestionDto addQuestion(@PathVariable("surveyId") Long id, @RequestBody QuestionDto questionDto) {
        return questionService.addQuestion(questionDto, id);
    }

    @Operation(summary = "Метод для изменения вопроса в опросе")
    @PutMapping(value = "/question/{questionId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public QuestionDto updateQuestion(@PathVariable("questionId") Long id, @RequestBody QuestionDto questionDto) {
        return questionService.updateQuestion(questionDto, id);
    }

    @Operation(summary = "Метод для удаления вопроса")
    @DeleteMapping(value = "/question/{questionId}")
    public void deleteQuestion(@PathVariable("questionId") Long id) {
        questionService.deleteQuestion(id);
    }

}
