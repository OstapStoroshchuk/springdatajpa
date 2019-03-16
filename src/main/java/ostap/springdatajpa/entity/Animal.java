package ostap.springdatajpa.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Getter
@Setter
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nickName;

    @Enumerated(EnumType.STRING)
    private PetType petType;

    @ManyToOne
    private Person person;

    @Override
    public String toString() {
        return "Animal{" +
                "nickName='" + nickName + '\'' +
                ", petType=" + petType +
                '}';
    }
}
