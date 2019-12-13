package fr.univlorraine.gheintz.RESTinpeace.dao;

import fr.univlorraine.gheintz.RESTinpeace.entity.Grave;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GraveRepositorySearchImplTest {

    @Autowired
    private GraveRepository graveRepository;

    @Test
    public void testGetScoreSingleLetter() {
        Grave grave = new Grave("t", "", "", new Date(), new Date(),"",0.0,0.0);
        assertThat(graveRepository.getScore(grave, "t")).isEqualTo(4);
    }

    @Test
    public void testGetScoreFirstName() {
        Grave grave = new Grave("Graves", "Digger", "", "1796-06-05", "1899-01-01","I Love Spring",0.0,0.0);
        assertThat(graveRepository.getScore(grave, "Graves")).isEqualTo(24);
    }

    @Test
    public void testGetScoreDate() {
        Grave grave = new Grave("Graves", "Digger", "", "05.06.1796", "1899-01-01","I Love Spring",0.0,0.0);
        assertThat(graveRepository.getScore(grave, "1796-06-05")).isEqualTo(-2);
        assertThat(graveRepository.getScore(grave, "05/06/1796")).isEqualTo(-2);
        assertThat(graveRepository.getScore(grave, "05.06.1796")).isEqualTo(40);
        assertThat(graveRepository.getScore(grave, "1796")).isEqualTo(16);
        assertThat(graveRepository.getScore(grave, "01.01.1796")).isEqualTo(-2);
    }

    @Test
    public void testGetScoreAccent() {
        Grave grave = new Grave("Graves", "Digger", "", "05.06.1796", "1899-01-01","I Love Spring",0.0,0.0);
        assertThat(graveRepository.getScore(grave, "1796-06-05")).isEqualTo(-2);
        assertThat(graveRepository.getScore(grave, "05/06/1796")).isEqualTo(-2);
        assertThat(graveRepository.getScore(grave, "05.06.1796")).isEqualTo(40);
        assertThat(graveRepository.getScore(grave, "1796")).isEqualTo(16);
        assertThat(graveRepository.getScore(grave, "01.01.1796")).isEqualTo(-2);
    }
}