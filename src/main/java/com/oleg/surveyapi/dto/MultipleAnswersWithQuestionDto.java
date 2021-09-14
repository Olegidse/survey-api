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
@Schema(description = "Список ответов на вопросы с текстом опроса")
@ToString
public class MultipleAnswersWithQuestionDto {
    private String surveyName;
    private List<AnswerWithQuestionDto> answers;
}
