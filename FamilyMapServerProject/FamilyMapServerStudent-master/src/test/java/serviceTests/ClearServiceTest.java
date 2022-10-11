package serviceTests;

import dataAccess.*;
import model.Authtoken;
import model.Event;
import model.Person;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ClearService;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNull;

public class ClearServiceTest {
    private User user;
    private Event event;
    private Person person;
    private Authtoken authtoken;
    private Database db;
    private UserDAO uDao;
    private EventDAO eDao;
    private PersonDAO pDao;
    private AuthtokenDAO aDao;
    private Connection conn;
    private ClearService service;

    @BeforeEach
    public void setUp() throws DataAccessException {
        service = new ClearService();
        user = new User("myUsername", "myPassword", "jaydenle@byu.edu",
                "Jay", "Lee", "m", "Random_123A");

        event = new Event("eventID", "username", "personID",
                0, 0, "country", "city", "event", 0);

        person = new Person("Random_123A", "myUsername", "Jay",
                "Lee", "m", "Random_111F", "Random_111M", "Random_111S");

        authtoken = new Authtoken("authtoken", "username");
        db = new Database();
        conn = db.openConnection();
        uDao = new UserDAO(conn);
        uDao.clear();
        aDao = new AuthtokenDAO(conn);
        aDao.clear();
        pDao = new PersonDAO(conn);
        pDao.clear();
        eDao = new EventDAO(conn);
        eDao.clear();
        db.closeConnection(true);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        conn = db.openConnection();
        uDao = new UserDAO(conn);
        uDao.clear();
        aDao = new AuthtokenDAO(conn);
        aDao.clear();
        pDao = new PersonDAO(conn);
        pDao.clear();
        eDao = new EventDAO(conn);
        eDao.clear();
        db.closeConnection(true);
    }

    @Test
    public void doServiceTest() throws DataAccessException {
        conn = db.openConnection();
        uDao = new UserDAO(conn);
        aDao = new AuthtokenDAO(conn);
        pDao = new PersonDAO(conn);
        eDao = new EventDAO(conn);
        uDao.insert(user);
        aDao.insert(authtoken);
        pDao.insert(person);
        eDao.insert(event);
        db.closeConnection(true);
        service.doService();
        conn = db.openConnection();
        uDao = new UserDAO(conn);
        aDao = new AuthtokenDAO(conn);
        pDao = new PersonDAO(conn);
        eDao = new EventDAO(conn);
        assertNull(uDao.find(user.getUsername()));
        assertNull(pDao.find(person.getPersonID()));
        assertNull(eDao.find(event.getEventID()));
        assertNull(aDao.findByUsername(authtoken.getUsername()));
        db.closeConnection(false);
    }
}
