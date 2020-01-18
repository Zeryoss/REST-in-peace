package fr.univlorraine.gheintz.RESTinpeace.controller;

import fr.univlorraine.gheintz.RESTinpeace.entity.Grave;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import static junit.framework.TestCase.assertSame;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GraveControllerTests {

    @Autowired
    GraveController graveController;

    @Test
    public void testGetAllGraves(){
        assertSame(graveController.list("").getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetOneGraveById(){
        assertSame(graveController.get(3).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testCreateGrave(){
        assertSame(graveController.save(createGrave()).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testDeleteGraveById(){
        assertSame(graveController.get(1).getStatusCode(), HttpStatus.OK);
        graveController.delete(1);
        assertSame(graveController.get(1).getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testUpdateGrave(){
        assertSame(graveController.update(2, createGrave()).getStatusCode(), HttpStatus.OK);
    }

    private Grave createGrave(){
        return new Grave("John", "Doe", "", "01.01.1900", "01.01.2000","Rest In Peace.",0.0,0.0);
    }
}