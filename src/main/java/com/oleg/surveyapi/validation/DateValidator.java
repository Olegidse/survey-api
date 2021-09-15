package com.oleg.surveyapi.validation;

import org.springframework.stereotype.Component;

@Component
public class DateValidator {

    public boolean isValid(Long startDate, Long endDate) {
        return startDate != null
                &&endDate != null
                &&endDate > startDate
                &&endDate > System.currentTimeMillis();
    }


}
