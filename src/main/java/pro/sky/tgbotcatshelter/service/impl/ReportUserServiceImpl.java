package pro.sky.tgbotcatshelter.service.impl;

import com.pengrad.telegrambot.model.Update;
import org.junit.platform.commons.logging.LoggerFactory;
import org.jvnet.hk2.annotations.Service;

import pro.sky.tgbotcatshelter.entity.ReportUser;
import pro.sky.tgbotcatshelter.repository.ReportUserRepository;
import pro.sky.tgbotcatshelter.service.ReportUserService;

import java.util.Collection;
import java.util.logging.Logger;

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
    public void createReportUser(ReportUser reportUser){
        logger.info("Started createReport method");
        reportUserRepository.save(reportUser);
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
}
