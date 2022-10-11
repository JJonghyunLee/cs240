package daoTests;

import dataAccess.AuthtokenDAO;
import dataAccess.DataAccessException;
import dataAccess.Database;
import model.Authtoken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthtokenDAOTest {
    private Database db;
    private Authtoken authtoken;
    AuthtokenDAO aDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        authtoken = new Authtoken("random_authtoken", "username");

        Connection conn = db.getConnection();
        aDao = new AuthtokenDAO(conn);
        aDao.clear();
    }

    @AfterEach
    public void tearDown() {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        aDao.insert(authtoken);
        Authtoken compareTest = aDao.findByUsername(authtoken.getUsername());
        assertNotNull(compareTest);
        assertEquals(authtoken.getAuthtoken(), compareTest.getAuthtoken());
    }

    @Test
    public void insertFail() throws DataAccessException {
        aDao.insert(authtoken);
        assertThrows(DataAccessException.class, () -> aDao.insert(authtoken));
    }

    @Test
    public void findByUsernamePass() throws DataAccessException {
        aDao.insert(authtoken);
        Authtoken compareTest = aDao.findByUsername(authtoken.getUsername());
        assertNotNull(compareTest);
        assertEquals(authtoken.getAuthtoken(), compareTest.getAuthtoken());
    }

    @Test
    public void findByUsernameFail() throws DataAccessException {
        aDao.insert(authtoken);
        String invalidUsername = "invalid";
        Authtoken compareTest = aDao.findByUsername(invalidUsername);
        assertNull(compareTest);
    }

    @Test
    public void findByAuthtokenPass() throws DataAccessException {
        aDao.insert(authtoken);
        Authtoken compareTest = aDao.findByAuthtoken(authtoken.getAuthtoken());
        assertNotNull(authtoken.getUsername(), compareTest.getUsername());
    }

    @Test
    public void findByAuthtokenFail() throws DataAccessException {
        aDao.insert(authtoken);
        String invalidUsername = "invalid";
        Authtoken compareTest = aDao.findByAuthtoken(invalidUsername);
        assertNull(compareTest);
    }

    @Test
    public void clearPass() throws DataAccessException {
        aDao.insert(authtoken);
        aDao.clear();
        assertNull(aDao.findByUsername(authtoken.getUsername()));
    }
}
