package pro.sky.tgbotcatshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static pro.sky.tgbotcatshelter.constants.Messages.*;

/**
 * Обработчик сообщений телеграмм бота, от реализации UpdatesListener.
 */
@Service
public final class TgBotCatShelterUpdatesListener implements UpdatesListener {
    private final Logger logger = LoggerFactory.getLogger(TgBotCatShelterUpdatesListener.class);
    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    /**
     * <u>Приветствие бота, выбор приюта и базовая информация</u>
     * <br>
     *
     * @param updates сообщение от пользователя не может быть {@code  null}
     *                Если приходит неизвестная команда, пишет - "команда не определена"
     * @return Бот отправляет ответ в зависимости от поступившей команды.
     */
    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            Message message = update.message();
            String text;
            Long chatId;
            if (message != null) {
                // Если это текстовое сообщение
                text = message.text();
                chatId = message.chat().id();
            } else if (update.callbackQuery() != null) {
                // Если это обратный вызов (например, нажатие кнопки)
                text = update.callbackQuery().data();
                chatId = update.callbackQuery().message().chat().id();
                createMessageOut(update);
            } else {
                // Игнорируем неизвестные типы обновлений
                return;
            }

            // Логируем текст сообщения
            logger.info(text);

            if (text.equalsIgnoreCase("/start")) {
                SendMessage send = new SendMessage(chatId, GREETINGS_NEW_USER);
                telegramBot.execute(send);
                InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
                markup.addRow(
                        new InlineKeyboardButton("Приют для кошек").callbackData("/c1"));
                markup.addRow(
                        new InlineKeyboardButton("Приют для собак").callbackData("/d1"));

                SendMessage send2 = new SendMessage(chatId, "Выберите интересующий вас приют:")
                        .replyMarkup(markup);
                // Отправляем сообщение send2
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
                        new InlineKeyboardButton("Позвать волонтера приюта").callbackData("/cat4"));

