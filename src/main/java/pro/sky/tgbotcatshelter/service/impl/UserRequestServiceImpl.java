package pro.sky.tgbotcatshelter.service.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.tgbotcatshelter.constants.UserStatus;
import pro.sky.tgbotcatshelter.constants.UserType;
import pro.sky.tgbotcatshelter.entity.ReportUser;
import pro.sky.tgbotcatshelter.entity.User;
import pro.sky.tgbotcatshelter.listener.TgBotCatShelterUpdatesListener;
import pro.sky.tgbotcatshelter.service.InlineKeyboardMarkupService;
import pro.sky.tgbotcatshelter.service.ReportUserService;
import pro.sky.tgbotcatshelter.service.UserRequestService;
import pro.sky.tgbotcatshelter.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static pro.sky.tgbotcatshelter.constants.Messages.*;


/**
 * Сервис для обработки запросов от пользователей.
 */
@Service
public class UserRequestServiceImpl implements UserRequestService {

    private Map<Long, Boolean> reportStateByChatId = new HashMap<>();
    private Map<Long, Boolean> updateUserInfoStateByChatId = new HashMap<>();
    private final Pattern pattern = Pattern.compile("(^[А-я]+)\\s+([А-я]+)\\s+(\\d{11}$)");
    private final InlineKeyboardMarkupService inlineKeyboardMarkupService;
    private final Logger logger = LoggerFactory.getLogger(TgBotCatShelterUpdatesListener.class);
    private final TelegramBot telegramBot;
    private final UserService userService;
    final Map<Long, UserType> userCatAndDogStateByChatId = new HashMap<>();
    private final Map<Long, String> stateByChatId = new HashMap<>();

    private final ReportUserService reportUserService;




    /**
     * Конструктор класса.
     *
     * @param
     * @param inlineKeyboardMarkupService сервис для создания inline-клавиатуры.
     * @param telegramBot                 бот для отправки сообщений.
     * @param userService                 сервис для работы с пользователями.
     * @param reportUserService
     */
    public UserRequestServiceImpl(InlineKeyboardMarkupService inlineKeyboardMarkupService,
                                  TelegramBot telegramBot,
                                  UserService userService,
                                  ReportUserService reportUserService) {
        this.inlineKeyboardMarkupService = inlineKeyboardMarkupService;
        this.telegramBot = telegramBot;
        this.userService = userService;
        this.reportUserService = reportUserService;
    }

    /**
     * Отправляет приветственное сообщение новому пользователю при команде /start.
     *
     * @param update объект, содержащий информацию о входящем сообщении.
     */
    @Override
    public void sendMessageStart(Update update) {

        Message message = update.message();
        Long chatId = message.from().id();
        String text = message.text();
        String userName = update.message().from().username();
        long telegramId = update.message().from().id();

        if (Boolean.TRUE.equals(updateUserInfoStateByChatId.get(chatId))) {
            updateUser(update);
            updateUserInfoStateByChatId.remove(chatId);
        } else if (Boolean.TRUE.equals(reportStateByChatId.get(chatId))) {
            takeReportFromUser(update);
            reportStateByChatId.remove(chatId);

        } else if ("/start".equals(text)) {

            User user = userService.findUserByTelegramId(telegramId);

            if (user == null) {
                // Приветствие нового пользователя
                greetingNewUser(chatId, userName);
                // Добавление нового пользователя в базу данных
                userService.addUser(telegramId, userName, UserType.DEFAULT, UserStatus.APPROVE);

            } else if (user.getUserType() == UserType.DEFAULT && user.getUserStatus() == UserStatus.APPROVE) {
                // Приветствие пользователя, уже записан в системе
                greetingNotNewUser(chatId, userName);
            }
        }
    }

