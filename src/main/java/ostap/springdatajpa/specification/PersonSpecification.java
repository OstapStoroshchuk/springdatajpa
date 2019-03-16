package ostap.springdatajpa.specification;

import org.springframework.data.jpa.domain.Specification;
import ostap.springdatajpa.entity.Person;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PersonSpecification implements Specification<Person> {

    private String value;

    public PersonSpecification(String value) {
        this.value = value;
    }

    private Predicate findByFirstName(Root<Person> root, CriteriaBuilder criteriaBuilder){
        return criteriaBuilder.like(root.get("firstName"), value);
    }

    private Predicate findByLastName(Root<Person> root, CriteriaBuilder criteriaBuilder){
        return criteriaBuilder.like(root.get("lastName"), value);
    }


    @Override
    public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.or(findByFirstName(root, criteriaBuilder), findByLastName(root,criteriaBuilder));
    }
}
