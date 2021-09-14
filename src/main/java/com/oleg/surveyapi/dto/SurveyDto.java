package com.oleg.surveyapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Schema(description = "Опрос")
@ToString
public class SurveyDto {
    @Schema(description = "Название опроса")
    private String name;

    @Schema(description = "Описание опроса")
    private String description;

    @Schema(description = "Дата начала опроса")
    private Long startDate;

    @Schema(description = "Дата окончания опроса")
    private Long endDate;

    @Schema(description = "Id опроса")
    private Long surveyId;


}