    /**
     * Отправляет приветственное сообщение новому пользователю.
     *
     * @param chatId   идентификатор чата пользователя.
     * @param userName имя пользователя.
     */
    private void greetingNewUser(Long chatId, String userName) {
        SendMessage sendMessage =
                new SendMessage(chatId, String.format(GREETINGS_NEW_USER, userName));

        // Создание inline-клавиатуры для выбора типа приюта
        sendMessage.replyMarkup(inlineKeyboardMarkupService.createButtonsShelterTypeSelect());

        // Отправка сообщения
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during sending message: {}", sendResponse.description());
        }
    }

    /**
     * Отправляет приветственное сообщение пользователю, уже зарегистрированному в системе.
     *
     * @param chatId   идентификатор чата пользователя.
     * @param userName имя пользователя.
     */
    private void greetingNotNewUser(Long chatId, String userName) {
        SendMessage sendMessage =
                new SendMessage(chatId, String.format(GREETINGS_USER, userName));

        // Создание inline-клавиатуры для выбора типа приюта
        sendMessage.replyMarkup(inlineKeyboardMarkupService.createButtonsShelterTypeSelect());

        // Отправка сообщения
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during sending message: {}", sendResponse.description());
        }
    }


    @Override
    public void updateUser(Update update) {

    }

    /**
     * метод отвечает за загрузку отчета от усыновителя.
     *
     * @param update
     * @param
     */
    @Override
    public void takeReportFromUser(Update update) {
        String reportText = update.message().caption();
        GetFile getFile = new GetFile(update.message().photo()[update.message().photo().length - 1].fileId());
        GetFileResponse response = telegramBot.execute(getFile);
        String imageUrl = telegramBot.getFullFilePath(response.file());
        Long chatId = update.message().chat().id();
        long telegramId = update.message().from().id();
        telegramBot.execute(new SendMessage(chatId, """
                Отправь, пожалуйста, следующую информацию о животном:
                Рацион животного:
                Общее самочувствие и привыкание к новому месту:
                Изменение в поведении: отказ от старых привычек, приобретение новых:"""));
        if (imageUrl != null && reportText != null) {
            ReportUser newReport = new ReportUser();
            newReport.setText(reportText);
            newReport.setPhotoPath(imageUrl);
//            newReport.setTelegramId(telegramId); добавится телеграмАйди в отчете после рефаторинга
            SendMessage message = new SendMessage(chatId, "Спасибо за отчёт, результат проверки узнаете в течение дня!");
            telegramBot.execute(message);
            reportUserService.createReportUser(newReport);
        } else {
            SendMessage message = new SendMessage(chatId, "Некорректный формат отчета!");
            telegramBot.execute(message);
        }
    }

    @Override
    public void createButtonClick(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        if (callbackQuery != null) {
            long chatId = callbackQuery.message().chat().id();
            String data = callbackQuery.data();
            switch (data) {
                case CLICK_CAT_SHELTER:

                    getCatShelterClick(chatId);

                    break;
                case CLICK_DOG_SHELTER:

                    getDogShelterClick(chatId);

                    break;
                case CLICK_CAT_SHELTER_INFO:

                    getCatShelterInfoClick(chatId);

                    break;
                case CLICK_DOG_SHELTER_INFO:

                    getDogShelterInfoClick(chatId);

                    break;
                case CLICK_HOW_TO_ADOPT_A_CAT:

                    getCatShelterTakeClick(chatId);

                    break;
                case CLICK_HOW_TO_ADOPT_A_DOG:

                    getDogShelterTakeClick(chatId);

                    break;
                case CLICK_SEND_A_CAT_REPORT:

                    getCatShelterReportClick(chatId);

                    break;
                case CLICK_SEND_A_DOG_REPORT:

                    getDogShelterReportClick(chatId);

                    break;
                case CLICK_CALL_A_VOLUNTEER: {

                    stateByChatId.put(chatId, CLICK_CALL_A_VOLUNTEER);
                    SendMessage sendMessage = new SendMessage(chatId, CALL_VOLUNTEER);
                    sendMessage(sendMessage);
                    break;
                }
                case CLICK_ARRANGEMENT_CAT_HOME:

                    getCatAtHomeClick(chatId);

                    break;
                case CLICK_ARRANGEMENT_DOG_HOME:

                    getDogAtHomeClick(chatId);

                    break;
                case CLICK_VISIT_CAT, CLICK_VISIT_DOG:
                    userCatAndDogStateByChatId.put(chatId, UserType.GUEST);
                    SendMessage sendMessage = new SendMessage(chatId, """
                            Для записи на посещение, нужно заполнить анкету:
                            Напишите Ваше Имя, номер телефона(без кода страны)
                            Пример:Иван 999-888-5555 A321AA""");
                    sendMessage(sendMessage);
                    break;

                        case CLICK_REPORT_CAT, CLICK_REPORT_DOG:
                            telegramBot.execute(new SendMessage(chatId, """
                        Отправьте отчет о питомце::
                        - Фото питомца;
                            - Рацион питомца;
                            - Общее самочувствие и привыкание к новому мету;
                            - Изменение в поведении (если есть)."""));
                            reportStateByChatId.put(chatId, true);

                    break;
                case CLICK_RULES_REPORT_CAT, CLICK_RULES_REPORT_DOG:
                    sendMessage(chatId, """
                            После того, как новый опекун забрал животное из приюта, ему требуется в течение первого месяца предоставлять обновления о благополучии и комфорте животного на новом месте.
                            В рамках ежедневного отчёта:
                                - Фото животного.
                                - Рацион животного.
                                - Общее самочувствие и привыкание к новому месту.
                                - Изменение в поведении: отказ от старых привычек, приобретение новых.
                            Отчет нужно присылать каждый день. Каждый день волонтеры проверяют отправленные отчёты до конца рабочего дня.
                            Не забывайте, это очень важно!""");

                    break;
                case CLICK_CAT_SHELTER_OVERVIEW:

                    sendMessage(chatId, """
                            Адрес приюта для кошек: улица Пушкина, Дом: Колотушкина.
                            Время работы с 10:00 до 19:00. Время приёма гостей с 12 до 15
                            Тел. приюта : +7 (800) 555-35-35
                            Телефон охраны: +7 (800) 555-35-35 (777)""");
                    break;
                case CLICK_DOG_SHELTER_OVERVIEW:

                    sendMessage(chatId, """
                            Адрес приюта для собак : улица Колотушкина, Дом: Пушкина.
                            Время работы с 10:00 до 19:00. Время приёма гостей с 12 до 15
                            Тел. приюта : +7 (800) 555-35-35
                            Телефон охраны: +7 (800) 555-35-35 (777)""");

                    break;
                case CLICK_SAFETY_PRECAUTIONS_DOG, CLICK_SAFETY_PRECAUTIONS_CAT:
                    sendMessage(chatId, """
                            Инструкции поведения в приюте:
                                                        
                            1.Мытье рук
                            Перед и после взаимодействия с животными используйте станции для мытья рук. Это поможет предотвратить передачу бактерий и сохранит чистоту.
                                                        
                            2.Осторожность с кормлением
                            Не кормите животных без разрешения сотрудников. У них может быть особая диета, и неправильное кормление может повредить их здоровью.
                                                        
                            3.Осторожный контакт
                            Подходите к животному осторожно. Некоторые из них могут быть пугливыми или агрессивными. Если собака выглядит напуганной, лучше дать ей пространство.
                                                        
                            4.Безопасные игрушки
                            Используйте только предоставленные приютом игрушки для взаимодействия с животными. Это обеспечит безопасность как для вас, так и для нашего друга.
                                                        
                            5.Соблюдение указанных зон
                            Соблюдайте указанные зоны доступа. Некоторые места могут быть ограничены для предотвращения конфликтов между животными или для их отдыха.
                                                        
                            6.Следуйте инструкциям сотрудников
                            Если у вас возникли вопросы или замечания, обратитесь к сотрудникам приюта. Они готовы помочь и предоставить необходимую информацию.
                                                        
                            7.Безопасность детей
                            Если вы пришли с детьми, убедитесь, что они также следуют правилам безопасности и не беспокоят животных без ведома взрослых.
                                                        
                            Эти простые меры помогут сделать ваш визит в приют безопасным и приятным для вас и для нас.""");
                    break;
                case CLICK_CAR_PASS_CAT:

                    sendMessage(chatId, """
                            Чтобы оформить пропуск для вашей машины, пожалуйста, предоставьте следующую информацию:

                            - ФИО владельца машины.
                            - Марка и модель автомобиля.
                            - Государственный номер автомобиля.
                            Вы можете отправить эту информацию по электронному адресу dog_priut@mail.ru.
                            Пропуск оформляется минимум за день и не будет подготовлен если вы планируете посетить нас в тот же день.""");

                    break;
                case CLICK_CAR_PASS_DOG:

                    sendMessage(chatId, """
                            Чтобы оформить пропуск для вашей машины, пожалуйста, предоставьте следующую информацию:

                            - ФИО владельца машины.
                            - Марка и модель автомобиля.
                            - Государственный номер автомобиля.
                            Вы можете отправить эту информацию по электронному адресу cat_priut@mail.ru.
                            Пропуск оформляется минимум за день и не будет подготовлен если вы планируете посетить нас в тот же день.""");

                    break;
                case CLICK_GREETING_WITH_CAT, CLICK_GREETING_WITH_DOG: {
                    String text = """
                            Перед встречей с вашим будущим спутником, пожалуйста, ознакомьтесь с несколькими важными правилами:
                                                        
                            Терпение и нежность: Каждое животное уникально. Дайте ему время привыкнуть к вам и новой обстановке. Будьте терпеливы и нежны.
                                                        
                            Предоставьте пространство: Некоторые животные могут быть напуганными или робкими. Позвольте им сделать первый шаг.
                                                        
                            Соблюдайте указанные правила: Важно соблюдать указанные сотрудниками приюта правила поведения с конкретным животным. Это поможет избежать недопониманий и стресса.
                                                        
                            Благодарим Вас за понимание!""";
                    InlineKeyboardMarkup buttons = new InlineKeyboardMarkup();
                    sendMessage2(chatId, text, buttons);
                    break;
                }
                case CLICK_DOC_CAT, CLICK_DOC_DOG: {
                    String text = """
                            Прежде чем забрать своего нового четвероногого друга из нашего приюта, убедитесь, что у вас есть все необходимые документы:
                                                        
                            Документ удостоверяющий личность: Пожалуйста, приготовьте копию вашего паспорта.
                                                        
                            И это всё. Просто не правда ли?""";
                    InlineKeyboardMarkup buttons = new InlineKeyboardMarkup();
                    sendMessage2(chatId, text, buttons);
                    break;
                }
                case CLICK_TRANSPORT_CAT, CLICK_TRANSPORT_DOG: {
                    String text = """
                            Планируете транспортировку вашего нового друга? Вот несколько рекомендаций, чтобы сделать переезд для него максимально комфортным:
                                                        
                            Безопасность в транспортном средстве: Обеспечьте безопасность вашего питомца во время перевозки. Используйте специальные переноски, ремни безопасности или сетки в автомобиле.
                                                        
                            Комфорт в переноске: Если вы используете переноску, удостоверьтесь, что она достаточно просторна для вашего животного.
                                                        
                            Подготовка к поездке: Забирать не обязательно в этот же день. Подготовьте животное к переезду. Приносите переноску к нам, чтобы питомец мог осмотреть и привыкнуть к ней.
                                                        
                            Уход за комфортом: Перед поездкой удостоверьтесь, что в переноске есть необходимые предметы: вода, миска, игрушки.""";
                    InlineKeyboardMarkup buttons = new InlineKeyboardMarkup();
                    sendMessage2(chatId, text, buttons);
                    break;
                }
                case CLICK_REASONS_FOR_REFUSAL_CAT: {
                    String text = """
                            Кажется завести кота просто и мы расскажем, по которым приют может прийти отказать в усыновлении питомца:
                                                        
                            Отсутствие времени и ухода: Если вы не можете обеспечить достаточно времени. Кошачьим иногда нужно много внимания, а иногда они максимально спокойны и ведут тихий образ жизни.
                                                        
                            Несовместимость с другими животными: Если у вас уже есть другие домашние питомцы, их характер и поведение могут быть несовместимы с новым приобретением.
                                                        
                            Неопределенные финансовые возможности: Если у вас нет стабильного и устойчивого финансового положения для обеспечения ухода, кормления и ветеринарного ухода за собакой.
                                                        
                            Потенциальные проблемы с жильем: Если в вашем месте проживания существуют ограничения или запреты на содержание домашних животных.""";
                    InlineKeyboardMarkup buttons = new InlineKeyboardMarkup();
                    sendMessage2(chatId, text, buttons);
                    break;
                }
                case CLICK_REASONS_FOR_REFUSAL_DOG: {
                    String text = """
                            Понимаем, что усыновление собаки - важное решение. Однако, есть несколько причин, по которым приют может отказать в предоставлении питомца:
                                                              
                            Неподготовленное жилье: Если жилищные условия не соответствуют требованиям для содержания конкретной породы или размера собаки.
                                                              
                            Отсутствие времени и ухода: Если вы не можете обеспечить достаточно времени для прогулок, заботы и внимания, что может повлиять на благополучие собаки.
                                                              
                            Несовместимость с другими животными: Если у вас уже есть другие домашние питомцы, их характер и поведение могут быть несовместимы с новым приобретением.
                                                              
                            Неопределенные финансовые возможности: Если у вас нет стабильного и устойчивого финансового положения для обеспечения ухода, кормления и ветеринарного ухода за собакой.
                                                              
                            Отсутствие опыта собачьего воспитания: Если у вас недостаточно опыта в уходе за собаками, особенно если это особенная порода с конкретными требованиями.
                                                              
                            Потенциальные проблемы с жильем: Если в вашем месте проживания существуют ограничения или запреты на содержание домашних животных.
                                                              
                            Несовершеннолетний возраст: Если вы не достигли совершеннолетия и не можете принимать независимых решений относительно ухода за животным.""";
                    InlineKeyboardMarkup buttons = new InlineKeyboardMarkup();
                    sendMessage2(chatId, text, buttons);
                    break;
                }
                case CLICK_CYTOLOGIST_ADVICE: {
                    String text = """
                            Будьте спокойными и уверенными: Подходите к собаке с уверенностью и спокойствием, избегая резких движений.
                                                        
                            Используйте позитивные сигналы: Используйте мягкий голос, улыбку и легкое похлопывание, чтобы создать положительное первое впечатление.
                                                        
                            Слушайте сигналы собаки: Внимательно следите за поведением собаки, реагируйте на ее настроение, и уважайте ее личное пространство.
                            
                            За более подробной консультацией обращайтесь к специалистам.
                            Дополнительный забавный материал который может ответить на веши вопросы: https://youtu.be/dGLOfSH18ms?si=JXDPUB1_H7Yfbsh4""";
                    InlineKeyboardMarkup buttons = new InlineKeyboardMarkup();
                    sendMessage2(chatId, text, buttons);
                    break;
                }
                case CLICK_FIND_CYNOLOGIST: {
                    String text = """
                            Консультация военных кинологов:
                            https://masterdog.ru/konsultatsiya-kinologa
                            
                            Консультация Московских кинологов:
                            https://guldog.ru/consultation
                            
                            Кинологи нашего города:
                            https://k-9.kz/nursultan_kcentre/lp/""";
                    InlineKeyboardMarkup buttons = new InlineKeyboardMarkup();
                    sendMessage2(chatId, text, buttons);
                    break;
                }
                case CLICK_PUPPY: {
                    String text = """
                            Обеспечьте своему щенку безопасность, разнообразные занятия и свое уютное место - и вы создадите ему счастливый и комфортный дом.
                            
                            Безопасное пространство: Оградите опасные зоны, уберите провода и мелкие предметы, создайте безопасное место для отдыха.
                                                        
                            Игрушки и развлечения: Предоставьте разнообразные игрушки для развития и развлечения щенка, помогая ему снять стресс и скуку.
                                                        
                            Место для обучения и отдыха: Определите уголок для обучения и отдыха, где будут миски, место для сна и тренировок.""";
                    InlineKeyboardMarkup buttons = new InlineKeyboardMarkup();
                    sendMessage2(chatId, text, buttons);
                    break;
                }
                case CLICK_DOG_BIG: {
                    String text = """
                            Предоставьте ему всё для активной жизни, заботьтесь о его питании и уделяйте внимание, чтобы поддерживать здоровье и счастье.
                            Комфортное место для отдыха: Предоставьте взрослому псу мягкое и уютное место для сна, где он может почувствовать себя безопасно.
                                                        
                            Регулярные прогулки и физическая активность: Обеспечьте достаточное количество прогулок и игр на свежем воздухе для поддержания физической активности и здоровья.
                                                        
                            Качественное питание и режим кормления: Выберите сбалансированный рацион для взрослых собак, соблюдая регулярные приемы пищи и контролируя порции.
                                                        
                            Внимание и общение: Посвящайте время вашему псу, играйте с ним, тренируйте его и уделяйте внимание, чтобы поддерживать эмоциональное благополучие.""";
                    InlineKeyboardMarkup buttons = new InlineKeyboardMarkup();
                    sendMessage2(chatId, text, buttons);
                    break;
                }
                case CLICK_KITTY: {
                    String text = """
                            Сделайте свой дом уютным и безопасным для котенка, предоставив ему мягкое место для отдыха, разнообразные игрушки и высокие точки для наблюдения за окружающим миром.
                            Уютное гнездо: Создайте теплое место для отдыха котенка с мягкой подстилкой, подушками или одеялом.
                                                        
                            Игрушки и забавы: Обеспечьте разнообразные игрушки, скретч-посты и зоны для пряток, чтобы развивать активность и удовлетворять инстинкты.
                                                        
                            Безопасность и высокие места: Убедитесь, что окна и балконы надежно закрыты, и предоставьте высокие площадки для наблюдения, чтобы котенок мог чувствовать себя в безопасности.""";
                    InlineKeyboardMarkup buttons = new InlineKeyboardMarkup();
                    sendMessage2(chatId, text, buttons);
                    break;
                }
                case CLICK_CAT_BIG: {
                    String text = """
                            Создайте домашнюю атмосферу, где ваш кот может наслаждаться комфортом, активными занятиями обеспечивая ему счастливую жизнь.
                            Комфортная зона отдыха: Обеспечьте взрослому коту мягкое и теплое место для отдыха с подушками или ковриком.
                                                        
                            Развлечения и игры: Предоставьте разнообразные игрушки, скретч-посты и места для пряток, чтобы поддерживать физическую активность и развивать инстинкты.
                                                        
                            Кормление и вода: Выбирайте качественные корма, соответствующие потребностям взрослого кота, и убедитесь, что миски с водой всегда доступны.
                                                        
                            Безопасность и высота: Убедитесь, что окна и балконы закрыты, и создайте высокие точки для наблюдения, где кот может чувствовать себя в безопасности.""";
                    InlineKeyboardMarkup buttons = new InlineKeyboardMarkup();
                    sendMessage2(chatId, text, buttons);
                    break;
                }
                case CLICK_CAT_INVALID, CLICK_DOG_INVALID: {
                    String text = """
                            Специализированные устройства: Рассмотрите использование специальных устройств, таких как коляски или переносные поднятые миски для кормления, чтобы обеспечить удобство передвижения и питания.
                                                        
                            Физическая терапия(При необходимости): Проводите регулярные упражнения и физическую терапию под наблюдением ветеринара, чтобы поддерживать здоровье и подвижность инвалидного животного.
                                                        
                            Адаптированный дом: Внесите изменения в дом, такие как плавные покрытия пола, чтобы облегчить передвижение, или создайте специальные рампы для доступа к разным уровням.
                                                        
                            Специальный режим питания: Разработайте индивидуализированный рацион, учитывая особенности здоровья инвалидного животного, и предоставляйте легкодоступные поилки.
                                                        
                            Эмоциональная поддержка: Обеспечьте дополнительное внимание и ласку, так как инвалидные животные могут нуждаться в большем эмоциональном комфорте и поддержке.
                                                        
                            Все эти меры направлены на обеспечение полноценной и комфортной жизни для животного с ограниченными возможностями. Важно консультироваться с ветеринаром для индивидуального подхода к заботе об инвалидном животном.""";
                    InlineKeyboardMarkup buttons = new InlineKeyboardMarkup();
                    sendMessage2(chatId, text, buttons);
                    break;
                }
            }
        }
    }

    // Отправляет сообщение с встроенной клавиатурой для выбора консультации как обустроить дом для собаки.
    private void getDogAtHomeClick(long chatId) {
        SendMessage sendMessage = new SendMessage(chatId, CHOSE);
        sendMessage.replyMarkup(inlineKeyboardMarkupService.createButtonsDogAtHome());
        sendMessage(sendMessage);
    }

    // Отправляет сообщение с встроенной клавиатурой для выбора консультации как обустроить дом для котов.
    private void getCatAtHomeClick(long chatId) {
        SendMessage sendMessage = new SendMessage(chatId, CHOSE);
        sendMessage.replyMarkup(inlineKeyboardMarkupService.createButtonsCatAtHome());
        sendMessage(sendMessage);
    }

    // Отправляет сообщение с встроенной клавиатурой для отправки отчета о собаке в приют
    private void getDogShelterReportClick(long chatId) {
        SendMessage sendMessage = new SendMessage(chatId, CHOSE);
        sendMessage.replyMarkup(inlineKeyboardMarkupService.createButtonsDogShelterReport());
        sendMessage(sendMessage);
    }

    // Отправляет сообщение с встроенной клавиатурой для отправки отчета о кошке в приют
    private void getCatShelterReportClick(long chatId) {
        SendMessage sendMessage = new SendMessage(chatId, CHOSE);
        sendMessage.replyMarkup(inlineKeyboardMarkupService.createButtonsCatShelterReport());
        sendMessage(sendMessage);
    }

    // Отправляет сообщение с встроенной клавиатурой для взятия собаки из приюта
    private void getDogShelterTakeClick(long chatId) {
        SendMessage sendMessage = new SendMessage(chatId, CHOSE);
        sendMessage.replyMarkup(inlineKeyboardMarkupService.createButtonsDogShelterTake());
        sendMessage(sendMessage);
    }

    // Отправляет сообщение с встроенной клавиатурой для взятия кошки из приюта
    private void getCatShelterTakeClick(long chatId) {
        SendMessage sendMessage = new SendMessage(chatId, CHOSE);
        sendMessage.replyMarkup(inlineKeyboardMarkupService.createButtonsCatShelterTake());
        sendMessage(sendMessage);
    }

    // Отправляет сообщение, используя объект SendMessage
    private void sendMessage(SendMessage sendMessage) {
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (sendResponse != null && !sendResponse.isOk()) {
            logger.error("Ошибка при отправке сообщения: {}", sendResponse.description());
        }
    }

    // Отправляет сообщение с встроенной клавиатурой для выбора приюта для собак
    private void getDogShelterClick(long chatId) {
        SendMessage sendMessage = new SendMessage(chatId, CHOSE);
        sendMessage.replyMarkup(inlineKeyboardMarkupService.createButtonsDogShelter());
        sendMessage(sendMessage);
    }

    // Отправляет сообщение с встроенной клавиатурой для выбора приюта для кошек
    private void getCatShelterClick(long chatId) {
        SendMessage sendMessage = new SendMessage(chatId, CHOSE);
        sendMessage.replyMarkup(inlineKeyboardMarkupService.createButtonsCatShelter());
        sendMessage(sendMessage);
    }

    // Отправляет сообщение с встроенной клавиатурой для получения информации о приюте для кошек
    private void getCatShelterInfoClick(long chatId) {
        SendMessage sendMessage = new SendMessage(chatId, CHOSE);
        sendMessage.replyMarkup(inlineKeyboardMarkupService.createButtonsCatShelterInfo());
        sendMessage(sendMessage);
    }

    // Отправляет сообщение с встроенной клавиатурой для получения информации о приюте для собак
    private void getDogShelterInfoClick(long chatId) {
        SendMessage sendMessage = new SendMessage(chatId, CHOSE);
        sendMessage.replyMarkup(inlineKeyboardMarkupService.createButtonsDogShelterInfo());
        sendMessage(sendMessage);
    }

    // Отправляет простое текстовое сообщение в указанный чат
    private void sendMessage(long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Ошибка при отправке сообщения: {}", sendResponse.description());
        }
    }

    // Отправляет сообщение, используя объект SendMessage
    private void sendMessage1(SendMessage message) {
        SendResponse sendResponse = telegramBot.execute(message);
        if (!sendResponse.isOk()) {
            logger.error("Ошибка при отправке сообщения: {}", sendResponse.description());
        }
    }

    // Отправляет сообщение с встроенной клавиатурой, используя указанные параметры
    private void sendMessage2(long chatId, String text, InlineKeyboardMarkup buttons) {
        SendMessage message = new SendMessage(chatId, text);
        message.replyMarkup(buttons);
        sendMessage1(message);
    }
}
