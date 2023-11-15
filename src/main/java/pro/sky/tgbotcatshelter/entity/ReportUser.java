package pro.sky.tgbotcatshelter.entity;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "reportUsers")
public class ReportUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String photoPath;
    private String text;

    public ReportUser(String photoPath, String text) {
        this.photoPath = photoPath;
        this.text = text;
    }

    public ReportUser() {
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportUser that = (ReportUser) o;
        return id == that.id && Objects.equals(photoPath, that.photoPath) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photoPath, text);
    }

    @Override
    public String toString() {
        return "ReportUser{" +
                "photoPath='" + photoPath + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}