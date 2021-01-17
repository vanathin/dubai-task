package com.ekar.demo.app.entity.converter;

import com.ekar.demo.app.dto.ThreadCountRequestDTO;
import com.ekar.demo.app.entity.ThreadCountRequestLog;
import org.springframework.stereotype.Component;

@Component
public class EntityConverter {
    public ThreadCountRequestLog convert(final ThreadCountRequestDTO requestDTO) {
        return ThreadCountRequestLog.builder()
                .consumerCount(requestDTO.getConsumerCount())
                .producerCount(requestDTO.getProducerCount())
                .build();
    }
}
