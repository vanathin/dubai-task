package com.ekar.demo.app.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Builder
@Table(name="request_log")
@Entity
public class ThreadCountRequestLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Min(value = 1)
    private Integer producerCount;
    @NotNull
    @Min(value = 1)
    private Integer consumerCount;
    @CreationTimestamp
    private LocalDateTime createdDateTime;
}
