package com.ekar.demo.app.logger;

import com.ekar.demo.app.dto.ThreadCountRequestDTO;
import com.ekar.demo.app.entity.converter.EntityConverter;
import com.ekar.demo.app.repository.RequestLogRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class Logging {

    private final RequestLogRepository requestLogRepository;
    private final EntityConverter entityConverter;

    @Before("execution(* com.ekar.demo.app.controller.ProducerConsumerController.increaseThreadCount(..))")
    public void before(JoinPoint jointpoint){
        Object[] obj = jointpoint.getArgs();
        for(Object o : obj){
            ThreadCountRequestDTO requestDTO = (ThreadCountRequestDTO)o;
            if(requestDTO != null) {
                requestLogRepository.save(entityConverter.convert(requestDTO));
            }else {
                break;
            }
        }
    }
}
