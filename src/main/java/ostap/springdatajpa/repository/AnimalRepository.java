package ostap.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ostap.springdatajpa.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}
