package pro.sky.tgbotcatshelter.service;

import jakarta.transaction.Transactional;
import pro.sky.tgbotcatshelter.constants.StatusReport;
import pro.sky.tgbotcatshelter.entity.ReportUser;
import pro.sky.tgbotcatshelter.entity.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

/**
 * Интерфейс сервиса для работы с отчетами пользователей.
 */
public interface ReportUserService {

    ReportUser createReportUser(String photoPath,
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
    Optional<ReportUser> findReportUserById(long id);

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
