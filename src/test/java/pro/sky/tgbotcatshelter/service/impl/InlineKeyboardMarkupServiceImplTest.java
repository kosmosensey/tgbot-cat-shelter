package pro.sky.tgbotcatshelter.service.impl;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static pro.sky.tgbotcatshelter.constants.Messages.*;

public class InlineKeyboardMarkupServiceImplTest {
    @Test
    public void testCreateButtonsShelterTypeSelect() {
        InlineKeyboardMarkupServiceImpl service = new InlineKeyboardMarkupServiceImpl();
        InlineKeyboardMarkup markup = service.createButtonsShelterTypeSelect();
        assertNotNull(markup);
        assertEquals(2, markup.inlineKeyboard().length);

        InlineKeyboardButton button1 = markup.inlineKeyboard()[0][0];
        assertNotNull(button1);
        assertEquals("Приют для кошек", button1.text());
        assertEquals(CLICK_CAT_SHELTER, button1.callbackData());

        InlineKeyboardButton button2 = markup.inlineKeyboard()[1][0];
        assertNotNull(button2);
        assertEquals("Приют для собак", button2.text());
        assertEquals(CLICK_DOG_SHELTER, button2.callbackData());
    }
    @Test
    public void testCreateButtonsCatShelter(){
        InlineKeyboardMarkupServiceImpl service = new InlineKeyboardMarkupServiceImpl();
        InlineKeyboardMarkup markup = service.createButtonsCatShelter();
        assertNotNull(markup);
        assertEquals(4,markup.inlineKeyboard().length);

        InlineKeyboardButton button1 = markup.inlineKeyboard()[0][0];
        assertNotNull(button1);
        assertEquals("Информация о приюте",button1.text());
        assertEquals(CLICK_CAT_SHELTER_INFO, button1.callbackData());

        InlineKeyboardButton button2 = markup.inlineKeyboard()[1][0];
        assertNotNull(button2);
        assertEquals("Как забрать питомца из приюта",button2.text());
        assertEquals(CLICK_HOW_TO_ADOPT_A_CAT, button2.callbackData());

        InlineKeyboardButton button3 = markup.inlineKeyboard()[2][0];
        assertNotNull(button3);
        assertEquals("Отправить отчет о питомце",button3.text());
        assertEquals(CLICK_SEND_A_CAT_REPORT, button3.callbackData());

        InlineKeyboardButton button4 = markup.inlineKeyboard()[3][0];
        assertNotNull(button4);
        assertEquals("Позвать волонтера",button4.text());
        assertEquals(CLICK_CALL_A_VOLUNTEER, button4.callbackData());
    }
    @Test
    public void testCreateButtonsDogShelter(){
        InlineKeyboardMarkupServiceImpl service = new InlineKeyboardMarkupServiceImpl();
        InlineKeyboardMarkup markup = service.createButtonsDogShelter();
        assertNotNull(markup);
        assertEquals(4,markup.inlineKeyboard().length);

        InlineKeyboardButton button1 = markup.inlineKeyboard()[0][0];
        assertNotNull(button1);
        assertEquals("Информация о приюте",button1.text());
        assertEquals(CLICK_DOG_SHELTER_INFO, button1.callbackData());

        InlineKeyboardButton button2 = markup.inlineKeyboard()[1][0];
        assertNotNull(button2);
        assertEquals("Как забрать питомца из приюта",button2.text());
        assertEquals(CLICK_HOW_TO_ADOPT_A_DOG, button2.callbackData());

        InlineKeyboardButton button3 = markup.inlineKeyboard()[2][0];
        assertNotNull(button3);
        assertEquals("Отправить отчет о питомце",button3.text());
        assertEquals(CLICK_SEND_A_DOG_REPORT, button3.callbackData());

        InlineKeyboardButton button4 = markup.inlineKeyboard()[3][0];
        assertNotNull(button4);
        assertEquals("Позвать волонтера",button4.text());
        assertEquals(CLICK_CALL_A_VOLUNTEER, button4.callbackData());
    }

