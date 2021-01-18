package com.ekar.demo.app.controller;

import com.ekar.demo.app.dto.ThreadCountRequestDTO;
import com.ekar.demo.app.helper.TestHelper;
import com.ekar.demo.app.service.ProducerConsumerService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProducerConsumerController.class)
class ProducerConsumerControllerTest {

    @MockBean
    private ProducerConsumerService producerConsumerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    public void testIncreaseThreadCount() {
        ThreadCountRequestDTO countRequest = ThreadCountRequestDTO.builder()
                .consumerCount(2)
                .producerCount(5)
                .build();

        doNothing().when(producerConsumerService).doCountIncrementDecrement(any(ThreadCountRequestDTO.class));
        //Action & Assert
        mockMvc.perform(post("/api/v1/thread-counts")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(TestHelper.asJsonString(ThreadCountRequestDTO.builder()
                        .consumerCount(2)
                        .producerCount(5)
                        .build())
                ))
                .andExpect(status().isCreated());

    }

    /*@Test
    @SneakyThrows
    public void testResetCounter() {
        UpdateCounterRequest updateCounter =UpdateCounterRequest.builder()
                .counterValue(50)
                .build();
        when(producerConsumerService.setCounter(any(UpdateCounterRequest.class)))
                .thenReturn(updateCounter);
        //Action & Assert
        mockMvc.perform(put("/api/v1/counter")
                .content(TestHelper.asJsonString(UpdateCounterRequest.builder()
                        .counterValue(50)
                        .build())
                ))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.counterValue").value(50));
    }*/
}