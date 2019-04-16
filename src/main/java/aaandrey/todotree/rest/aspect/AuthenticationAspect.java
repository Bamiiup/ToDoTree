package aaandrey.todotree.rest.aspect;

import aaandrey.todotree.rest.utils.AuthenticationUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.stream.Stream;

@Component
@Aspect
public class AuthenticationAspect {
    @Autowired
    private AuthenticationUtils authenticationUtils;

    @Pointcut("execution(@aaandrey.todotree.rest.aspect.Authenticational * *(..))")
    public void annotatedByAuthenticational() {

    }

    @Around("annotatedByAuthenticational()")
    public Object around(ProceedingJoinPoint joinPoint) {
        //REVIEW: улучшить pointcut чтобы он захватывал только те методы, которые
        //имеют HttpServletRequest. Появится возможность сразу получить HttpServletRequest и
        //пропадёт необходимость в методе findRequest
        HttpServletRequest request = findRequest(joinPoint);
        if (request == null) {
            throw new RuntimeException("Method annotated by Authenticational must have HttpServletRequest argument");
        }

        Object target = joinPoint.getTarget();

        return authenticationUtils.peformAfterAuthentication(request, (userId) -> {
            try {
                //REVIEW: добавить к классам controller-ов интерфейс
                //которые они реализовывают, в котором будет методы по установке userId
                //и target просто кастить к этому интерфейсу и явно вызывать метод
                //ниже несколько строчек, устанавливающих через поле будут удалены
                Field userIdField = target.getClass().getDeclaredField("userId");

                userIdField.setAccessible(true);
                userIdField.set(target, userId);

                @SuppressWarnings("unchecked")
                ResponseEntity<Object> result = (ResponseEntity<Object>) joinPoint.proceed();

                userIdField.set(target, null);
                userIdField.setAccessible(false);

                return result;

            } catch (NoSuchFieldException e) {
                throw new RuntimeException("Class with methods annotated by Authenticational must have userId field", e);
            } catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });

    }

    private HttpServletRequest findRequest(ProceedingJoinPoint joinPoint) {
        HttpServletRequest request = (HttpServletRequest) Stream.of(joinPoint.getArgs())
                .filter(arg -> HttpServletRequest.class.isAssignableFrom(arg.getClass())).findFirst().orElse(null);

        return request;
    }
}
