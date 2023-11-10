package pro.sky.tgbotcatshelter.service;

import pro.sky.tgbotcatshelter.constants.StatusReport;
import pro.sky.tgbotcatshelter.entity.Report;
import pro.sky.tgbotcatshelter.entity.User;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Сервис для работы с отчетами о питомцах.
 */
public interface ReportService {

    /**
     * Сохраняет новый отчет о питомце.
     *
     * @param userId       Пользователь, создающий отчет.
     * @param dateReport   Дата создания отчета.
     * @param statusReport Статус отчета.
     * @param reportText   Текст отчета.
     * @param picture      Фотография питомца.
     * @return Созданный отчет.
     */
    Report saveReport(User userId,
                      LocalDate dateReport,
                      StatusReport statusReport,
                      String reportText,
                      byte[] picture);

    /**
     * Удаляет отчет о питомце по идентификатору.
     *
     * @param id Идентификатор отчета.
     */
    void deleteReportById(Long id);

    /**
     * Получает все отчеты о питомцах.
     *
     * @return Коллекция всех отчетов.
     */
    Collection<Report> getAllReport();
}