    @Test
    void createButtonsCatShelterInfo() {
        InlineKeyboardMarkupServiceImpl service = new InlineKeyboardMarkupServiceImpl();
        InlineKeyboardMarkup markup = service.createButtonsCatShelterInfo();
        assertNotNull(markup);
        assertEquals(5,markup.inlineKeyboard().length);

        InlineKeyboardButton button1 = markup.inlineKeyboard()[0][0];
        assertNotNull(button1);
        assertEquals("Обзор приюта",button1.text());
        assertEquals(CLICK_CAT_SHELTER_OVERVIEW, button1.callbackData());

        InlineKeyboardButton button2 = markup.inlineKeyboard()[1][0];
        assertNotNull(button2);
        assertEquals("Оформить пропуск для автомобиля",button2.text());
        assertEquals(CLICK_CAR_PASS_CAT, button2.callbackData());

        InlineKeyboardButton button3 = markup.inlineKeyboard()[2][0];
        assertNotNull(button3);
        assertEquals("Техника безопасности",button3.text());
        assertEquals(CLICK_SAFETY_PRECAUTIONS_CAT, button3.callbackData());

        InlineKeyboardButton button4 = markup.inlineKeyboard()[3][0];
        assertNotNull(button4);
        assertEquals("Записаться на посещение",button4.text());
        assertEquals(CLICK_VISIT_CAT, button4.callbackData());

        InlineKeyboardButton button5 = markup.inlineKeyboard()[4][0];
        assertNotNull(button5);
        assertEquals("Позвать волонтера",button5.text());
        assertEquals(CLICK_CALL_A_VOLUNTEER, button5.callbackData());
    }

    @Test
    void createButtonsDogShelterInfo() {
        InlineKeyboardMarkupServiceImpl service = new InlineKeyboardMarkupServiceImpl();
        InlineKeyboardMarkup markup = service.createButtonsDogShelterInfo();
        assertNotNull(markup);
        assertEquals(5,markup.inlineKeyboard().length);

        InlineKeyboardButton button1 = markup.inlineKeyboard()[0][0];
        assertNotNull(button1);
        assertEquals("Обзор приюта",button1.text());
        assertEquals(CLICK_DOG_SHELTER_OVERVIEW, button1.callbackData());

        InlineKeyboardButton button2 = markup.inlineKeyboard()[1][0];
        assertNotNull(button2);
        assertEquals("Оформить пропуск для автомобиля",button2.text());
        assertEquals(CLICK_CAR_PASS_DOG, button2.callbackData());

        InlineKeyboardButton button3 = markup.inlineKeyboard()[2][0];
        assertNotNull(button3);
        assertEquals("Техника безопасности",button3.text());
        assertEquals(CLICK_SAFETY_PRECAUTIONS_DOG, button3.callbackData());

        InlineKeyboardButton button4 = markup.inlineKeyboard()[3][0];
        assertNotNull(button4);
        assertEquals("Записаться на посещение",button4.text());
        assertEquals(CLICK_VISIT_DOG, button4.callbackData());

        InlineKeyboardButton button5 = markup.inlineKeyboard()[4][0];
        assertNotNull(button5);
        assertEquals("Позвать волонтера",button5.text());
        assertEquals(CLICK_CALL_A_VOLUNTEER, button5.callbackData());
    }

