package fr.univlorraine.gheintz.RESTinpeace.dao;

import fr.univlorraine.gheintz.RESTinpeace.entity.Grave;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraveRepositorySearch {

    public List<Grave> search(String searchString);
    public int getScore(Grave grave, String searchString);

}
