package com.ekar.demo.app.concurrency;

import com.ekar.demo.app.concurrency.consumer.ConsumerJob;
import com.ekar.demo.app.concurrency.producer.ProducerJob;
import com.ekar.demo.app.config.AppProperties;
import com.ekar.demo.app.entity.CounterLog;
import com.ekar.demo.app.repository.CounterLogRespository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import static com.ekar.demo.app.service.ProducerConsumerService.counter;
import static com.ekar.demo.app.service.ProducerConsumerService.rejected;

@Slf4j
@RequiredArgsConstructor
public class OffloadThread extends Thread {

    private final ThreadPoolHolder poolHolder;
    private final AppProperties appProperties;
    private final CounterLogRespository counterLogRespository;

    @SuppressWarnings("BusyWait")
    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            if (rejected.get()
                    || counter.get() <= appProperties.getCounterLowerBound()
                    || counter.get() >= appProperties.getCounterUpperBound()) {
                final LocalDateTime localDateTime = LocalDateTime.now();
                poolHolder.terminate();
                if (!rejected.get()) {
                    counterLogRespository.save(CounterLog.builder()
                            .message(String.format("Counter value: %s", counter.get()))
                            .transactionDateTime(localDateTime)
                            .build());
                }
                break;
            }
            poolHolder.getProducers().execute(new ProducerJob(appProperties));
            poolHolder.getConsumers().execute(new ConsumerJob(appProperties));
            Thread.sleep(5);
        }
    }
}