    @Test
    void createButtonsCatShelterTake() {
        InlineKeyboardMarkupServiceImpl service = new InlineKeyboardMarkupServiceImpl();
        InlineKeyboardMarkup markup = service.createButtonsCatShelterTake();
        assertNotNull(markup);
        assertEquals(6,markup.inlineKeyboard().length);

        InlineKeyboardButton button1 = markup.inlineKeyboard()[0][0];
        assertNotNull(button1);
        assertEquals("Правила знакомства с питомцем",button1.text());
        assertEquals(CLICK_GREETING_WITH_CAT, button1.callbackData());

        InlineKeyboardButton button2 = markup.inlineKeyboard()[1][0];
        assertNotNull(button2);
        assertEquals("Документы для забора питомца",button2.text());
        assertEquals(CLICK_DOC_CAT, button2.callbackData());

        InlineKeyboardButton button3 = markup.inlineKeyboard()[2][0];
        assertNotNull(button3);
        assertEquals("Как транспортировать питомца",button3.text());
        assertEquals(CLICK_TRANSPORT_CAT, button3.callbackData());

        InlineKeyboardButton button4 = markup.inlineKeyboard()[3][0];
        assertNotNull(button4);
        assertEquals("Обустройство дома для питомца",button4.text());
        assertEquals(CLICK_ARRANGEMENT_CAT_HOME, button4.callbackData());

        InlineKeyboardButton button5 = markup.inlineKeyboard()[4][0];
        assertNotNull(button5);
        assertEquals("Причины отказа",button5.text());
        assertEquals(CLICK_REASONS_FOR_REFUSAL_CAT, button5.callbackData());

        InlineKeyboardButton button6 = markup.inlineKeyboard()[5][0];
        assertNotNull(button6);
        assertEquals("Позвать волонтера",button6.text());
        assertEquals(CLICK_CALL_A_VOLUNTEER, button6.callbackData());
    }

    @Test
    void createButtonsDogShelterTake() {
        InlineKeyboardMarkupServiceImpl service = new InlineKeyboardMarkupServiceImpl();
        InlineKeyboardMarkup markup = service.createButtonsDogShelterTake();
        assertNotNull(markup);
        assertEquals(8,markup.inlineKeyboard().length);

        InlineKeyboardButton button1 = markup.inlineKeyboard()[0][0];
        assertNotNull(button1);
        assertEquals("Правила знакомства с питомцем",button1.text());
        assertEquals(CLICK_GREETING_WITH_DOG, button1.callbackData());

        InlineKeyboardButton button2 = markup.inlineKeyboard()[1][0];
        assertNotNull(button2);
        assertEquals("Документы для забора питомца",button2.text());
        assertEquals(CLICK_DOC_DOG, button2.callbackData());

        InlineKeyboardButton button3 = markup.inlineKeyboard()[2][0];
        assertNotNull(button3);
        assertEquals("Как транспортировать питомца",button3.text());
        assertEquals(CLICK_TRANSPORT_DOG, button3.callbackData());

        InlineKeyboardButton button4 = markup.inlineKeyboard()[3][0];
        assertNotNull(button4);
        assertEquals("Обустройство дома для питомца",button4.text());
        assertEquals(CLICK_ARRANGEMENT_DOG_HOME, button4.callbackData());

        InlineKeyboardButton button5 = markup.inlineKeyboard()[4][0];
        assertNotNull(button5);
        assertEquals("Причины отказа",button5.text());
        assertEquals(CLICK_REASONS_FOR_REFUSAL_DOG, button5.callbackData());

        InlineKeyboardButton button6 = markup.inlineKeyboard()[5][0];
        assertNotNull(button6);
        assertEquals("Советы кинолога",button6.text());
        assertEquals(CLICK_CYTOLOGIST_ADVICE, button6.callbackData());

        InlineKeyboardButton button7 = markup.inlineKeyboard()[6][0];
        assertNotNull(button7);
        assertEquals("Найти кинолога",button7.text());
        assertEquals(CLICK_FIND_CYNOLOGIST, button7.callbackData());

        InlineKeyboardButton button8 = markup.inlineKeyboard()[7][0];
        assertNotNull(button8);
        assertEquals("Позвать волонтера",button8.text());
        assertEquals(CLICK_CALL_A_VOLUNTEER, button8.callbackData());
    }

    @Test
    void createButtonsCatShelterReport() {
        InlineKeyboardMarkupServiceImpl service = new InlineKeyboardMarkupServiceImpl();
        InlineKeyboardMarkup markup = service.createButtonsCatShelterReport();
        assertNotNull(markup);
        assertEquals(3,markup.inlineKeyboard().length);

        InlineKeyboardButton button1 = markup.inlineKeyboard()[0][0];
        assertNotNull(button1);
        assertEquals("Правила отправки отчета",button1.text());
        assertEquals(CLICK_RULES_REPORT_CAT, button1.callbackData());

        InlineKeyboardButton button2 = markup.inlineKeyboard()[1][0];
        assertNotNull(button2);
        assertEquals("Отправить отчёт",button2.text());
        assertEquals(CLICK_REPORT_CAT, button2.callbackData());

        InlineKeyboardButton button3 = markup.inlineKeyboard()[2][0];
        assertNotNull(button3);
        assertEquals("Позвать волонтера",button3.text());
        assertEquals(CLICK_CALL_A_VOLUNTEER, button3.callbackData());
    }

