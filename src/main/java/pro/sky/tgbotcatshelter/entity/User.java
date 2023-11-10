package pro.sky.tgbotcatshelter.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import pro.sky.tgbotcatshelter.constants.ShelterType;
import pro.sky.tgbotcatshelter.constants.UserStatus;
import pro.sky.tgbotcatshelter.constants.UserType;

import java.util.Objects;

/**
 * Пользователи
 */
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "telegram_id")
    private Long telegramId;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "car_number")
    private String carNumber;
    @Column(name = "shelter_type")
    private ShelterType shelterType;
    @Column(name = "user_type")
    private UserType userType;
    @Column(name = "user_status")
    private UserStatus userStatus;

    public User(Long id,
                Long telegram_id,
                String name,
                String address,
                String phoneNumber,
                String carNumber) {
        this.id = id;
        this.telegramId = telegram_id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.carNumber = carNumber;
    }

    public User(Long telegramId, UserType userType, UserStatus userStatus) {
        this.telegramId = telegramId;
        this.userType = userType;
        this.userStatus = userStatus;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Long telegramId) {
        this.telegramId = telegramId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public ShelterType getShelterType() {
        return shelterType;
    }

    public void setShelterType(ShelterType shelterType) {
        this.shelterType = shelterType;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(telegramId, user.telegramId) && Objects.equals(name, user.name) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(carNumber, user.carNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, telegramId, name, phoneNumber, carNumber);
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", telegram_id=" + telegramId +
               ", name='" + name + '\'' +
               ", phoneNumber='" + phoneNumber + '\'' +
               ", carNumber='" + carNumber + '\'' +
               '}';
    }

    public void setUserName(String name) {

        if (name.matches("^[a-zA-Zа-яА-Я]+$")
                && Character.isUpperCase(name.charAt(0))) {
            this.name = name;
        } else {
            throw new RuntimeException("Имя введено некорректно");
        }
    }

    public void setPhoneNumber(String phoneNumber) {

        phoneNumber = phoneNumber.replace("-", "");
        phoneNumber = phoneNumber.replace(" ", "");
        phoneNumber = phoneNumber.replace("+", "");

        if (phoneNumber.length() == 10) {
            this.phoneNumber = "(+997)" + phoneNumber;
        } else if (phoneNumber.length() > 11) {
            throw new RuntimeException("Телефон слишком длинный");
        } else if (phoneNumber.length() < 10) {
            throw new RuntimeException("Телефон слишком короткий");
        }
    }

    public void setCarNumber(String carNumber) {
        if (carNumber == null || carNumber.isEmpty() || carNumber.isBlank()) {
            this.carNumber = "Без автомобиля";
        } else {
            this.carNumber = carNumber;
        }
    }
}
