package pro.sky.tgbotcatshelter.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "pet_type")
    private String petType;
    @Column(name = "color")
    private String color;
    @Column(name = "sex")
    private String sex;

    public Animal(Long id,
                  String name,
                  String petType,
                  String color,
                  String sex) {
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

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
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
