package pro.sky.tgbotcatshelter.service;

import com.pengrad.telegrambot.model.Update;
import pro.sky.tgbotcatshelter.entity.ReportUser;

import java.util.Collection;

public interface ReportUserService {

    void createReportUser(ReportUser reportUser);

    Collection<ReportUser> findAllReportUser();

    public ReportUser findReportUserById(long id);

    public void takeReportFromUser(Update update);
}
