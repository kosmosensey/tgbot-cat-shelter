package pro.sky.tgbotcatshelter;

import com.pengrad.telegrambot.model.Update;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pro.sky.tgbotcatshelter.entity.ReportUser;
import pro.sky.tgbotcatshelter.repository.ReportUserRepository;
import pro.sky.tgbotcatshelter.service.ReportUserService;

import java.util.Collection;

@SpringBootApplication
@OpenAPIDefinition
public class TgBotCatShelterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TgBotCatShelterApplication.class, args);
    }
}