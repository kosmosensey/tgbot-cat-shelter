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
     * Метод, обрабатывающий сообщения.
     * @param updates Сообщения полученные телеграмм бот.
     * @return Возвращает ответ в зависимости от сообщения или нажатой клавиши.
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

                markup.addRow(new InlineKeyboardButton("Узнать информацию о приюте").callbackData("/c1"));
                markup.addRow(new InlineKeyboardButton("Как взять животное из приюта").callbackData("/c2"));
                markup.addRow(new InlineKeyboardButton("Прислать отчет о питомце").callbackData("/c3"));
                markup.addRow(new InlineKeyboardButton("Позвать волонтера ").callbackData("/c4"));
                SendMessage send = new SendMessage(chatId, """
                        Здравствуйте!
                        Вас приветствует бот приютов домашних животных.
                        Выберете опцию для продолжения.""")
                        .replyMarkup(markup);
                // Отправляем сообщение
                telegramBot.execute(send);
            }
        });
        // Возвращаем подтверждение обработанных обновлений
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
