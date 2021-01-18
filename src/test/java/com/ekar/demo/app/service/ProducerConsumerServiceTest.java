package com.ekar.demo.app.service;

import com.ekar.demo.app.config.AppProperties;
import com.ekar.demo.app.dto.SetCounterRequestDTO;
import com.ekar.demo.app.dto.ThreadCountRequestDTO;
import com.ekar.demo.app.repository.CounterLogRespository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProducerConsumerServiceTest {

    @Mock
    private AppProperties appProperties;

    @Mock
    private CounterLogRespository counterLogRespository;

    @InjectMocks
    private ProducerConsumerService producerConsumerService;

    @Test
    public void testThreadCountOperation() {
        when(appProperties.getConsumerPrefix()).thenReturn("Consumer-");
        when(appProperties.getProducerPrefix()).thenReturn("Producer-");
        when(appProperties.getCounterLowerBound()).thenReturn(0);
        when(appProperties.getCounterUpperBound()).thenReturn(100);
        ThreadCountRequestDTO countRequest = ThreadCountRequestDTO.builder()
                .producerCount(2)
                .consumerCount(5)
                .build();
        producerConsumerService.doCountIncrementDecrement(countRequest);
    }

    @Test
    public void testThreadCountReset() {
        SetCounterRequestDTO setCounterRequestDTO = new SetCounterRequestDTO();
        setCounterRequestDTO.setCounterValue(50);
        Assertions.assertTrue(producerConsumerService.setCounter(setCounterRequestDTO));
    }

}