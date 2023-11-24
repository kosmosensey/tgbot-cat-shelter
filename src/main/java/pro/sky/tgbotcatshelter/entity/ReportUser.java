package pro.sky.tgbotcatshelter.entity;

import jakarta.persistence.*;
import lombok.*;
import pro.sky.tgbotcatshelter.constants.StatusReport;

import java.time.LocalDate;

/**
 * Класс, представляющий отчет о пользователе приложения.
 */
@Data
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "report_users")
public class ReportUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "photo_path")
    private String photoPath;

    @Column(name = "report_text")
    private String reportText;

    @JoinColumn(name = "telegram_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User telegramId;
    @Column(name = "status_report")
    private StatusReport statusReport;
    @Column(name = "date_report")
    private LocalDate dateReport;
    @Column(name = "date_end_of_probation")
    private LocalDate dateEndOfProbation;

    public ReportUser(String photoPath,
                      String reportText,
                      User telegramId,
                      StatusReport statusReport,
                      LocalDate dateReport,
                      LocalDate dateEndOfProbation) {
        this.photoPath = photoPath;
        this.reportText = reportText;
        this.telegramId = telegramId;
        this.statusReport = statusReport;
        this.dateReport = dateReport;
        this.dateEndOfProbation = dateEndOfProbation;
    }

    public User getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(User telegramId) {
        this.telegramId = telegramId;
    }

    public StatusReport getStatusReport() {
        return statusReport;
    }

    public void setStatusReport(StatusReport statusReport) {
        this.statusReport = statusReport;
    }

    public LocalDate getDateReport() {
        return dateReport;
    }

    public void setDateReport(LocalDate dateReport) {
        this.dateReport = dateReport;
    }

    public LocalDate getDateEndOfProbation() {
        return dateEndOfProbation;
    }

    public void setDateEndOfProbation(LocalDate dateEndOfProbation) {
        this.dateEndOfProbation = dateEndOfProbation;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }
}