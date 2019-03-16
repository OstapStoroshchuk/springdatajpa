package ostap.springdatajpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ostap.springdatajpa.dto.request.AnimalRequest;
import ostap.springdatajpa.dto.response.AnimalResponse;
import ostap.springdatajpa.exception.WrongInputDataException;
import ostap.springdatajpa.service.AnimalService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping
    public void saveAnimal(@RequestBody AnimalRequest animalRequest) throws WrongInputDataException {
        animalService.save(animalRequest);
    }

    @GetMapping
    public List<AnimalResponse> findAll(){
      return animalService.findAll();
    }
}
