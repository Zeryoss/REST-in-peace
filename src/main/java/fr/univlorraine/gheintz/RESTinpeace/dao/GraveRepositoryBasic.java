package fr.univlorraine.gheintz.RESTinpeace.dao;

import fr.univlorraine.gheintz.RESTinpeace.entity.Grave;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraveRepositoryBasic extends CrudRepository<Grave, Long> {

    public List<Grave> findByBirthNameContaining(String name);
    public List<Grave> findByLastNameContaining(String name);
    public List<Grave> findByFirstNameContaining(String name);
    public List<Grave> findByEpitaphContaining(String name);

}
