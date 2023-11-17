package pro.sky.tgbotcatshelter.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.tgbotcatshelter.constants.StatusReport;
import pro.sky.tgbotcatshelter.entity.Report;
import pro.sky.tgbotcatshelter.entity.User;
import pro.sky.tgbotcatshelter.repository.ReportRepository;
import pro.sky.tgbotcatshelter.repository.UserRepository;
import pro.sky.tgbotcatshelter.service.ReportService;

import java.time.LocalDate;
import java.util.Collection;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    // Конструктор, принимающий репозиторий отчетов
    public ReportServiceImpl(ReportRepository reportRepository, UserRepository userRepository) {
        this.reportRepository = reportRepository;
    }

    // Метод сохраняет отчет в репозитории
    @Override
    public Report saveReport(User userId,
                             LocalDate dateReport,
                             StatusReport statusReport,
                             String reportText,
                             byte[] picture) {

        // Создание нового отчета
        Report report = new Report(userId,
                dateReport,
                statusReport,
                reportText,
                picture);

        // Сохранение отчета в репозитории
        return reportRepository.save(report);
    }

    // Метод удаляет отчет из репозитория по его идентификатору
    @Override
    public void deleteReportById(Long id) {
        reportRepository.deleteById(id);
    }

    // Метод возвращает все отчеты из репозитория
    @Override
    public Collection<Report> getAllReport() {
        return reportRepository.findAll();
    }
}
