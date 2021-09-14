package com.oleg.surveyapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Schema(description = "Вопрос")
@ToString
public class AnswerWithQuestionDto {
    @Schema(name = "Текст вопроса")
    private String text;
    @Schema(name = "Типа вопроса")
    private String type;
    @Schema(name = "Ответ на вопрос")
    private String answer;
}