    @Test
    void createButtonsDogShelterReport() {
        InlineKeyboardMarkupServiceImpl service = new InlineKeyboardMarkupServiceImpl();
        InlineKeyboardMarkup markup = service.createButtonsDogShelterReport();
        assertNotNull(markup);
        assertEquals(3,markup.inlineKeyboard().length);

        InlineKeyboardButton button1 = markup.inlineKeyboard()[0][0];
        assertNotNull(button1);
        assertEquals("Правила отправки отчета",button1.text());
        assertEquals(CLICK_RULES_REPORT_DOG, button1.callbackData());

        InlineKeyboardButton button2 = markup.inlineKeyboard()[1][0];
        assertNotNull(button2);
        assertEquals("Отправить отчёт",button2.text());
        assertEquals(CLICK_REPORT_DOG, button2.callbackData());

        InlineKeyboardButton button3 = markup.inlineKeyboard()[2][0];
        assertNotNull(button3);
        assertEquals("Позвать волонтера",button3.text());
        assertEquals(CLICK_CALL_A_VOLUNTEER, button3.callbackData());
    }

    @Test
    void createButtonsCatAtHome() {
        InlineKeyboardMarkupServiceImpl service = new InlineKeyboardMarkupServiceImpl();
        InlineKeyboardMarkup markup = service.createButtonsCatAtHome();
        assertNotNull(markup);
        assertEquals(4,markup.inlineKeyboard().length);

        InlineKeyboardButton button1 = markup.inlineKeyboard()[0][0];
        assertNotNull(button1);
        assertEquals("Котенок",button1.text());
        assertEquals(CLICK_KITTY, button1.callbackData());

        InlineKeyboardButton button2 = markup.inlineKeyboard()[1][0];
        assertNotNull(button2);
        assertEquals("Взрослый кот",button2.text());
        assertEquals(CLICK_CAT_BIG, button2.callbackData());

        InlineKeyboardButton button3 = markup.inlineKeyboard()[2][0];
        assertNotNull(button3);
        assertEquals("С ограниченными возможностями",button3.text());
        assertEquals(CLICK_CAT_INVALID, button3.callbackData());

        InlineKeyboardButton button4 = markup.inlineKeyboard()[3][0];
        assertNotNull(button4);
        assertEquals("Позвать волонтера",button4.text());
        assertEquals(CLICK_CALL_A_VOLUNTEER, button4.callbackData());
    }

    @Test
    void createButtonsDogAtHome() {
        InlineKeyboardMarkupServiceImpl service = new InlineKeyboardMarkupServiceImpl();
        InlineKeyboardMarkup markup = service.createButtonsDogAtHome();assertNotNull(markup);
        assertEquals(4,markup.inlineKeyboard().length);

        InlineKeyboardButton button1 = markup.inlineKeyboard()[0][0];
        assertNotNull(button1);
        assertEquals("Щенок",button1.text());
        assertEquals(CLICK_PUPPY, button1.callbackData());

        InlineKeyboardButton button2 = markup.inlineKeyboard()[1][0];
        assertNotNull(button2);
        assertEquals("Взрослый пес",button2.text());
        assertEquals(CLICK_DOG_BIG, button2.callbackData());

        InlineKeyboardButton button3 = markup.inlineKeyboard()[2][0];
        assertNotNull(button3);
        assertEquals("С ограниченными возможностями",button3.text());
        assertEquals(CLICK_DOG_INVALID, button3.callbackData());

        InlineKeyboardButton button4 = markup.inlineKeyboard()[3][0];
        assertNotNull(button4);
        assertEquals("Позвать волонтера",button4.text());
        assertEquals(CLICK_CALL_A_VOLUNTEER, button4.callbackData());
    }
}
