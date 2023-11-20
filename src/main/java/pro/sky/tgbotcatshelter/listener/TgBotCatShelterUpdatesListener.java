package pro.sky.tgbotcatshelter.listener;

import pro.sky.tgbotcatshelter.service.UserRequestService;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
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
    private final TelegramBot telegramBot;
    private final UserRequestService userRequestService;

    public TgBotCatShelterUpdatesListener(TelegramBot telegramBot,
                                          UserRequestService userRequestService) {
        this.telegramBot = telegramBot;
        this.userRequestService = userRequestService;
    }

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
    @SneakyThrows
    public int process(List<Update> updates) {

        try {

            updates.forEach(update -> {
                logger.info("Handles update: {}", update);

                if (update.message() == null) {
                    userRequestService.createButtonClick(update);

                } else {
                    userRequestService.sendMessageStart(update);
                }
                if (userRequestService.checkVolunteer(update)) {
                    return;
                }
            });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}

