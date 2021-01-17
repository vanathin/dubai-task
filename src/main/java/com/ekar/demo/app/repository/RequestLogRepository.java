package com.ekar.demo.app.repository;

import com.ekar.demo.app.dto.ThreadCountRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLogRepository extends JpaRepository<ThreadCountRequestDTO, Long> {
}
