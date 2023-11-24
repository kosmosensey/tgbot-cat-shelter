package pro.sky.tgbotcatshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sky.tgbotcatshelter.constants.UserType;
import pro.sky.tgbotcatshelter.entity.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Выводим пользователя по идентификатору в телеграмм
     *
     * @param telegramId идентификатор в телеграмм
     * @return найденный пользователь
     */
    @Query("SELECT u FROM User u WHERE u.telegramId = :telegram_id")
    User findByTelegramId(@Param("telegram_id") long telegramId);
    @Query("SELECT u FROM User u")
    List<User> getAllUsers();
    @Query("SELECT u FROM User u WHERE u.userType= :user_type")
    List<User> getAllUserByType (@Param("user_type") UserType userType);

}
