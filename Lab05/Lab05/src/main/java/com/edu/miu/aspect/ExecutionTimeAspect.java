package com.edu.miu.aspect;

import com.edu.miu.dto.ActivityLogDTO;
import com.edu.miu.service.ActivityLogService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Aspect
@Component
@AllArgsConstructor
public class ExecutionTimeAspect {
    private final ActivityLogService service;

    @Around("@annotation(com.edu.miu.controller.annotation.ExecutionTime)") // pointCut expression
    public Object calculateExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long start = System.nanoTime();
        var result  = proceedingJoinPoint.proceed();
        long finish=System.nanoTime();
        System.out.println(proceedingJoinPoint.getSignature().getName()+" duration "+(finish-start));
        service.saveLog(new ActivityLogDTO(1, LocalDate.now(),proceedingJoinPoint.getSignature().toLongString(),(finish-start)));
        return result;
}
}
