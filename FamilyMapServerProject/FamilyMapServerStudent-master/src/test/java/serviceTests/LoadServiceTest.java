package serviceTests;

import dataAccess.*;
import model.Event;
import model.Person;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.LoadRequest;
import result.LoadResult;
import service.LoadService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoadServiceTest {
    private Database db;
    private Person newPerson;
    private Person mother;
    private Person father;
    private PersonDAO pDao;
    private Event birth;
    private Event marriage;
    private Event death;
    private EventDAO eDao;
    private User user;
    private UserDAO uDao;
    private AuthtokenDAO aDao;
    private List<Person> personList;
    private List<Event> eventList;
    private List<User> userList;
    private Connection conn;
    private LoadRequest request;
    private LoadService service;
    private LoadResult result;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        service = new LoadService();
        newPerson = new Person("Random_123A", "myUsername", "Jay",
                "Lee", "m", "Random_111F", "Random_111M", "Random_111S");
        father = new Person("Father_123A", "myUsername", "Jonh",
                "Lee", "m", "Random_111F", "Random_111M", "Random_111S");
        mother = new Person("Mother_123A", "myUsername", "Rebecca",
                "Lee", "f", "Random_111F", "Random_111M", "Random_111S");
        personList = new ArrayList<>();
        personList.add(newPerson);
        personList.add(father);
        personList.add(mother);


        birth = new Event("birthID", "username", "personID",
                0, 0, "country", "city", "birth", 0);
        marriage = new Event("marriageID", "username", "personID",
                0, 0, "country", "city", "marriage", 0);
        death = new Event("deathID", "username", "personID",
                0, 0, "country", "city", "death", 0);
        eventList = new ArrayList<>();
        eventList.add(birth);
        eventList.add(marriage);
        eventList.add(death);

        user = new User("myUsername", "myPassword", "jaydenle@byu.edu",
                "Jay", "Lee", "m", "Random_123A");
        userList = new ArrayList<>();
        userList.add(user);
        request = new LoadRequest(userList, personList, eventList);
        conn = db.openConnection();
        uDao = new UserDAO(conn);
        uDao.clear();
        pDao = new PersonDAO(conn);
        pDao.clear();
        eDao = new EventDAO(conn);
        eDao.clear();
        aDao = new AuthtokenDAO(conn);
        aDao.clear();
        db.closeConnection(true);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        conn = db.openConnection();
        uDao = new UserDAO(conn);
        uDao.clear();
        pDao = new PersonDAO(conn);
        pDao.clear();
        eDao = new EventDAO(conn);
        eDao.clear();
        aDao = new AuthtokenDAO(conn);
        aDao.clear();
        db.closeConnection(true);
    }

    @Test
    public void doServicePass() throws DataAccessException {
        conn = db.openConnection();
        uDao = new UserDAO(conn);
        pDao = new PersonDAO(conn);
        eDao = new EventDAO(conn);
        uDao.insert(user);
        pDao.insert(newPerson);
        eDao.insert(birth);
        db.closeConnection(true);
        result = service.doService(request);
        assertTrue(result.isSuccess());

        conn = db.openConnection();
        uDao = new UserDAO(conn);
        pDao = new PersonDAO(conn);
        eDao = new EventDAO(conn);
        aDao = new AuthtokenDAO(conn);
        assertNotNull(uDao.find(user.getUsername()));
        assertNotNull(aDao.findByUsername(user.getUsername()));
        assertEquals(3, pDao.findAll(newPerson.getAssociatedUsername()).size());
        assertEquals(3, eDao.findAll(birth.getAssociatedUsername()).size());
        db.closeConnection(false);
    }

    @Test
    public void doServiceFail() throws DataAccessException {
        userList.add(user);
        request = new LoadRequest(userList, personList, eventList);
        result = service.doService(request);
        assertFalse(result.isSuccess());
    }

}
