package pro.sky.tgbotcatshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TgBotCatShelterUpdatesListener implements UpdatesListener {
    private Logger logger = LoggerFactory.getLogger(TgBotCatShelterUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;
    @Autowired


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            String text = update.message().text();
            Long chatId = update.message().chat().id();
                      if ("/start".equalsIgnoreCase(text)) {
                telegramBot.execute(new SendMessage(chatId, "Привет! Я бот-приюта для животных, " +
                        "помогу тебе в выборе и получении необходимой информации"));
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
