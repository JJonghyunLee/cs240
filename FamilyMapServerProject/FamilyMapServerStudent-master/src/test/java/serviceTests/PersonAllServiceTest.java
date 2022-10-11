package serviceTests;

import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.PersonDAO;
import model.Event;
import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.EventAllResult;
import result.PersonAllResult;
import service.PersonAllService;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersonAllServiceTest {
    private Person newPerson;
    private Person mother;
    private Person father;
    private Person unassociatedPerson;
    private PersonAllService service;
    private PersonDAO pDao;
    private Database db;
    private Connection conn;
    private String username;
    private String invalidUsername;

    @BeforeEach
    public void setUp() throws DataAccessException {
        service = new PersonAllService();
        username = "username";
        invalidUsername = " invalid";

        newPerson = new Person("Random_123A", username, "Jay",
                "Lee", "m", "Random_111F", "Random_111M", "Random_111S");

        father = new Person("Father_123A", username, "Jonh",
                "Lee", "m", "Random_111F", "Random_111M", "Random_111S");

        mother = new Person("Mother_123A", username, "Rebecca",
                "Lee", "f", "Random_111F", "Random_111M", "Random_111S");

        unassociatedPerson = new Person("Invalid_123A", "unassociated", "Simon",
                "Campbell", "m", "Random_111F", "Random_111M", "Random_111S");

        db = new Database();
        conn = db.openConnection();
        pDao = new PersonDAO(conn);
        pDao.insert(newPerson);
        pDao.insert(mother);
        pDao.insert(father);
        pDao.insert(unassociatedPerson);
        db.closeConnection(true);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        conn = db.openConnection();
        pDao = new PersonDAO(conn);
        pDao.clear();
        db.closeConnection(true);
    }

    @Test
    public void doServicePass() throws DataAccessException {
        conn = db.openConnection();
        pDao = new PersonDAO(conn);
        assertNotNull(pDao.find(newPerson.getPersonID()));
        assertNotNull(pDao.find(mother.getPersonID()));
        assertNotNull(pDao.find(father.getPersonID()));
        assertNotNull(pDao.find(unassociatedPerson.getPersonID()));
        db.closeConnection(false);
        PersonAllResult result = service.doService(username);
        List<Person> list = result.getPersonList();
        assertEquals(3, list.size());
        assertFalse(list.contains(unassociatedPerson));
        for (Person person : list) {
            assertEquals(username, person.getAssociatedUsername());
        }
    }

    @Test
    public void doServiceFail() throws DataAccessException {
        conn = db.openConnection();
        pDao = new PersonDAO(conn);
        assertNotNull(pDao.find(newPerson.getPersonID()));
        assertNotNull(pDao.find(mother.getPersonID()));
        assertNotNull(pDao.find(father.getPersonID()));
        assertNotNull(pDao.find(unassociatedPerson.getPersonID()));
        db.closeConnection(false);
        PersonAllResult result = service.doService(invalidUsername);
        List<Person> list = result.getPersonList();
        assertEquals(0, list.size());
    }
}

