package com.ekar.demo.app.concurrency.consumer;

import com.ekar.demo.app.config.AppProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import static com.ekar.demo.app.service.ProducerConsumerService.counter;

@Slf4j
@RequiredArgsConstructor
public class ConsumerJob implements Runnable {

    private final AppProperties appProperties;

    @SneakyThrows
    @Override
    public void run() {
        if (counter.get() <= appProperties.getCounterLowerBound()
                || counter.get() >= appProperties.getCounterUpperBound()) {
            return;
        }
        log.info("Counter: {}", counter.decrementAndGet());
        //To simulate some latency of work
        Thread.sleep(appProperties.getConsumerProcessingTime());
    }
}