package ru.afanasyev.telegram.app.impl.profiling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class CommandProfileAspect {
    private final ProfileService profileService;

    @Pointcut("within(ru.afanasyev.telegram.app.api.CommandHandler+) && execution(* handle(..))")
    public void callAt() {
    }

    @Before("callAt()")
    public void before(JoinPoint pjp) {
        profileService.profile(pjp);
    }
}
