package pro.sky.tgbotcatshelter.service.impl;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.springframework.stereotype.Service;
import pro.sky.tgbotcatshelter.service.InlineKeyboardMarkupService;

import static pro.sky.tgbotcatshelter.constants.Messages.*;

@Service
public class InlineKeyboardMarkupServiceImpl implements InlineKeyboardMarkupService {

    @Override
    public InlineKeyboardMarkup createButtonsShelterTypeSelect() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Приют для кошек")
                .callbackData(CLICK_CAT_SHELTER));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Приют для собак")
                .callbackData(CLICK_DOG_SHELTER));
        return inlineKeyboardMarkup;
    }

    @Override
    public InlineKeyboardMarkup createButtonsCatShelter() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Информация о приюте")
                .callbackData(CLICK_CAT_SHELTER_INFO));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Как забрать питомца из приюта")
                .callbackData(CLICK_HOW_TO_ADOPT_A_CAT));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Отправить отчет о питомце")
                .callbackData(CLICK_SEND_A_CAT_REPORT));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера")
                .callbackData(CLICK_CALL_A_VOLUNTEER));

        return inlineKeyboardMarkup;
    }

    @Override
    public InlineKeyboardMarkup createButtonsDogShelter() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Информация о приюте")
                .callbackData(CLICK_DOG_SHELTER_INFO));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Как забрать питомца из приюта")
                .callbackData(CLICK_HOW_TO_ADOPT_A_DOG));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Отправить отчет о питомце")
                .callbackData(CLICK_SEND_A_DOG_REPORT));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера")
                .callbackData(CLICK_CALL_A_VOLUNTEER));

        return inlineKeyboardMarkup;
    }

    @Override
    public InlineKeyboardMarkup createButtonsCatShelterInfo() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Обзор приюта")
                .callbackData(CLICK_CAT_SHELTER_OVERVIEW));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Оформить пропуск для автомобиля")
                .callbackData(CLICK_CAR_PASS_CAT));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Техника безопасности")
                .callbackData(CLICK_SAFETY_PRECAUTIONS_CAT));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Записаться на посещение")
                .callbackData(CLICK_VISIT_CAT));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера")
                .callbackData(CLICK_CALL_A_VOLUNTEER));

        return inlineKeyboardMarkup;
    }

    @Override
    public InlineKeyboardMarkup createButtonsDogShelterInfo() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Обзор приюта")
                .callbackData(CLICK_DOG_SHELTER_OVERVIEW));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Оформить пропуск для автомобиля")
                .callbackData(CLICK_CAR_PASS_DOG));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Техника безопасности")
                .callbackData(CLICK_SAFETY_PRECAUTIONS_DOG));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Записаться на посещение")
                .callbackData(CLICK_VISIT_DOG));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера")
                .callbackData(CLICK_CALL_A_VOLUNTEER));

        return inlineKeyboardMarkup;
    }

    @Override
    public InlineKeyboardMarkup createButtonsCatShelterTake() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Правила знакомства с питомцем")
                .callbackData(CLICK_GREETING_WITH_CAT));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Документы для забора питомца")
                .callbackData(CLICK_DOC_CAT));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Как транспортировать питомца")
                .callbackData(CLICK_TRANSPORT_CAT));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Обустройство дома для питомца")
                .callbackData(CLICK_ARRANGEMENT_CAT_HOME));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Причины отказа")
                .callbackData(CLICK_REASONS_FOR_REFUSAL_CAT));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера")
                .callbackData(CLICK_CALL_A_VOLUNTEER));

        return inlineKeyboardMarkup;
    }

    @Override
    public InlineKeyboardMarkup createButtonsDogShelterTake() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Правила знакомства с питомцем")
                .callbackData(CLICK_GREETING_WITH_DOG));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Документы для забора питомца")
                .callbackData(CLICK_DOC_DOG));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Как транспортировать питомца")
                .callbackData(CLICK_TRANSPORT_DOG));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Обустройство дома для питомца")
                .callbackData(CLICK_ARRANGEMENT_DOG_HOME));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Причины отказа")
                .callbackData(CLICK_REASONS_FOR_REFUSAL_DOG));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Советы кинолога")
                .callbackData(CLICK_CYTOLOGIST_ADVICE));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Найти кинолога")
                .callbackData(CLICK_FIND_CYNOLOGIST));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера")
                .callbackData(CLICK_CALL_A_VOLUNTEER));

        return inlineKeyboardMarkup;
    }

    @Override
    public InlineKeyboardMarkup createButtonsCatShelterReport() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Правила отправки отчета")
                .callbackData(CLICK_RULES_REPORT_CAT));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Отправить отчёт")
                .callbackData(CLICK_REPORT_CAT));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера")
                .callbackData(CLICK_CALL_A_VOLUNTEER));

        return inlineKeyboardMarkup;
    }

    @Override
    public InlineKeyboardMarkup createButtonsDogShelterReport() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Правила отправки отчета")
                .callbackData(CLICK_RULES_REPORT_DOG));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Отправить отчёт")
                .callbackData(CLICK_REPORT_DOG));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера")
                .callbackData(CLICK_CALL_A_VOLUNTEER));

        return inlineKeyboardMarkup;
    }

    @Override
    public InlineKeyboardMarkup createButtonsCatAtHome() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Котенок")
                .callbackData(CLICK_KITTY));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Взрослый кот")
                .callbackData(CLICK_CAT_BIG));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("С ограниченными возможностями")
                .callbackData(CLICK_CAT_INVALID));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера")
                .callbackData(CLICK_CALL_A_VOLUNTEER));

        return inlineKeyboardMarkup;
    }

    @Override
    public InlineKeyboardMarkup createButtonsDogAtHome() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Щенок")
                .callbackData(CLICK_PUPPY));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Взрослый пес")
                .callbackData(CLICK_DOG_BIG));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("С ограниченными возможностями")
                .callbackData(CLICK_DOG_INVALID));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера")
                .callbackData(CLICK_CALL_A_VOLUNTEER));

        return inlineKeyboardMarkup;
    }
}
