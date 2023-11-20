package pro.sky.tgbotcatshelter.entity;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Класс, представляющий отчет о пользователе приложения.
 */
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

    @Column(name = "telegram_id")
    private Long telegramId;

    public ReportUser() {
    }

    public ReportUser(String photoPath, String reportText, Long telegramId) {
        this.photoPath = photoPath;
        this.reportText = reportText;
        this.telegramId = telegramId;
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

    public Long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Long telegramId) {
        this.telegramId = telegramId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportUser that = (ReportUser) o;
        return id == that.id && Objects.equals(photoPath, that.photoPath) && Objects.equals(reportText, that.reportText) && Objects.equals(telegramId, that.telegramId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photoPath, reportText, telegramId);
    }
}