                SendMessage send = new SendMessage(chatId, "Выберите один из вариантов:")
                        .replyMarkup(markup);
                // Отправляем сообщение send
                telegramBot.execute(send);
            } else if (text.equalsIgnoreCase("/d1")) {
                InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
                markup.addRow(
                        new InlineKeyboardButton("Узнать информацию о приюте для собак").callbackData("/dog1"));
                markup.addRow(
                        new InlineKeyboardButton("Как взять животное из приюта собак").callbackData("/dog2"));
                markup.addRow(
                        new InlineKeyboardButton("Прислать отчет о питомце").callbackData("/dog3"));
                markup.addRow(
                        new InlineKeyboardButton("Позвать волонтера приюта").callbackData("/dog4"));

                SendMessage send = new SendMessage(chatId, "Выберите один из вариантов:")
                        .replyMarkup(markup);
                // Отправляем сообщение send
                telegramBot.execute(send);
            } else if (text.equalsIgnoreCase("/сat1")) {
                InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
                markup.addRow(
                        new InlineKeyboardButton("О нашем приюте кошек").callbackData("/сat11"));
                markup.addRow(
                        new InlineKeyboardButton("Расписание работы приюта кошек и адрес, схему проезда")
                                .callbackData("/сat12"));
                markup.addRow(
                        new InlineKeyboardButton("Контактные данные охраны для оформления пропуска на машину").callbackData("/сat13"));
                markup.addRow(
                        new InlineKeyboardButton("Рекомендации о технике безопасности на территории приюта").callbackData("/сat14"));
                markup.addRow(
                        new InlineKeyboardButton("Принять и записать контактные данные для связи").callbackData("/сat15"));
                markup.addRow(
                        new InlineKeyboardButton("Позвать волонтера приюта").callbackData("/сat16"));

                SendMessage send = new SendMessage(chatId, "Выберите один из вариантов:")
                        .replyMarkup(markup);
                // Отправляем сообщение send

                telegramBot.execute(send);
            } else if (text.equalsIgnoreCase("/dog1")) {
                InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
                markup.addRow(
                        new InlineKeyboardButton("О нашем приюте для собак").callbackData("/dog11"));
                markup.addRow(
                        new InlineKeyboardButton("Расписание работы приюта и адрес, схему проезда")
                                .callbackData("/dog12"));
                markup.addRow(
                        new InlineKeyboardButton("Контактные данные охраны для оформления пропуска на машину").callbackData("/dog13"));
                markup.addRow(
                        new InlineKeyboardButton("Рекомендации о технике безопасности на территории приюта").callbackData("/dog14"));
                markup.addRow(
                        new InlineKeyboardButton("Принять и записать контактные данные для связи").callbackData("/dog15"));
                markup.addRow(
                        new InlineKeyboardButton("Позвать волонтера приюта").callbackData("/dog16"));

                SendMessage send = new SendMessage(chatId, "Выберите один из вариантов:")
                        .replyMarkup(markup);
                // Отправляем сообщение send

                telegramBot.execute(send);
            } else if (text.equalsIgnoreCase("/сat2")) {
                InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
                markup.addRow(
                        new InlineKeyboardButton("Правила знакомства с животным").callbackData("/сat21"));

                markup.addRow(
                        new InlineKeyboardButton("Список документов, необходимых для того, чтобы взять животное из приюта")
                                .callbackData("/сat22"));
                markup.addRow(
                        new InlineKeyboardButton("Список рекомендаций по транспортировке кошки/кота").callbackData("/сat23"));
                markup.addRow(
                        new InlineKeyboardButton("Список рекомендаций по обустройству дома для котенка").callbackData("/сat24"));
                markup.addRow(
                        new InlineKeyboardButton("Список рекомендаций по обустройству дома для взрослого животного").callbackData("/сat25"));
                markup.addRow(
                        new InlineKeyboardButton("Список рекомендаций по обустройству дома для животного с ограниченными возможностями").callbackData("/сat26"));
                markup.addRow(
                        new InlineKeyboardButton("Список причин, почему могут отказать и не дать забрать кошку из приюта").callbackData("/сat27"));
                markup.addRow(
                        new InlineKeyboardButton("Принять и записать контактные данные для связи").callbackData("/сat28"));
                markup.addRow(
                        new InlineKeyboardButton("Позвать волонтера приюта кошек").callbackData("/сat29"));

                SendMessage send = new SendMessage(chatId, "Выберите один из вариантов:")
                        .replyMarkup(markup);
                // Отправляем сообщение send

                telegramBot.execute(send);
            } else if (text.equalsIgnoreCase("/dog2")) {
                InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
                markup.addRow(
                        new InlineKeyboardButton("Правила знакомства с животным").callbackData("/dog21"));

                markup.addRow(
                        new InlineKeyboardButton("Список документов, необходимых для того, чтобы взять собаку из приюта")
                                .callbackData("/dog22"));
                markup.addRow(
                        new InlineKeyboardButton("Список рекомендаций по транспортировке собаки").callbackData("/dog23"));
                markup.addRow(
                        new InlineKeyboardButton("Список рекомендаций по обустройству дома для щенка").callbackData("/dog24"));
                markup.addRow(
                        new InlineKeyboardButton("Список рекомендаций по обустройству дома для взрослого животного").callbackData("/dog25"));
                markup.addRow(
                        new InlineKeyboardButton("Список рекомендаций по обустройству дома для животного с ограниченными возможностями").callbackData("/dog26"));
                markup.addRow(
                        new InlineKeyboardButton("Список причин, почему могут отказать и не дать забрать собаку из приюта").callbackData("/dog27"));
                markup.addRow(
                        new InlineKeyboardButton("Cоветы кинолога по первичному общению с собакой").callbackData("/dog28"));
                markup.addRow(
                        new InlineKeyboardButton("Рекомендации проверенных кинологов").callbackData("/dog29"));
                markup.addRow(
                        new InlineKeyboardButton("Принять и записать контактные данные для связи").callbackData("/dog210"));
                markup.addRow(
                        new InlineKeyboardButton("Позвать волонтера").callbackData("/dog211"));

                SendMessage send = new SendMessage(chatId, "Выберите один из вариантов:")
                        .replyMarkup(markup);
                // Отправляем сообщение send

                telegramBot.execute(send);
            }
        });
        // Возвращаем подтверждение обработанных обновлений
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
    private void createMessageOut(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        if (callbackQuery != null) {
            String data = callbackQuery.data();
            switch (data) {
                case "/dog210" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), GET_CONTACT_INFORMATION));
                case "/dog211" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), CALL_VOLUNTEER));
                case "/сat3" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), SEND_PETS_REPORT));
                case "/dog3" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), SEND_PETS_REPORT));
                case "/cat4" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), CALL_VOLUNTEER));
                case "/dog4" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), CALL_VOLUNTEER));
                case "/сat21" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), RULES_FOR_DATING_CATS));
                case "/сat22" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), DOCUMENTS_TO_PICK_UP_A_ANIMALS));
                case "/сat23" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), RECOMMENDATIONS_FOR_TRANSPORTING_A_CAT));
                case "/сat24" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), ARRANGE_A_HOME_FOR_A_KITTEN));
                case "/сat25" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), SET_UP_A_HOME_FOR_AN_ADULT_CAT));
                case "/сat26" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), ARRANGE_A_HOME_FOR_A_DISABLED_ANIMAL));
                case "/сat27" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), REASONS_FOR_REFUSAL));
                case "/сat28" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), GET_CONTACT_INFORMATION));
                case "/сat29" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), CALL_VOLUNTEER));
                case "/dog21" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), RULES_FOR_DATING_DOGS));
                case "/dog22" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), DOCUMENTS_TO_PICK_UP_A_ANIMALS));
                case "/dog23" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), RECOMMENDATIONS_FOR_TRANSPORTING_A_DOG));
                case "/dog24" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), ARRANGE_A_HOME_FOR_A_PUPPY));
                case "/dog25" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), SET_UP_A_HOME_FOR_AN_ADULT_DOG ));
                case "/dog26" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), ARRANGE_A_HOME_FOR_A_DISABLED_ANIMAL));
                case "/dog27" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), REASONS_FOR_REFUSAL));
                case "/dog28" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), TIPS_FOR_INITIAL_COMMUNICATION_WITH_A_DOG));
                case "/dog29" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), RECOMMENDATIONS_FROM_DOG_HANDLERS));
                case "/сat11" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), INFORMATION_ABOUT_THE_SHELTER_CATS));
                case "/сat12" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), SCHEDULE_ADDRESS_DIRECTIONS_CATS));
                case "/сat13" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), SECURITY_INFORMATION_FOR_ISSUING_A_PASS));
                case "/сat14" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), SAFETY_PRECAUTIONS));
                case "/сat15" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), GET_CONTACT_INFORMATION));
                case "/сat16" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), CALL_VOLUNTEER));
                case "/dog11" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), INFORMATION_ABOUT_THE_SHELTER_DOGS));
                case "/dog12" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), SCHEDULE_ADDRESS_DIRECTIONS_DOGS));
                case "/dog13" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), SECURITY_INFORMATION_FOR_ISSUING_A_PASS));
                case "/dog14" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), SAFETY_PRECAUTIONS));
                case "/dog15" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), GET_CONTACT_INFORMATION));
                case "/dog16" ->
                        telegramBot.execute(new SendMessage(update.callbackQuery().from().id(), CALL_VOLUNTEER));
            }
        }
    }

}

