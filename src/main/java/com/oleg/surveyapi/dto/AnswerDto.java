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
public class AnswerDto {

    @Schema(description = "Id вопроса")
    private Long questionId;

//    @Schema(description = "Id пользователя, оветившего на вопрос ")
//    private Long personId;

    @Schema(description = "Ответ на вопрос")
    private String answer;

}
