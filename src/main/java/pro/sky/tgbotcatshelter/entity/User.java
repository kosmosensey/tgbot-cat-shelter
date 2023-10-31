package pro.sky.tgbotcatshelter.entity;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Пользователи
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "telegram_id")
    private Long telegram_id;

    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "car_number")
    private String carNumber;
    @Column(name = "city")
    private String city;

    public User(Long id,
                Long telegram_id,
                String name,
                String phoneNumber,
                String carNumber,
                String city) {
        this.id = id;
        this.telegram_id = telegram_id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.carNumber = carNumber;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return telegram_id;
    }

    public void setChatId(Long chatId) {
        this.telegram_id = chatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTelegram_id() {
        return telegram_id;
    }

    public void setTelegram_id(Long telegram_id) {
        this.telegram_id = telegram_id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(telegram_id, user.telegram_id) && Objects.equals(name, user.name) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(carNumber, user.carNumber) && Objects.equals(city, user.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, telegram_id, name, phoneNumber, carNumber, city);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", telegram_id=" + telegram_id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
