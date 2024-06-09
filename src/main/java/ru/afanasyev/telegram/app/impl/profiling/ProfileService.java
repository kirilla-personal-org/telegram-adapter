package ru.afanasyev.telegram.app.impl.profiling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileService {
    public void profile(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = signature.getName();

        log.info("Method: \"{}\" in class: \"{}\" was invoked", methodName, className);
    }
}
