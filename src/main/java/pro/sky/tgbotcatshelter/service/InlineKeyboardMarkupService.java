package pro.sky.tgbotcatshelter.service;

import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;

/**
 * Сервис для создания кастомных клавиатур встроенных под сообщениями.
 */
public interface InlineKeyboardMarkupService {

    /**
     * Генерирует встроенную клавиатуру для выбора типа приюта.
     *
     * @return Встроенную клавиатуру.
     */
    InlineKeyboardMarkup createButtonsShelterTypeSelect();

    /**
     * Генерирует встроенную клавиатуру для меню КОШАЧЬЕГО приюта.
     *
     * @return Встроенную клавиатуру.
     */
    InlineKeyboardMarkup createButtonsCatShelter();

    /**
     * Генерирует встроенную клавиатуру для меню СОБАЧЬЕГО приюта.
     *
     * @return Встроенную клавиатуру.
     */
    InlineKeyboardMarkup createButtonsDogShelter();

    /**
     * Генерирует встроенную клавиатуру для меню "Информация о приюте" КОШАЧЬЕГО приюта.
     *
     * @return Встроенную клавиатуру.
     */
    InlineKeyboardMarkup createButtonsCatShelterInfo();

    /**
     * Генерирует встроенную клавиатуру для меню "Информация о приюте" СОБАЧЬЕГО приюта.
     *
     * @return Встроенную клавиатуру.
     */
    InlineKeyboardMarkup createButtonsDogShelterInfo();

    /**
     * Генерирует встроенную клавиатуру для меню "Как взять животное из приюта" КОШАЧЬЕГО приюта.
     *
     * @return Встроенную клавиатуру.
     */
    InlineKeyboardMarkup createButtonsCatShelterTake();

    /**
     * Генерирует встроенную клавиатуру для меню "Как взять животное из приюта" СОБАЧЬЕГО приюта.
     *
     * @return Встроенную клавиатуру.
     */
    InlineKeyboardMarkup createButtonsDogShelterTake();

    /**
     * Генерирует встроенную клавиатуру для меню "Прислать отчет о питомце" КОШАЧЬЕГО приюта.
     *
     * @return Встроенную клавиатуру.
     */
    InlineKeyboardMarkup createButtonsCatShelterReport();

    /**
     * Генерирует встроенную клавиатуру для меню "Прислать отчет о питомце" СОБАЧЬЕГО приюта.
     *
     * @return Встроенную клавиатуру.
     */
    InlineKeyboardMarkup createButtonsDogShelterReport();

    /**
     * Генерирует встроенную клавиатуру для меню "Обустройство дома для питомца" КОШАЧЬЕГО приюта.
     *
     * @return Встроенную клавиатуру.
     */
    InlineKeyboardMarkup createButtonsCatAtHome();

    /**
     * Генерирует встроенную клавиатуру для меню "Обустройство дома для питомца" СОБАЧЬЕГО приюта.
     *
     * @return Встроенную клавиатуру.
     */
    InlineKeyboardMarkup createButtonsDogAtHome();

    InlineKeyboardMarkup createButtonsVolunteerMenu();

    InlineKeyboardMarkup createButtonsCheckReport();

    InlineKeyboardMarkup createButtonsCheckReportNotOk();

    InlineKeyboardMarkup createButtonsCheckReportNotOkExtend();
}
