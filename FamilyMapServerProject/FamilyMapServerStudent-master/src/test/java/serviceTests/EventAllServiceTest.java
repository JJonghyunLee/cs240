package serviceTests;

import dataAccess.*;
import model.Authtoken;
import model.Event;
import model.Person;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.EventAllResult;
import service.EventAllService;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventAllServiceTest {
    private Event birth;
    private Event marriage;
    private Event death;
    private Event unassociatedEvent;
    private EventAllService service;
    private EventDAO eDao;
    private Database db;
    private Connection conn;
    private String username;
    private String invalidUsername;

    @BeforeEach
    public void setUp() throws DataAccessException {
        service = new EventAllService();
        username = "username";
        invalidUsername = " invalid";

        birth = new Event("birthID", username, "personID",
                0, 0, "country", "city", "birth", 0);
        marriage = new Event("marriageID", username, "personID",
                0, 0, "country", "city", "marriage", 0);
        death = new Event("deathID", username, "personID",
                0, 0, "country", "city", "death", 0);
        unassociatedEvent = new Event("eventID", "unassociated", "unassociated",
                0, 0, "country", "city", "death", 0);

        db = new Database();
        conn = db.openConnection();
        eDao = new EventDAO(conn);
        eDao.insert(birth);
        eDao.insert(marriage);
        eDao.insert(death);
        eDao.insert(unassociatedEvent);
        db.closeConnection(true);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        conn = db.openConnection();
        eDao = new EventDAO(conn);
        eDao.clear();
        db.closeConnection(true);
    }

    @Test
    public void doServicePass() throws DataAccessException {
        conn = db.openConnection();
        eDao = new EventDAO(conn);
        assertNotNull(eDao.find(birth.getEventID()));
        assertNotNull(eDao.find(marriage.getEventID()));
        assertNotNull(eDao.find(death.getEventID()));
        assertNotNull(eDao.find(unassociatedEvent.getEventID()));
        db.closeConnection(false);
        EventAllResult result = service.doService(username);
        List<Event> list = result.getEventList();
        assertEquals(3, list.size());
        assertFalse(list.contains(unassociatedEvent));
        for (Event event : list) {
            assertEquals(username, event.getAssociatedUsername());
        }
    }

    @Test
    public void doServiceFail() throws DataAccessException {
        conn = db.openConnection();
        eDao = new EventDAO(conn);
        assertNotNull(eDao.find(birth.getEventID()));
        assertNotNull(eDao.find(marriage.getEventID()));
        assertNotNull(eDao.find(death.getEventID()));
        assertNotNull(eDao.find(unassociatedEvent.getEventID()));
        db.closeConnection(false);
        EventAllResult result = service.doService(invalidUsername);
        List<Event> list = result.getEventList();
        assertEquals(0, list.size());
    }
}
