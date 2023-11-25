package pro.sky.tgbotcatshelter.entity;

import org.junit.jupiter.api.Test;
import pro.sky.tgbotcatshelter.constants.StatusReport;
import pro.sky.tgbotcatshelter.constants.UserStatus;
import pro.sky.tgbotcatshelter.constants.UserType;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportUserTest {

    private final String VALID_PHOTO_PATH = "/path/to/photo.jpg";
    private final String VALID_REPORT_TEXT = "This is a test report.";
    private final User VALID_TELEGRAM_ID = new User(1L, "John Doe", UserType.VOLUNTEER, UserStatus.APPROVE);
    private final StatusReport VALID_STATUS_REPORT = StatusReport.ACCEPTED;
    private final LocalDate VALID_DATE_REPORT = LocalDate.now();
    private final LocalDate VALID_DATE_END_OF_PROBATION = LocalDate.now().plusDays(30);

    private final ReportUser defaultReportUser = new ReportUser(
            VALID_PHOTO_PATH,
            VALID_REPORT_TEXT,
            VALID_TELEGRAM_ID,
            VALID_STATUS_REPORT,
            VALID_DATE_REPORT,
            VALID_DATE_END_OF_PROBATION
    );

    @Test
    public void getPhotoPath_ShouldReturnPhotoPath() {
        assertEquals(VALID_PHOTO_PATH, defaultReportUser.getPhotoPath());
    }

    @Test
    public void getReportText_ShouldReturnReportText() {
        assertEquals(VALID_REPORT_TEXT, defaultReportUser.getReportText());
    }

    @Test
    public void getTelegramId_ShouldReturnTelegramId() {
        assertEquals(VALID_TELEGRAM_ID, defaultReportUser.getTelegramId());
    }

    @Test
    public void getStatusReport_ShouldReturnStatusReport() {
        assertEquals(VALID_STATUS_REPORT, defaultReportUser.getStatusReport());
    }

    @Test
    public void getDateReport_ShouldReturnDateReport() {
        assertEquals(VALID_DATE_REPORT, defaultReportUser.getDateReport());
    }

    @Test
    public void getDateEndOfProbation_ShouldReturnDateEndOfProbation() {
        assertEquals(VALID_DATE_END_OF_PROBATION, defaultReportUser.getDateEndOfProbation());
    }
}
