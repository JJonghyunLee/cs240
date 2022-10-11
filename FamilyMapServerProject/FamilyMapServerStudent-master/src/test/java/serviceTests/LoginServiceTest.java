package serviceTests;

import dataAccess.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.LoginRequest;
import request.RegisterRequest;
import result.LoginResult;
import service.LoginService;
import service.RegisterService;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginServiceTest {
    private UserDAO uDao;
    private AuthtokenDAO aDao;
    private EventDAO eDao;
    private PersonDAO pDao;
    private Database db;
    private Connection conn;

    @BeforeEach
    public void setUp() throws DataAccessException {
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

    @Test
    public void doServicePass() throws DataAccessException {
        RegisterRequest registerRequest = new RegisterRequest("myUsername", "myPassword", "jaydenle@byu.edu",
                "Jay", "Lee", "m");
        RegisterService registerService = new RegisterService();
        registerService.doService(registerRequest);
        LoginRequest request = new LoginRequest("myUsername", "myPassword");
        LoginService service = new LoginService();
        LoginResult result = service.doService(request);
        assertTrue(result.isSuccess());
    }

    @Test
    public void doServiceFail() throws DataAccessException {
        RegisterRequest registerRequest = new RegisterRequest("myUsername", "myPassword", "jaydenle@byu.edu",
                "Jay", "Lee", "m");
        RegisterService registerService = new RegisterService();
        registerService.doService(registerRequest);
        LoginRequest request = new LoginRequest("invalid", "invalid");
        LoginService service = new LoginService();
        LoginResult result = service.doService(request);
        assertFalse(result.isSuccess());
    }
}
