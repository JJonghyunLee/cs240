package daoTests;

import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.UserDAO;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    private Database db;
    private User newUser;
    private UserDAO uDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        newUser = new User("myUsername", "myPassword", "jaydenle@byu.edu",
                "Jay", "Lee", "m", "Random_123A");

        Connection conn = db.getConnection();
        uDao = new UserDAO(conn);
        uDao.clear();
    }

    @AfterEach
    public void tearDown() {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        uDao.insert(newUser);
        User compareTest = uDao.find(newUser.getUsername());
        assertNotNull(compareTest);
        assertEquals(newUser, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        uDao.insert(newUser);
        assertThrows(DataAccessException.class, () -> uDao.insert(newUser));
    }

    @Test
    public void findPass() throws DataAccessException {
        uDao.insert(newUser);
        User compareTest = uDao.find(newUser.getUsername());
        assertNotNull(compareTest);
        assertEquals(newUser, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException {
        uDao.insert(newUser);
        String invalidID = "INVALID";
        User user = uDao.find(invalidID);
        assertNull(user);
    }

    @Test
    public void clearPass() throws DataAccessException {
        uDao.insert(newUser);
        uDao.clear();
        assertNull(uDao.find(newUser.getPersonID()));
    }
}