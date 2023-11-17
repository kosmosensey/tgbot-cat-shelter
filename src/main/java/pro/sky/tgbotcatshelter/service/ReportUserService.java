package pro.sky.tgbotcatshelter.service;

import com.pengrad.telegrambot.model.Update;
import pro.sky.tgbotcatshelter.entity.ReportUser;

import java.util.Collection;

/**
 * Интерфейс сервиса для работы с отчетами пользователей.
 */
public interface ReportUserService {

    /**
     * Создает отчет пользователя.
     *
     * @param reportUser объект отчета пользователя для создания
     */
    void createReportUser(ReportUser reportUser);

    /**
     * Находит и возвращает все отчеты пользователей.
     *
     * @return коллекция отчетов пользователей
     */
    Collection<ReportUser> findAllReportUser();

    /**
     * Находит отчет пользователя по его идентификатору.
     *
     * @param id идентификатор отчета пользователя
     * @return объект отчета пользователя
     */
    ReportUser findReportUserById(long id);

    /**
     * Получает отчет от пользователя и сохраняет его.
     *
     * @param update объект Update, содержащий информацию об отчете пользователя
     */
    void takeReportFromUser(Update update);
}
