package ru.yandex.practicum.filmorate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class FilmorateApplication {
	private final static Logger log = LoggerFactory.getLogger(FilmorateApplication.class);
	public static void main(String[] args) {
		log.info("start "+ LocalDateTime.now());
		SpringApplication.run(FilmorateApplication.class, args);
		log.info("stop "+ LocalDateTime.now());

	}

}
