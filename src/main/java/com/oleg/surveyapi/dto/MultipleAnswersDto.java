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
@Schema(description = "Прохождение опроса")
@ToString
public class MultipleAnswersDto {
    @Schema(description = "Id пользователя, проходящего опрос")
    private Long personId;
    @Schema(description = "Список ответов")
    private List<AnswerDto> answers;
}
