package fr.univlorraine.gheintz.RESTinpeace.init;

import fr.univlorraine.gheintz.RESTinpeace.dao.GraveDAO;
import fr.univlorraine.gheintz.RESTinpeace.entity.Grave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
public class DataInit implements ApplicationRunner {

    private GraveDAO graveDAO;
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public DataInit(GraveDAO graveDAO) {
        this.graveDAO = graveDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        long graveCount = graveDAO.count();

        if (graveCount == 0) {

            Grave[] graves = {
                    new Grave("Honoré", "De Balzac", "",  df.parse("1799-05-20"),  df.parse("1850-08-18"), "Huit jours avec la fièvre ! J’aurai encore eu le temps d’écrire un livre !", 48.8614, 2.3933 ),
                    new Grave("Immanuel", "Kant", "",  df.parse("1724-04-22"),  df.parse("1804-02-12"), "Es ist gut", 54.710128, 20.510584),
                    new Grave("Karl", "Marx", "",  df.parse("1818-05-05"),  df.parse("1882-03-14"), "The philosophers have only interpreted the world in various ways; the point is to change it", 51.5662, 0.1439),
                    new Grave("Diana", "Spencer", "",  df.parse("1980-12-20"),  df.parse("1980-12-20"), "", 52.2823152, -0.9839502),
                    new Grave("Marilyn", "Monroe", "",  df.parse("1980-12-20"),  df.parse("1980-12-20"), "", 34.059607, -118.443977)
            };

            for (final Grave g : graves) {
                graveDAO.save(g);
            }
        }

    }

}