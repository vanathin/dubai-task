package com.ekar.demo.app.service;

import com.ekar.demo.app.dto.SetCounterRequestDTO;
import com.ekar.demo.app.dto.ThreadCountRequestDTO;
import com.ekar.demo.app.config.AppProperties;
import com.ekar.demo.app.repository.CounterLogRespository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.concurrent.atomic.AtomicInteger;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProducerConsumerServiceTest {

    @InjectMocks
    private ProducerConsumerService producerConsumerService;

    @Mock
    private AppProperties appProperties;

    @Mock
    private CounterLogRespository counterLogRespository;

    @BeforeTestClass
    void setUp() {
        when(appProperties.getConsumerPrefix()).thenReturn("Consumer-");
        when(appProperties.getProducerPrefix()).thenReturn("Producer-");
    }

    @Test
    public void testThreadCountOperation(){
        AtomicInteger counter = new AtomicInteger(50);
        ThreadCountRequestDTO countRequest = ThreadCountRequestDTO.builder()
                .producerCount(2)
                .consumerCount(5)
                .build();
        producerConsumerService.doCountIncrementDecrement(countRequest);
    }

    @Test
    public void testThreadCountReset(){
        SetCounterRequestDTO setCounterRequestDTO = new SetCounterRequestDTO();
        setCounterRequestDTO.setCounterValue(50);
        Assertions.assertTrue(producerConsumerService.setCounter(setCounterRequestDTO));
    }

}