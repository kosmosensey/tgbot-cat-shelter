package pro.sky.tgbotcatshelter.listener;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.pengrad.telegrambot.model.*;
import com.pengrad.telegrambot.model.request.*;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {
    private final Logger log = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);


    @Autowired
    private TelegramBot telegramBot;


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            log.info("Processing update: {}", update);
            if(update.message()==null && update.callbackQuery()==null) {
                log.info("message = null");
            }
            if(update.message()!=null){
                switchMessages(update.message());
            }
            if(update.callbackQuery()!=null){
                switchCallback(update.callbackQuery());
            }
        });
        return CONFIRMED_UPDATES_ALL;
    }

    /**
     * Method that contains logic of processing messages <br>
     * based on: {@link TelegramBot} <br>
     * using {@code InlineKeyboardMarkup}
     * @see TelegramBot
     */
    private void switchMessages(Message message){
        log.info("switchMessages is processing...");
        User telegramUser = message.from();
        Long chatId = message.chat().id();

        if (message.text().equals("/start")) {

            String helloText = "Здравствуй, " + telegramUser.firstName() + "!\n" +
                    "Я помогу тебе забрать собаку или кошку домой из приюта. Для начала выбери животное:";

            //отправляем кнопки с вариантами приютов
            InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
                    new InlineKeyboardButton("Приют для кошек  " + "\uD83D\uDC08")
                            .callbackData("catShelter"),
                    new InlineKeyboardButton("Приют для собак   " + "\uD83D\uDC15")
                            .callbackData("dogShelter"));

            SendMessage mess = new SendMessage(chatId, helloText);
            mess.replyMarkup(inlineKeyboard);
            telegramBot.execute(mess);


        } else{
            InlineKeyboardMarkup inlineKeyboard1 = new InlineKeyboardMarkup();
            inlineKeyboard1.addRow(new InlineKeyboardButton("Информация о приютах")
                    .callbackData("infoAboutShelter"));
            inlineKeyboard1.addRow(new InlineKeyboardButton("Как взять животное")
                    .callbackData("howTakeAnimal"));
            inlineKeyboard1.addRow(new InlineKeyboardButton("Прислать отчет о питомце")
                    .callbackData("sendReport"));
            inlineKeyboard1.addRow(new InlineKeyboardButton("Позвать волонтера")
                    .callbackData("callVolunteer"));

            SendMessage mess1 = new SendMessage(chatId,"Давай продолжим! Выбери, что тебя интересует:")
                    .replyMarkup(inlineKeyboard1);
            telegramBot.execute(mess1);
        }
    }


    private void switchCallback(CallbackQuery callbackQuery) {
        if (callbackQuery.data().contains("catShelter") || callbackQuery.data().contains("dogShelter")) {
            log.info("callback_data = catShelter");

            InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();
            inlineKeyboard.addRow(new InlineKeyboardButton("Информация о приютах")
                    .callbackData("infoAboutShelter"));
            inlineKeyboard.addRow(new InlineKeyboardButton("Как взять животное")
                    .callbackData("howTakeAnimal"));
            inlineKeyboard.addRow(new InlineKeyboardButton("Прислать отчет о питомце")
                    .callbackData("sendReport"));
            inlineKeyboard.addRow(new InlineKeyboardButton("Позвать волонтера")
                    .callbackData("callVolunteer"));

            SendMessage mess = new SendMessage(callbackQuery.message().chat().id(), "Выбор меню")
                    .replyMarkup(inlineKeyboard);
            telegramBot.execute(mess);
        } else if (callbackQuery.data().contains("infoAboutShelter")) {
            log.info("callback_data = infoAboutShelter");

            InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();
            inlineKeyboard.addRow(new InlineKeyboardButton("Адреса приютов и время работы")
                    .callbackData("sheltersAddresses"));
            inlineKeyboard.addRow(new InlineKeyboardButton("Оформление пропуска на машину")
                    .callbackData("passCar"));
            inlineKeyboard.addRow(new InlineKeyboardButton("Правила нахождения внутри и общения с животным")
                    .callbackData("shelterRules"));
            inlineKeyboard.addRow(new InlineKeyboardButton("Записать контактные данные для связи")
                    .callbackData("contactInformation"));
            inlineKeyboard.addRow(new InlineKeyboardButton("Позвать волонтера")
                    .callbackData("callVolunteer"));

            SendMessage mess = new SendMessage(callbackQuery.message().chat().id(), "Я помогу тебе " +
                    "узнать информацию о приюте! Выбери, что тебя интересует: ")
                    .replyMarkup(inlineKeyboard);
            telegramBot.execute(mess);


        }
    }

}