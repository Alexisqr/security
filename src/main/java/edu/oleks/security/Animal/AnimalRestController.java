package edu.oleks.security.Animal;

import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/location/{location}")
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    public List<Animal> getAnimalsByLocation(@PathVariable String location) {
        return animalService.getByLocation(location);
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    @GetMapping("/sex/{sex}")
    public List<Animal> getAnimalsBySex(@PathVariable String sex) {
        return animalService.getBySex(sex);
    }
    @DeleteMapping("/del/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN')")
    public void delete(@PathVariable String id) {
        animalService.deleteById(id);
    }
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    public Animal create(@RequestBody Animal animal) {
        return animalService.create(animal);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    public Animal updateAnimal(@PathVariable("id") String id, @RequestBody Animal animalDetails) {
        return animalService.update(id, animalDetails);
    }
    @GetMapping("/hello/user")
    @PreAuthorize("hasRole('USER')")
    public String helloUser() {
        return "Hello User!";
    }

    @GetMapping("hello/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloAdmin() {
        return "Hello Admin!";
    }
    @PermitAll
    @GetMapping("hello/unknown")
    public String helloUnknown() {
        return "Hello Unknown!";
    }
}
