package daoTests;

import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.PersonDAO;
import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//We will use this to test that our insert method is working and failing in the right ways
public class PersonDAOTest {
    private Database db;
    private Person newPerson;
    private Person mother;
    private Person father;
    private PersonDAO pDao;
    private Person unassociatedPerson;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        newPerson = new Person("Random_123A", "myUsername", "Jay",
                "Lee", "m", "Random_111F", "Random_111M", "Random_111S");

        father = new Person("Father_123A", "myUsername", "Jonh",
                "Lee", "m", "Random_111F", "Random_111M", "Random_111S");

        mother = new Person("Mother_123A", "myUsername", "Rebecca",
                "Lee", "f", "Random_111F", "Random_111M", "Random_111S");

        unassociatedPerson = new Person("Invalid_123A", "unassociated", "Simon",
                "Campbell", "m", "Random_111F", "Random_111M", "Random_111S");
        Connection conn = db.getConnection();
        pDao = new PersonDAO(conn);
        pDao.clear();
    }

    @AfterEach
    public void tearDown() {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        pDao.insert(newPerson);
        Person compareTest = pDao.find(newPerson.getPersonID());
        assertNotNull(compareTest);
        assertEquals(newPerson, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        pDao.insert(newPerson);
        assertThrows(DataAccessException.class, () -> pDao.insert(newPerson));
    }

    @Test
    public void findPass() throws DataAccessException {
        pDao.insert(newPerson);
        Person compareTest = pDao.find(newPerson.getPersonID());
        assertNotNull(compareTest);
        assertEquals(newPerson, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException {
        pDao.insert(newPerson);
        String invalidID = "INVALID";
        Person foundPerson = pDao.find(invalidID);
        assertNull(foundPerson);
    }

    @Test
    public void setMotherPass() throws DataAccessException {
        pDao.insert(newPerson);
        pDao.insert(mother);
        pDao.setMother(newPerson.getPersonID(), mother.getPersonID());
        Person updatedPerson = pDao.find(newPerson.getPersonID());
        assertEquals(mother.getPersonID(), updatedPerson.getMotherID());
    }

    @Test
    public void setMotherFail() throws DataAccessException {
        pDao.insert(newPerson);
        pDao.setMother("invalid", mother.getPersonID());
        Person compareTest = pDao.find(newPerson.getPersonID());
        assertNotEquals(mother.getPersonID(), compareTest.getMotherID());
    }

    @Test
    public void setFatherPass() throws DataAccessException {
        pDao.insert(newPerson);
        pDao.insert(father);
        pDao.setFather(newPerson.getPersonID(), father.getPersonID());
        Person updatedPerson = pDao.find(newPerson.getPersonID());
        assertEquals(father.getPersonID(), updatedPerson.getFatherID());
    }

    @Test
    public void setFatherFail() throws DataAccessException {
        pDao.insert(newPerson);
        pDao.setFather("invalid", father.getPersonID());
        Person compareTest = pDao.find(newPerson.getPersonID());
        assertNotEquals(father.getPersonID(), compareTest.getFatherID());
    }

    @Test
    public void clearAncestorsPass() throws DataAccessException {
        pDao.insert(newPerson);
        pDao.insert(father);
        pDao.insert(mother);
        pDao.insert(unassociatedPerson);
        pDao.clearAncestors(newPerson.getAssociatedUsername());
        Person personCleared = pDao.find(newPerson.getPersonID());
        Person fatherCleared = pDao.find(father.getPersonID());
        Person motherCleared = pDao.find(mother.getPersonID());
        Person unassociatedPersonCleared = pDao.find(unassociatedPerson.getPersonID());
        assertNull(personCleared);
        assertNull(fatherCleared);
        assertNull(motherCleared);
        assertNotNull(unassociatedPersonCleared);
    }

    @Test
    public void clearAncestorsFail() throws DataAccessException {
        pDao.insert(newPerson);
        pDao.insert(father);
        pDao.insert(mother);
        pDao.insert(unassociatedPerson);
        pDao.clearAncestors("invalid");
        Person personCleared = pDao.find(newPerson.getPersonID());
        Person fatherCleared = pDao.find(father.getPersonID());
        Person motherCleared = pDao.find(mother.getPersonID());
        Person unassociatedPersonCleared = pDao.find(unassociatedPerson.getPersonID());
        assertNotNull(personCleared);
        assertNotNull(fatherCleared);
        assertNotNull(motherCleared);
        assertNotNull(unassociatedPersonCleared);
    }


    @Test
    public void clearPass() throws DataAccessException {
        pDao.insert(newPerson);
        pDao.clear();
        assertNull(pDao.find(newPerson.getPersonID()));
    }

    @Test
    public void findAllPass() throws DataAccessException {
        pDao.insert(newPerson);
        pDao.insert(father);
        pDao.insert(mother);
        pDao.insert(unassociatedPerson);
        List<Person> list = pDao.findAll(newPerson.getAssociatedUsername());
        Person newPersonRetrieved = list.get(0);
        Person fatherRetrieved = list.get(1);
        Person motherRetrieved = list.get(2);
        assertEquals(list.size(), 3);
        assertEquals(newPerson.getPersonID(), newPersonRetrieved.getPersonID());
        assertEquals(father.getPersonID(), fatherRetrieved.getPersonID());
        assertEquals(mother.getPersonID(), motherRetrieved.getPersonID());
    }

    @Test
    public void findAllFail() throws DataAccessException {
        pDao.insert(newPerson);
        pDao.insert(father);
        pDao.insert(mother);
        pDao.insert(unassociatedPerson);
        List<Person> list = pDao.findAll("invalidUsername");
        assertEquals(0, list.size());
    }
}
