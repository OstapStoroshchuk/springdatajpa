package ostap.springdatajpa.dto.response;

import lombok.Getter;
import lombok.Setter;
import ostap.springdatajpa.entity.Animal;
import ostap.springdatajpa.entity.PetType;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class AnimalResponse {

    private Integer id;

    private String nickName;

    private PetType petType;

    private PersonResponse personResponse;

    public AnimalResponse(Animal animal) {
        this.id = animal.getId();
        this.nickName = animal.getNickName();
        this.petType = animal.getPetType();
        this.personResponse =new PersonResponse(animal.getPerson());
    }
}
