package pro.sky.tgbotcatshelter.entity;

import jakarta.persistence.*;
import pro.sky.tgbotcatshelter.constants.AnimalSex;
import pro.sky.tgbotcatshelter.constants.PetType;

import java.util.Objects;

/**
 * Класс, представляющий информацию о животном.
 */
@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "pet_type")
    private PetType petType;

    @Column(name = "color")
    private String color;

    @Column(name = "sex")
    private AnimalSex sex;

    /**
     * Пустой конструктор для создания объекта Animal.
     */
    public Animal() {
    }

    /**
     * Конструктор для создания объекта Animal с определенными параметрами.
     *
     * @param id      идентификатор животного
     * @param name    имя животного
     * @param petType тип животного (например, кошка, собака и т.д.)
     * @param color   цвет животного
     * @param sex     пол животного
     */
    public Animal(Long id, String name, PetType petType, String color, AnimalSex sex) {
        this.id = id;
        this.name = name;
        this.petType = petType;
        this.color = color;
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public AnimalSex getSex() {
        return sex;
    }

    public void setSex(AnimalSex sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(id, animal.id) && Objects.equals(name, animal.name) && Objects.equals(petType, animal.petType) && Objects.equals(color, animal.color) && Objects.equals(sex, animal.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, petType, color, sex);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", petType='" + petType + '\'' +
                ", color='" + color + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
