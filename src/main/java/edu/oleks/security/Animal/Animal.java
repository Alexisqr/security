package edu.oleks.security.Animal;

/*
@author   oleksandra
@project   security
@class  Item
@version  1.0.0
@since 19.02.2025 - 22.41
*/

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Document
public class Animal extends AuditMetaData {

    @Id
    private String id;
    private String name;
    private int age;
    private String sex;
    private String description;
    private String location;
    private String type;
    private String photos;
    private boolean isAvailableForAdoption;
    private String shelterId;
    private boolean sterilization;


    public Animal() {}

    public Animal(String name, int age, String sex, String description, String location, String type, String photos, boolean isAvailableForAdoption, String shelterId, boolean sterilization) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.description = description;
        this.location = location;
        this.type = type;
        this.photos = photos;
        this.isAvailableForAdoption = isAvailableForAdoption;
        this.shelterId = shelterId;
        this.sterilization = sterilization;
    }

    public Animal(String id,String name, int age, String sex, String description, String location, String type, String photos, boolean isAvailableForAdoption, String shelterId, boolean sterilization) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.description = description;
        this.location = location;
        this.type = type;
        this.photos = photos;
        this.isAvailableForAdoption = isAvailableForAdoption;
        this.shelterId = shelterId;
        this.sterilization = sterilization;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                ", photos='" + photos + '\'' +
                ", isAvailableForAdoption=" + isAvailableForAdoption +
                ", shelterId='" + shelterId + '\'' +
                ", sterilization=" + sterilization +
                '}';
    }

    public String getLocation() {
        return location;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSex() {
        return sex;
    }

    public String getType() {
        return type;
    }

    public String getPhotos() {
        return photos;
    }

    public boolean isAvailableForAdoption() {
        return isAvailableForAdoption;
    }

    public String getShelterId() {
        return shelterId;
    }

    public boolean isSterilization() {
        return sterilization;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public void setAvailableForAdoption(boolean availableForAdoption) {
        isAvailableForAdoption = availableForAdoption;
    }

    public void setShelterId(String shelterId) {
        this.shelterId = shelterId;
    }

    public void setSterilization(boolean sterilization) {
        this.sterilization = sterilization;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return getId().equals(animal.getId());
    }

    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

}