package pro.sky.tgbotcatshelter.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.tgbotcatshelter.constants.StatusReport;
import pro.sky.tgbotcatshelter.entity.ReportUser;
import pro.sky.tgbotcatshelter.entity.User;
import pro.sky.tgbotcatshelter.repository.ReportUserRepository;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ReportUserServiceImplTest {

    @Mock
    private ReportUserRepository reportUserRepository;

    @InjectMocks
    private ReportUserServiceImpl reportUserService;

    public ReportUserServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createReportUser_ShouldCreateReportUser() {
        User user = new User();
        LocalDate dateReport = LocalDate.now();
        LocalDate dateEndOfProbation = LocalDate.now().plusMonths(1);;
        StatusReport statusReport = StatusReport.DEFAULT;
        String reportText = "Test report";
        String photoPath = "https://api.telegram.org/file/bot6787712038:AAFfG49Ff8laGMsmomnMlYSiCX6F97JHcUc/photos/file_8.jpg";

        ReportUser report = new ReportUser(photoPath, reportText, user, statusReport, dateReport, dateEndOfProbation);
        when(reportUserRepository.save(report)).thenReturn(report);

        ReportUser savedReport = reportUserService.createReportUser(photoPath, reportText, user, statusReport, dateReport, dateEndOfProbation);

        Assertions.assertNotNull(savedReport);
        assertEquals(report, savedReport);
        verify(reportUserRepository, times(1)).save(report);
    }

    @Test
    public void findAllReportUser_ShouldReturnAllReports() {
        ReportUser report1 = new ReportUser();
        ReportUser report2 = new ReportUser();
        when(reportUserRepository.findAll()).thenReturn(Arrays.asList(report1, report2));

        Collection<ReportUser> allReports = reportUserService.findAllReportUser();

        assertEquals(2, allReports.size());
    }

    @Test
    public void findReportUserById_ShouldReturnReportUser() {
        long id = 1L;
        ReportUser report = new ReportUser();
        when(reportUserRepository.findById(id)).thenReturn(Optional.of(report));

        Optional<ReportUser> foundReport = reportUserService.findReportUserById(id);

        assertTrue(foundReport.isPresent());
        assertEquals(report, foundReport.get());
    }

}