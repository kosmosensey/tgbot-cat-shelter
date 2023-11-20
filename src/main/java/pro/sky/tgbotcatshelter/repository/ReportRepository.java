package pro.sky.tgbotcatshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.tgbotcatshelter.entity.Report;
@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}
