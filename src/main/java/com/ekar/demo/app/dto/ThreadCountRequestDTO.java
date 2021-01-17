package com.ekar.demo.app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
@Builder
public class ThreadCountRequestDTO {
    @NotNull
    @Min(value = 1)
    private Integer producerCount;
    @NotNull
    @Min(value = 1)
    private Integer consumerCount;
}
