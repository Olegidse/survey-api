package com.oleg.surveyapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@Schema(description = "Список всех ответов пользователя на все вопросы")
@ToString
public class AnswerDetalizationDto {
    private List<MultipleAnswersWithQuestionDto> answers;
}
