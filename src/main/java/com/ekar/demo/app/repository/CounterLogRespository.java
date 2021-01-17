package com.ekar.demo.app.repository;

import com.ekar.demo.app.entity.CounterLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterLogRespository extends JpaRepository<CounterLog, Long> {
}
