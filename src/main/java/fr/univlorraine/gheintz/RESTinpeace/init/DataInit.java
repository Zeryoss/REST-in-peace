package fr.univlorraine.gheintz.RESTinpeace.init;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import fr.univlorraine.gheintz.RESTinpeace.dao.GraveDAO;
import fr.univlorraine.gheintz.RESTinpeace.entity.Grave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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

            Faker faker = new Faker();

            List<Grave> graves = new ArrayList<>();

            Name name;
            String birthName;
            String epitaph;
            int randomNum;
            Date today = new Date();
            Coordinates coordinates;

            for (int i = 0; i < 500; i++) {
                name = faker.name();

                Date birthDate = faker.date().birthday();

                birthName = "";
                randomNum = ThreadLocalRandom.current().nextInt(1, 4);
                if (randomNum == 2) {
                    birthName = faker.name().lastName();
                }

                epitaph = "";
                randomNum = ThreadLocalRandom.current().nextInt(1, 11);
                switch (randomNum) {
                    case 1:
                        epitaph = "Rest In Peace.";
                        break;
                    case 3:
                        epitaph = faker.gameOfThrones().quote();
                        break;
                    case 5:
                        epitaph = faker.yoda().quote();
                        break;
                    case 6:
                        epitaph = faker.backToTheFuture().quote();
                        break;
                    case 7:
                        epitaph = faker.twinPeaks().quote();
                        break;
                    case 8:
                        epitaph = faker.chuckNorris().fact();
                        break;
                    case 9:
                        epitaph = faker.rickAndMorty().quote();
                        break;
                    case 10:
                        epitaph = faker.harryPotter().quote();
                        break;
                    default:
                        break;
                }

                coordinates = generateCoordinates(-73.91241619668341, 40.738351873998056, 500);

                graves.add(
                        new Grave(
                                name.firstName(),
                                name.lastName(),
                                birthName,
                                birthDate,
                                faker.date().between(birthDate, today),
                                epitaph,
                                coordinates.longitude,
                                coordinates.latitude
                        )
                );
            }

            for (final Grave g : graves) {
                graveDAO.save(g);
            }
        }

    }

    /**
     * Generates random GPS coordinates around the given point within the given distance.
     *
     * @param x0     longitude
     * @param y0     latitude
     * @param radius in meters
     * @return
     */
    public Coordinates generateCoordinates(double x0, double y0, int radius) {
        Random random = new Random();

        // Convert radius from meters to degrees
        double radiusInDegrees = radius / 111000f;

        double u = random.nextDouble();
        double v = random.nextDouble();
        double w = radiusInDegrees * Math.sqrt(u);
        double t = 2 * Math.PI * v;
        double x = w * Math.cos(t);
        double y = w * Math.sin(t);

        // Adjust the x-coordinate for the shrinking of the east-west distances
        double new_x = x / Math.cos(Math.toRadians(y0));

        double foundLongitude = new_x + x0;
        double foundLatitude = y + y0;

        return new Coordinates(foundLongitude, foundLatitude);
    }

}

class Coordinates {
    public double longitude;
    public double latitude;

    public Coordinates(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}