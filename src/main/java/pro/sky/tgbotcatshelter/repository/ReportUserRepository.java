package pro.sky.tgbotcatshelter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.tgbotcatshelter.entity.ReportUser;

@Repository
public interface ReportUserRepository extends JpaRepository<ReportUser,Long> {
}
