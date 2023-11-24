package pro.sky.tgbotcatshelter.service.impl;

import com.pengrad.telegrambot.model.Update;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.tgbotcatshelter.constants.StatusReport;
import pro.sky.tgbotcatshelter.entity.ReportUser;
import pro.sky.tgbotcatshelter.entity.User;
import pro.sky.tgbotcatshelter.repository.ReportUserRepository;
import pro.sky.tgbotcatshelter.service.ReportUserService;

import java.time.LocalDate;
import java.util.Collection;


@Service
public class ReportUserServiceImpl implements ReportUserService{

    // Инициализация логгера для класса
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ReportUserServiceImpl.class);
    private final ReportUserRepository reportUserRepository;

    // Конструктор, принимающий репозиторий пользовательских отчетов
    public ReportUserServiceImpl(ReportUserRepository reportUserRepository) {
        this.reportUserRepository = reportUserRepository;
    }

    // Метод для создания отчета пользователя
    public void createReportUser(String photoPath,
                                 String reportText,
                                 User telegramId,
                                 StatusReport statusReport,
                                 LocalDate dateReport,
                                 LocalDate dateEndOfProbation){
        logger.info("Started createReport method");
        ReportUser report = new ReportUser(photoPath,
                reportText,
                telegramId,
                statusReport,
                dateReport,
                dateEndOfProbation);
        reportUserRepository.save(report);
    }

    // Метод для поиска всех пользовательских отчетов
    public Collection<ReportUser> findAllReportUser(){
        logger.info("Started findAllReport method");
        return reportUserRepository.findAll();
    }

    // Метод для поиска отчета пользователя по его идентификатору
    @Override
    public ReportUser findReportUserById(long id) {
        return null; // Пока не реализовано
    }

    // Метод для получения отчета от пользователя через объект Update
    @Override
    public void takeReportFromUser(Update update) {
        // Пока не реализовано
    }
    @Override
    @Transactional
    public void updateDateEndOfProbationById(User userId,
                                             LocalDate dateEndOfProbation) {

        reportUserRepository.updateDateEndOfProbationById(userId,
                dateEndOfProbation);
    }
    @Override
    public Collection<ReportUser> getAllReport() {
        return reportUserRepository.findAll();
    }

    @Override
    @Transactional
    public void updateStatusReportById(User userId,
                                       StatusReport statusReport) {

        reportUserRepository.updateStatusReportById(userId,
                statusReport);
    }
}
