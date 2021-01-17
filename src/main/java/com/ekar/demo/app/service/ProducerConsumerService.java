package com.ekar.demo.app.service;

import com.ekar.demo.app.concurrency.OffloadThread;
import com.ekar.demo.app.concurrency.ThreadPoolHolder;
import com.ekar.demo.app.config.AppProperties;
import com.ekar.demo.app.dto.SetCounterRequestDTO;
import com.ekar.demo.app.dto.ThreadCountRequestDTO;
import com.ekar.demo.app.entity.converter.EntityConverter;
import com.ekar.demo.app.repository.CounterLogRespository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerConsumerService {

    public static final AtomicInteger counter = new AtomicInteger(50);
    public static final AtomicBoolean rejected = new AtomicBoolean(false);

    private final AppProperties appProperties;
    private final CounterLogRespository counterLogRespository;
    private ThreadPoolHolder poolHolder;

    @SneakyThrows
    public void doCountIncrementDecrement(final ThreadCountRequestDTO request) {
        checkAndShutdownExistingGracefully();

        poolHolder = ThreadPoolHolder.builder()
                .producers(Executors.newFixedThreadPool(request.getProducerCount(),
                        new CustomizableThreadFactory(appProperties.getProducerPrefix())))
                .consumers(Executors.newFixedThreadPool(request.getConsumerCount(),
                        new CustomizableThreadFactory(appProperties.getConsumerPrefix())))
                .build();

        final Thread offloadThread = new OffloadThread(poolHolder, appProperties, counterLogRespository);
        offloadThread.setName("OffloadParentThread");
        offloadThread.start();
    }

    @SuppressWarnings("BusyWait")
    private void checkAndShutdownExistingGracefully() {
        Optional.ofNullable(poolHolder)
                .ifPresent(threadPoolHolder -> {
                    rejected.set(true);
                    while(!(threadPoolHolder.getConsumers().isTerminated()
                            && threadPoolHolder.getProducers().isTerminated())) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            log.error("Failed to pause.", e);
                        }
                    }
                    log.info("Gracefully stopped the existing producers & consumers.");
                    rejected.set(false);
                });
    }

    public boolean setCounter(final SetCounterRequestDTO setCounterRequestDTO) {
        counter.set(setCounterRequestDTO.getCounterValue());
        return true;
    }
}
