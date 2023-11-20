package pro.sky.tgbotcatshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.tgbotcatshelter.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByTelegramId(long telegramId);
}
