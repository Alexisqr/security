package edu.oleks.security.Animal;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
@author   oleksandra
@project   security
@class  ItemRestController
@version  1.0.0
@since 19.02.2025 - 22.57
*/
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/animals")
@AllArgsConstructor
public class AnimalRestController {

    @Autowired
    private AnimalService animalService;

    @GetMapping
    public List<Animal> getItems() {
        return animalService.getAll();
    }
    @GetMapping("/{id}")
    public Animal getOneItem(@PathVariable String id) {
        return animalService.getById(id);
    }
    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable String id) {
        animalService.deleteById(id);
    }

    @PostMapping
    public Animal create(@RequestBody Animal animal) {
        return animalService.create(animal);
    }

    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable("id") String id, @RequestBody Animal animalDetails) {
        return animalService.update(id, animalDetails);
    }
    @GetMapping("/hello/user")
    public String helloUser() {
        return "Hello User!";
    }

    @GetMapping("hello/admin")
    public String helloAdmin() {
        return "Hello Admin!";
    }

    @GetMapping("hello/unknown")
    public String helloUnknown() {
        return "Hello Unknown!";
    }
}
