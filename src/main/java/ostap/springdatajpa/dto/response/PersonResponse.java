package ostap.springdatajpa.dto.response;

import lombok.Getter;
import lombok.Setter;
import ostap.springdatajpa.entity.Person;

@Getter @Setter
public class PersonResponse {

    private Integer id;

    private String firstName;

    private String lastName;

    private Integer age;

    private String pathToPhoto;

    public PersonResponse(Person person) {
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.age = person.getAge();
        this.pathToPhoto = person.getPathToPhoto();
    }
}
