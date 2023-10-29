package pro.sky.tgbotcatshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
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

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            Message message = update.message();
            String text;
            Long chatId;
            if (message != null) {
                text = message.text();
                chatId = message.chat().id();
            } else if (update.callbackQuery() != null) {
                text = update.callbackQuery().data();
                chatId = update.callbackQuery().message().chat().id();
            } else {
                return;
            }
            logger.info(text);
            if (text.equalsIgnoreCase("/start")) {
                SendMessage send = new SendMessage(chatId, "Привет! Я бот-приюта для животных, " +
                        "помогу тебе в выборе друга-питомца и получении необходимой информации" +
                        "  В этом чате я смогу ответить на ваши вопросы 24/7");
                telegramBot.execute(send);
                InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
                markup.addRow(
                        new InlineKeyboardButton("Приют для кошек").callbackData("/c1"));
                markup.addRow(
                        new InlineKeyboardButton("Приют для собак").callbackData("/с2"));

                SendMessage send2 = new SendMessage(chatId, "Выберите интересующий вас приют:")
                        .replyMarkup(markup);
                telegramBot.execute(send2);
            } else if (text.equalsIgnoreCase("/c1")) {
                InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
                markup.addRow(
                        new InlineKeyboardButton("Узнать информацию о приюте кошек").callbackData("/сat1"));
                markup.addRow(
                        new InlineKeyboardButton("Как взять животное из приюта кошек").callbackData("/сat2"));
                markup.addRow(
                        new InlineKeyboardButton("Прислать отчет о питомце").callbackData("/сat3"));
                markup.addRow(
                        new InlineKeyboardButton("Позвать волонтера приюта кошек").callbackData("/cat4"));

                SendMessage send = new SendMessage(chatId, "Выберите один из вариантов:")
                        .replyMarkup(markup);
                telegramBot.execute(send);
            } else if (text.equalsIgnoreCase("/с2")) {
                InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
                markup.addRow(
                        new InlineKeyboardButton("Узнать информацию о приюте для собак").callbackData("/d1"));
                markup.addRow(
                        new InlineKeyboardButton("Как взять животное из приюта собак").callbackData("/d2"));
                markup.addRow(
                        new InlineKeyboardButton("Прислать отчет о питомце").callbackData("/d3"));
                markup.addRow(
                        new InlineKeyboardButton("Позвать волонтера приюта собак").callbackData("/d4"));

                SendMessage send = new SendMessage(chatId, "Выберите один из вариантов:")
                        .replyMarkup(markup);
                telegramBot.execute(send);
            } else {
                SendMessage send1 = new SendMessage(chatId, "команда не определена");
                telegramBot.execute(send1);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}

