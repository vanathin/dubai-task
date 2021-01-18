package com.ekar.demo.app.concurrency;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Getter
@Builder
public class ThreadPoolHolder {
    private final ExecutorService producers;
    private final ExecutorService consumers;

    private static void gracefulTermination(final ExecutorService executor, final String type) {
        if (!executor.isShutdown()) {
            log.debug("{} Termination initiated...", type);
            executor.shutdown();
            try {
                if (!executor.awaitTermination(100, TimeUnit.MILLISECONDS)) {
                    executor.shutdownNow();
                } else {
                    log.debug("{} Termination Completed.", type);
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
        }
    }

    public void terminate() {
        gracefulTermination(producers, "Producer");
        gracefulTermination(consumers, "Consumer");
    }
}
