package com.ekar.demo.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
public class SetCounterRequestDTO {

    @Min(1)
    @Max(99)
    private Integer counterValue;
}
