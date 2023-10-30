package pro.sky.tgbotcatshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
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
     *
     * <u>Приветствие бота, выбор приюта и базовая информация</u>
     * <br>
     * @param updates сообщение от пользователя не может быть {@code  null}
     *                Если приходит неизвестная команда, пишет - "команда не определена"
     *
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
            } else {
                // Игнорируем неизвестные типы обновлений
                return;
            }

            // Логируем текст сообщения
            logger.info(text);

            if (text.equalsIgnoreCase("/start")) {
                // Создаем клавиатуру с встроенными кнопками
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
                // Отправляем сообщение
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
        // Возвращаем подтверждение обработанных обновлений
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
