package pro.sky.tgbotcatshelter.repository;


import org.springframework.stereotype.Repository;
import pro.sky.tgbotcatshelter.entity.NotificationTask;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationTaskRepository extends JpaRepository <NotificationTask,Long> {
    List<NotificationTask> findAllByExecDate(LocalDateTime execDate);
}
