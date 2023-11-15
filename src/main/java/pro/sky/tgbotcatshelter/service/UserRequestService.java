package pro.sky.tgbotcatshelter.service;

import com.pengrad.telegrambot.model.Update;

/**
 * Сервис для обработки запросов пользователей в боте.
 */
public interface UserRequestService {


    /**
     * Обрабатывает запрос на записи данных юзера.
     *
     * @param update Объект, представляющий обновление бота.
     */
    void updateUser(Update update);


    /**
     * Обрабатывает запрос на отсылку отчета Юзером и получением отчета в БД.
     *
     * @param update Объект, представляющий обновление бота.
     */
    void takeReportFromUser(Update update);

    /**
     * Обрабатывает запрос на создание кнопки.
     *
     * @param update Объект, представляющий обновление бота.
     */
    void createButtonClick(Update update);

    /**
     * Отправляет начальное приветственное сообщение.
     *
     * @param update Объект, представляющий обновление бота.
     */
    void sendMessageStart(Update update);



}