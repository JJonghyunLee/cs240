package serviceTests;

import dataAccess.*;
import model.Authtoken;
import model.Event;
import model.Person;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.LoginRequest;
import request.RegisterRequest;
import result.ClearResult;
import result.LoginResult;
import result.RegisterResult;
import service.ClearService;
import service.LoginService;
import service.RegisterService;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterServiceTest {
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
    private ClearService clearService;
    private ClearResult clearResult;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        clearService = new ClearService();
        clearResult = clearService.doService();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        clearResult = clearService.doService();
    }

    @Test
    public void doServicePass() throws DataAccessException {
        RegisterRequest registerRequest = new RegisterRequest("myUsername", "myPassword", "jaydenle@byu.edu",
                "Jay", "Lee", "m");
        RegisterService registerService = new RegisterService();
        RegisterResult result = registerService.doService(registerRequest);
        assertTrue(result.isSuccess());
        LoginRequest request = new LoginRequest("myUsername", "myPassword");
        LoginService service = new LoginService();
        LoginResult loginResult = service.doService(request);
        assertTrue(loginResult.isSuccess());
    }

    @Test
    public void doServiceFail() throws DataAccessException {
        RegisterRequest registerRequest = new RegisterRequest("myUsername", "myPassword", "jaydenle@byu.edu",
                "Jay", "Lee", "m");
        RegisterService registerService = new RegisterService();
        RegisterResult result = registerService.doService(registerRequest);
        assertTrue(result.isSuccess());
        result = registerService.doService(registerRequest);
        assertFalse(result.isSuccess());
    }
}