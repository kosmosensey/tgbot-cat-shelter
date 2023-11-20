package pro.sky.tgbotcatshelter.entity;

import jakarta.persistence.*;
import pro.sky.tgbotcatshelter.constants.ShelterType;
import java.util.Objects;

/**
 * Сущность, представляющая информацию о приюте.
 */
@Entity
@Table(name = "shelters")
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    // Адрес приюта
    @Column(name = "address_shelter")
    private String addressShelter;

    // Время работы приюта
    @Column(name = "time_work")
    private String timeWork;

    // Инструкции по проезду к приюту
    @Column(name = "driving_directions")
    private String drivingDirections;

    // Телефон приюта
    @Column(name = "phone_shelter")
    private String phoneShelter;

    // Телефон службы безопасности приюта
    @Column(name = "phone_security")
    private String phoneSecurity;

    // Тип приюта
    @Column(name = "shelter_type")
    private ShelterType shelterType;

    // Пустой конструктор для JPA
    public Shelter() {
    }

    // Конструктор для создания объекта Shelter
    public Shelter(long id, String addressShelter, String timeWork,
                   String drivingDirections, String phoneShelter,
                   String phoneSecurity, ShelterType shelterType) {
        this.id = id;
        this.addressShelter = addressShelter;
        this.timeWork = timeWork;
        this.drivingDirections = drivingDirections;
        this.phoneShelter = phoneShelter;
        this.phoneSecurity = phoneSecurity;
        this.shelterType = shelterType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddressShelter() {
        return addressShelter;
    }

    public void setAddressShelter(String addressShelter) {
        this.addressShelter = addressShelter;
    }

    public String getTimeWork() {
        return timeWork;
    }

    public void setTimeWork(String timeWork) {
        this.timeWork = timeWork;
    }

    public String getDrivingDirections() {
        return drivingDirections;
    }

    public void setDrivingDirections(String drivingDirections) {
        this.drivingDirections = drivingDirections;
    }

    public String getPhoneShelter() {
        return phoneShelter;
    }

    public void setPhoneShelter(String phoneShelter) {
        this.phoneShelter = phoneShelter;
    }

    public String getPhoneSecurity() {
        return phoneSecurity;
    }

    public void setPhoneSecurity(String phoneSecurity) {
        this.phoneSecurity = phoneSecurity;
    }

    public ShelterType getShelterType() {
        return shelterType;
    }

    public void setShelterType(ShelterType shelterType) {
        this.shelterType = shelterType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shelter shelter = (Shelter) o;
        return id == shelter.id && Objects.equals(addressShelter, shelter.addressShelter) && Objects.equals(timeWork, shelter.timeWork) && Objects.equals(drivingDirections, shelter.drivingDirections) && Objects.equals(phoneShelter, shelter.phoneShelter) && Objects.equals(phoneSecurity, shelter.phoneSecurity) && Objects.equals(shelterType, shelter.shelterType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addressShelter, timeWork, drivingDirections, phoneShelter, phoneSecurity, shelterType);
    }

    @Override
    public String toString() {
        return "Shelter{" +
                "id=" + id +
                ", addressShelter='" + addressShelter + '\'' +
                ", timeWork='" + timeWork + '\'' +
                ", drivingDirections='" + drivingDirections + '\'' +
                ", phoneShelter='" + phoneShelter + '\'' +
                ", phoneSecurity='" + phoneSecurity + '\'' +
                ", shelterType='" + shelterType + '\'' +
                '}';
    }
}
