package ru.afanasyev.telegram.app.impl.profiling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.stereotype.Service;
import ru.afanasyev.telegram.app.api.ProfileRepository;
import ru.afanasyev.telegram.domain.profile.ClassProfile;
import ru.afanasyev.telegram.domain.profile.MethodProfile;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileService {
    private final ProfileRepository profileRepository;

//    @Scheduled(fixedDelay = 5000)
    public void showProfilingInfo() {
        profileRepository.findAll().forEach(classProfile -> {
                Long sum = classProfile.getMethodProfiles().stream()
                    .map(MethodProfile::getCalls)
                    .reduce(Long::sum).orElse(0L);
                log.info("Class: {}, calls: {}", classProfile.getClassName(), sum);
            }
        );
    }

    public void profile(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = signature.getName();

        log.info("Method: \"{}\" in class: \"{}\" was invoked", methodName, className);
        incrementCalls(className, methodName);
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private void incrementCalls(String className, String methodName) {
        ClassProfile classProfile = profileRepository.findByClassName(className)
            .orElse(createNewProfile(className, methodName));
        MethodProfile methodProfile = classProfile.getMethodProfiles().stream()
            .filter(mp -> methodName.equals(mp.getMethodName()))
            .findFirst().orElse(createMethodProfile(methodName));
        methodProfile.setCalls(methodProfile.getCalls() + 1);
        profileRepository.save(classProfile);
    }

    private ClassProfile createNewProfile(String className, String methodName) {
        Set<MethodProfile> methodProfiles = Set.of(createMethodProfile(methodName));
        return new ClassProfile(className, methodProfiles);
    }

    private MethodProfile createMethodProfile(String methodName) {
        return new MethodProfile(methodName, 0L);
    }
}
