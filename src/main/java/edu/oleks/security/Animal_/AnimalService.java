package edu.oleks.security.Animal_;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public void deleteById(String id) {
        animalRepository.deleteById(id);
    }

    public Animal create(Animal item) {
        return animalRepository.save(item);
    }

    public Animal update(Animal item) {
        return animalRepository.save(item);
    }
}
