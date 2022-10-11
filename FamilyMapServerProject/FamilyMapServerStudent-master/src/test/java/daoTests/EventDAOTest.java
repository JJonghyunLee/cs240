package daoTests;

import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.EventDAO;
import model.Event;
import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EventDAOTest {
    private Database db;
    private Event event;
    private Event birth;
    private Event marriage;
    private Event death;
    private Event unassociatedEvent;
    private EventDAO eDao;
    private Person person;
    private Person invalidPerson;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();

        person = new Person("personID", "myUsername", "Jay",
                "Lee", "m", "Random_111F", "Random_111M", "Random_111S");

        invalidPerson = new Person("invalid", "invalid", "invalid",
                "invalid", "m", "invalid", "invalid", "invalid");

        event = new Event("eventID", "username", "personID",
                0, 0, "country", "city", "event", 0);

        birth = new Event("birthID", "username", "personID", 0,
                0, "country", "city", "birth", 0);

        marriage = new Event("marriageID", "username", "personID", 0,
                0, "country", "city", "marriage", 0);

        death = new Event("deathID", "username", "personID", 0,
                0, "country", "city", "death", 0);

        unassociatedEvent = new Event("uEventID", "unassociated", "unassociated", 0,
                0, "country", "city", "event", 0);

        Connection conn = db.getConnection();
        eDao = new EventDAO(conn);
        eDao.clear();
    }

    @AfterEach
    public void tearDown() {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        eDao.insert(event);
        Event compareTest = eDao.find(event.getEventID());
        assertNotNull(compareTest);
        assertEquals(event, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        eDao.insert(event);
        assertThrows(DataAccessException.class, () -> eDao.insert(event));
    }

    @Test
    public void findPass() throws DataAccessException {
        eDao.insert(event);
        Event compareTest = eDao.find(event.getEventID());
        assertNotNull(compareTest);
        assertEquals(event, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException {
        eDao.insert(event);
        String invalidID = "INVALID";
        Event foundEvent = eDao.find(invalidID);
        assertNull(foundEvent);
    }

    @Test
    public void clearPass() throws DataAccessException {
        eDao.insert(event);
        eDao.clear();
        assertNull(eDao.find(event.getPersonID()));
    }

    @Test
    public void findAll() throws DataAccessException {
        eDao.insert(event);
        eDao.insert(birth);
        eDao.insert(marriage);
        eDao.insert(death);
        eDao.insert(unassociatedEvent);
        List<Event> list = eDao.findAll(event.getAssociatedUsername());
        Event eventRetrieved = list.get(0);
        Event birthRetrieved = list.get(1);
        Event marriageRetrieved = list.get(2);
        Event unassociatedEventRetrieved = list.get(3);
        assertEquals(list.size(), 4);
        assertEquals(event.getEventID(), eventRetrieved.getEventID());
        assertEquals(birth.getEventID(), birthRetrieved.getEventID());
        assertEquals(marriage.getEventID(), marriageRetrieved.getEventID());
        assertEquals(death.getEventID(), death.getEventID());
    }

    @Test
    public void findAllFail() throws DataAccessException {
        eDao.insert(event);
        eDao.insert(birth);
        eDao.insert(marriage);
        eDao.insert(death);
        eDao.insert(unassociatedEvent);
        List<Event> list = eDao.findAll("invalidUsername");
        assertEquals(list.size(), 0);
    }

    @Test
    public void getBirthEventPass() throws DataAccessException {
        eDao.insert(event);
        eDao.insert(birth);
        eDao.insert(marriage);
        eDao.insert(death);
        eDao.insert(unassociatedEvent);
        Event birthRetrieved = eDao.getBirthEvent(person);
        assertEquals("birth", birthRetrieved.getEventType());
        assertEquals(birth.getEventID(), birthRetrieved.getEventID());
    }

    @Test
    public void getBirthEventFail() throws DataAccessException {
        eDao.insert(event);
        eDao.insert(birth);
        eDao.insert(marriage);
        eDao.insert(death);
        eDao.insert(unassociatedEvent);
        Event birthRetrieved = eDao.getBirthEvent(invalidPerson);
        assertNull(birthRetrieved);
    }

    @Test
    public void getMarriageEventPass() throws DataAccessException {
        eDao.insert(event);
        eDao.insert(birth);
        eDao.insert(marriage);
        eDao.insert(death);
        eDao.insert(unassociatedEvent);
        Event marriageRetrieved = eDao.getMarriageEvent(person);
        assertEquals("marriage", marriageRetrieved.getEventType());
        assertEquals(marriage.getEventID(), marriageRetrieved.getEventID());
    }

    @Test
    public void getMarriageEventFail() throws DataAccessException {
        eDao.insert(event);
        eDao.insert(birth);
        eDao.insert(marriage);
        eDao.insert(death);
        eDao.insert(unassociatedEvent);
        Event marriageRetrieved = eDao.getMarriageEvent(invalidPerson);
        assertNull(marriageRetrieved);
    }

}
