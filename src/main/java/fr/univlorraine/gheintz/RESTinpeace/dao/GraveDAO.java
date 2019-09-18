package fr.univlorraine.gheintz.RESTinpeace.dao;

import fr.univlorraine.gheintz.RESTinpeace.entity.Grave;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraveDAO extends CrudRepository<Grave, Long> {

    public List<Grave> findByBirthNameLike(String name);

}
