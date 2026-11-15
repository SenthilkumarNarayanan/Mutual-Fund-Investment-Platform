package com.mfplatform.user;

import com.mfplatform.exception.GlobalExceptionHandler;
import com.mfplatform.logging.CorrelationIdFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
    GlobalExceptionHandler.class,
    CorrelationIdFilter.class
})
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}