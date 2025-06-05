package com.herodevs.nes.trial.spring.controller;

import com.herodevs.nes.trial.spring.model.Pet;
import com.herodevs.nes.trial.spring.repository.PetRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PetController {

    private final PetRepository petRepository;

    private PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @GetMapping("/helloPets")
    public String helloPets() {
        return "Woof! Meow! Blub! Tweet!";
    }

    @GetMapping("/allPets")
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @GetMapping(value = "/pets", params = "name")
    public List<Pet> getByName(@RequestParam("name") String name) {
        return petRepository.findByName(name);
    }

    @GetMapping(value = "/pets", params = "type")
    public List<Pet> getByType(@RequestParam("type") String type) {
        return petRepository.findByType(type);
    }

}
