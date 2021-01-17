package com.ekar.demo.app.controller;

import com.ekar.demo.app.dto.ResponseDTO;
import com.ekar.demo.app.dto.SetCounterRequestDTO;
import com.ekar.demo.app.dto.ThreadCountRequestDTO;
import com.ekar.demo.app.service.ProducerConsumerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Customer Account APIs", description = "These APIs are used to create and retrieve customer accounts.")
public class ProducerConsumerController {

    private static final String SUCCESS = "Success";

    private final ProducerConsumerService producerConsumerService;

    @PostMapping("/thread-counts")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO increaseThreadCount(@Valid @RequestBody final ThreadCountRequestDTO request) {
        producerConsumerService.doCountIncrementDecrement(request);
        return ResponseDTO.builder().status(SUCCESS).build();
    }

    @PutMapping("/counter")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO setCounterValue(@Valid @RequestBody final SetCounterRequestDTO counterDTO) {
        producerConsumerService.setCounter(counterDTO);
        return ResponseDTO.builder().status(SUCCESS).build();
    }
}
