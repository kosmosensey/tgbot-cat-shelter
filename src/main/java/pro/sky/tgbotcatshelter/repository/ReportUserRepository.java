package pro.sky.tgbotcatshelter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sky.tgbotcatshelter.constants.StatusReport;
import pro.sky.tgbotcatshelter.entity.ReportUser;
import pro.sky.tgbotcatshelter.entity.User;

import java.time.LocalDate;

@Repository
public interface ReportUserRepository extends JpaRepository<ReportUser, Long> {
    @Modifying
    @Query("UPDATE ReportUser r SET " +
           "r.dateEndOfProbation = :date_end_of_probation " +
           " WHERE r.telegramId = :telegram_id")
    void updateDateEndOfProbationById(
            @Param("telegram_id") User user,
            @Param("date_end_of_probation") LocalDate dateEndOfProbation);

    @Modifying
    @Query("UPDATE ReportUser r SET " +
           "r.statusReport = :status_report " +
           " WHERE r.telegramId = :telegram_id")

    void updateStatusReportById(
            @Param("telegram_id") User user,
            @Param("status_report") StatusReport statusReport);
}