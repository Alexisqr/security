package edu.oleks.security.Animal;


import edu.oleks.security.security.SecurityConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
@author   oleksandra
@project   security
@class  ItemService
@version  1.0.0
@since 19.02.2025 - 22.54
*/

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;
    private SecurityConfig securityConfig;
    private List<Animal> animals = new ArrayList<Animal>();

    @PostConstruct
    void init() {
        animals.add(new Animal("1","name1",1,"M","description1","location","cat","https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww",false,"1",true));
        animals.add(new Animal("2","name2",1,"F","description2","location2","dog","https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww",false,"2",false));
        animals.add(new Animal("3","name3",1,"M","description3","location3","cat","https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww",false,"1",true));

        animalRepository.saveAll(animals);
    }


    public List<Animal> getAll() {
        return animalRepository.findAll();
    }

    public Animal getById(String id) {
        return animalRepository.findById(id).orElse(null);
    }

    public List<Animal> getByLocation(String location) {
        return animalRepository.findAll().stream()
                .filter(animal -> location.equalsIgnoreCase(animal.getLocation()))
                .collect(Collectors.toList());
    }

    public List<Animal> getBySex(String sex) {
        return animalRepository.findAll().stream()
                .filter(animal -> sex.equalsIgnoreCase(animal.getSex()))
                .collect(Collectors.toList());
    }

    public void deleteById(String id) {
        animalRepository.deleteById(id);
    }

    public Animal create(Animal item) {
        return animalRepository.save(item);
    }

    public Animal update(String id, Animal animalDetails) {
        Animal animal = animalRepository.findById(id).orElse(null);

        if (animal != null) {
            if (animalDetails.getName() != null) animal.setName(animalDetails.getName());
            if (animalDetails.getAge() != 0) animal.setAge(animalDetails.getAge());
            if (animalDetails.getSex() != null) animal.setSex(animalDetails.getSex());
            if (animalDetails.getDescription() != null) animal.setDescription(animalDetails.getDescription());
            if (animalDetails.getLocation() != null) animal.setLocation(animalDetails.getLocation());
            if (animalDetails.getType() != null) animal.setType(animalDetails.getType());
            if (animalDetails.getPhotos() != null) animal.setPhotos(animalDetails.getPhotos());
            if (animalDetails.getShelterId() != null) animal.setShelterId(animalDetails.getShelterId());
            if (animalDetails.isAvailableForAdoption() != animal.isAvailableForAdoption()) {
                animal.setAvailableForAdoption(animalDetails.isAvailableForAdoption());
            }
            if (animalDetails.isSterilization() != animal.isSterilization()) {
                animal.setSterilization(animalDetails.isSterilization());
            }

            return animalRepository.save(animal);
        } else {
            return null;
        }
    }
}
