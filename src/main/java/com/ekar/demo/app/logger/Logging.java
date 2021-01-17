package com.ekar.demo.app.logger;

import com.ekar.demo.app.dto.ThreadCountRequestDTO;
import com.ekar.demo.app.config.AppProperties;
import com.ekar.demo.app.repository.RequestLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logging {

    @Autowired
    private RequestLogRepository requestLogRepository;

    @Autowired
    private AppProperties appProperties;

    @Before("execution(* com.ekar.demo.app.controller.ProducerConsumerController.increaseThreadCount(..))")
    public void before(JoinPoint jointpoint){
        Object[] obj = jointpoint.getArgs();
        for(Object o : obj){
            ThreadCountRequestDTO p = (ThreadCountRequestDTO)o;
            if(p != null) {
                requestLogRepository.save(p);
            }else {
                break;
            }
        }
    }
}
