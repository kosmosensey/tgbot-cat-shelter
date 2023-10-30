package pro.sky.tgbotcatshelter.entıty;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long telegramUserId;

    public AppUser(){}

    public AppUser(Long id, Long telegramUserId) {
        Id = id;
        this.telegramUserId = telegramUserId;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getTelegramUserId() {
        return telegramUserId;
    }

    public void setTelegramUserId(Long telegramUserId) {
        this.telegramUserId = telegramUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(Id, appUser.Id) && Objects.equals(telegramUserId, appUser.telegramUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, telegramUserId);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "Id=" + Id +
                ", telegramUserId=" + telegramUserId +
                '}';
    }
}

