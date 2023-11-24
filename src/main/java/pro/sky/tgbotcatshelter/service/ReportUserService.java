package pro.sky.tgbotcatshelter.service;

import com.pengrad.telegrambot.model.Update;
import jakarta.transaction.Transactional;
import pro.sky.tgbotcatshelter.constants.StatusReport;
import pro.sky.tgbotcatshelter.entity.ReportUser;
import pro.sky.tgbotcatshelter.entity.User;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Интерфейс сервиса для работы с отчетами пользователей.
 */
public interface ReportUserService {

    void createReportUser(String photoPath,
                          String reportText,
                          User telegramId,
                          StatusReport statusReport,
                          LocalDate dateReport,
                          LocalDate dateEndOfProbation);

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

    @Transactional
    void updateDateEndOfProbationById(User userId,
                                      LocalDate dateEndOfProbation);
    /**
     * Выводим все отчеты
     *
     * @return список отчетов
     */
    Collection<ReportUser> getAllReport();

    @Transactional
    void updateStatusReportById(User userId,
                                StatusReport statusReport);
}
