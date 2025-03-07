package ru.yandex.practicum.filmorate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class FilmorateApplication {
    private static final Logger log = LoggerFactory.getLogger(FilmorateApplication.class);

    public static void main(String[] args) {
        log.info("start " + LocalDateTime.now());
        SpringApplication.run(FilmorateApplication.class, args);
        log.info("stop " + LocalDateTime.now());

    }

}
