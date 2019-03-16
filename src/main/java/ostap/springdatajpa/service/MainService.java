package ostap.springdatajpa.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ostap.springdatajpa.entity.Animal;
import ostap.springdatajpa.entity.Person;
import ostap.springdatajpa.entity.PetType;
import ostap.springdatajpa.repository.PersonRepository;
import ostap.springdatajpa.repository.AnimalRepository;

import javax.transaction.Transactional;

@Service
public class MainService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AnimalRepository petRepository;

    @Transactional// manager.getTransaction.begin()------- manager.getTransaction.commit()
    public  void createInfo(){
        Person person = new Person();
        person.setFirstName("Ostap");
        person.setLastName("Storoshchuk");
        person.setAge(22);
        person = personRepository.save(person);

        Animal animal = new Animal();
        animal.setNickName("Archi");
        animal.setPetType(PetType.DOG);
        animal.setPerson(person);
        petRepository.save(animal);

        Animal animal2 = new Animal();
        animal2.setNickName("Berta");
        animal2.setPetType(PetType.DOG);
        animal2.setPerson(person);
        petRepository.save(animal2);
    }
    @Transactional
    public  void printAllPersons(){
        personRepository.findAll().forEach(person -> {
            System.out.println(person.getFirstName());
            System.out.println(person.getAnimals());
        });
    }
}
