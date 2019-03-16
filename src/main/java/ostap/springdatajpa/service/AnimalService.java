package ostap.springdatajpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ostap.springdatajpa.dto.request.AnimalRequest;
import ostap.springdatajpa.dto.response.AnimalResponse;
import ostap.springdatajpa.entity.Animal;
import ostap.springdatajpa.entity.Person;
import ostap.springdatajpa.exception.WrongInputDataException;
import ostap.springdatajpa.repository.AnimalRepository;
import ostap.springdatajpa.repository.PersonRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public AnimalResponse save(AnimalRequest animalRequest) throws WrongInputDataException {
        Animal animal = new Animal();
        animal.setNickName(animalRequest.getNickName());
        animal.setPetType(animalRequest.getPetType());
        animal.setPerson(findPersonById(animalRequest.getIdPerson()));
        return new AnimalResponse(animalRepository.save(animal));
    }

    @Transactional
    public List<AnimalResponse> findAll(){
        return animalRepository.findAll().stream().map(AnimalResponse::new).collect(Collectors.toList());
    }


    private Person findPersonById(Integer id) throws WrongInputDataException {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if(optionalPerson.isPresent()){
            return optionalPerson.get();
        }
        throw new WrongInputDataException("Person with id : "+id+" not found");
    }
}
