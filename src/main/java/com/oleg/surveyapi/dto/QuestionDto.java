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
public class QuestionDto {

    @Schema(description = "Текст вопроса")
    private String text;

    @Schema(description = "Тип вопроса")
    private String type;

    @Schema(description = "id опроса")
    private Long surveyId;

    @Schema(description = "id вопроса")
    private Long questionId;

}
