package pro.sky.tgbotcatshelter.entity;

import jakarta.persistence.*;
import pro.sky.tgbotcatshelter.constants.StatusReport;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;


@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "date_report")
    private LocalDate dateReport;

    @Column(name = "status_report")
    private StatusReport statusReport;

    @Column(name = "date_end_of_probation")
    private LocalDate dateEndOfProbation;

    @Column(name = "report_text")
    private String reportText;

    @Column(name = "link_picture")
    private byte[] picture;

    public Report(User userId,
                  LocalDate dateReport,
                  StatusReport statusReport,
                  LocalDate dateEndOfProbation,
                  String reportText,
                  byte[] picture) {
        this.userId = userId;
        this.dateReport = dateReport;
        this.statusReport = statusReport;
        this.dateEndOfProbation = dateEndOfProbation;
        this.reportText = reportText;
        this.picture = picture;
    }

    public Report(LocalDate dateEndOfProbation) {
        this.dateEndOfProbation = dateEndOfProbation;
    }

    public Report(User userId,
                  LocalDate dateReport,
                  StatusReport statusReport,
                  String reportText,
                  byte[] picture) {
        this.userId = userId;
        this.dateReport = dateReport;
        this.statusReport = statusReport;
        this.reportText = reportText;
        this.picture = picture;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public LocalDate getDateReport() {
        return dateReport;
    }

    public void setDateReport(LocalDate dateReport) {
        this.dateReport = dateReport;
    }

    public StatusReport getStatusReport() {
        return statusReport;
    }

    public void setStatusReport(StatusReport statusReport) {
        this.statusReport = statusReport;
    }

    public LocalDate getDateEndOfProbation() {
        return dateEndOfProbation;
    }

    public void setDateEndOfProbation(LocalDate dateEndOfProbation) {
        this.dateEndOfProbation = dateEndOfProbation;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return id == report.id && Objects.equals(userId, report.userId) && Objects.equals(dateReport, report.dateReport) && statusReport == report.statusReport && Objects.equals(dateEndOfProbation, report.dateEndOfProbation) && Objects.equals(reportText, report.reportText) && Arrays.equals(picture, report.picture);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, userId, dateReport, statusReport, dateEndOfProbation, reportText);
        result = 31 * result + Arrays.hashCode(picture);
        return result;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", userId=" + userId +
                ", dateReport=" + dateReport +
                ", statusReport=" + statusReport +
                ", dateEndOfProbation=" + dateEndOfProbation +
                ", reportText='" + reportText + '\'' +
                ", picture=" + Arrays.toString(picture) +
                '}';
    }
}
