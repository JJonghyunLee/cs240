package serviceTests;

import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.EventDAO;
import dataAccess.PersonDAO;
import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.RegisterRequest;
import result.ClearResult;
import result.FillResult;
import result.RegisterResult;
import service.ClearService;
import service.FillService;
import service.RegisterService;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class FillServiceTest {
    private Database db;
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
        RegisterService registerService = new RegisterService();
        RegisterRequest registerRequest = new RegisterRequest("myUsername", "myPassword", "jaydenle@byu.edu",
                "Jay", "Lee", "m");
        RegisterResult registerResult = registerService.doService(registerRequest);
        assertTrue(registerResult.isSuccess());
        FillService fillService = new FillService();
        FillResult fillResult = fillService.doService("myUsername", 4);
        assertTrue(fillResult.isSuccess());

        conn = db.openConnection();
        PersonDAO pDao = new PersonDAO(conn);
        EventDAO eDao = new EventDAO(conn);
        assertEquals(31, pDao.findAll("myUsername").size());
        assertEquals(91, eDao.findAll("myUsername").size());
        db.closeConnection(false);
    }

    @Test
    public void doServiceFail() throws DataAccessException {
        RegisterService registerService = new RegisterService();
        RegisterRequest registerRequest = new RegisterRequest("myUsername", "myPassword", "jaydenle@byu.edu",
                "Jay", "Lee", "m");
        RegisterResult registerResult = registerService.doService(registerRequest);
        assertTrue(registerResult.isSuccess());
        FillService fillService = new FillService();
        FillResult fillResult = fillService.doService("invalid", 4);
        assertFalse(fillResult.isSuccess());
    }

    @Test
    public void fillMethodPass() throws DataAccessException {
        RegisterService registerService = new RegisterService();
        RegisterRequest registerRequest = new RegisterRequest("myUsername", "myPassword", "jaydenle@byu.edu",
                "Jay", "Lee", "m");
        RegisterResult registerResult = registerService.doService(registerRequest);
        assertTrue(registerResult.isSuccess());
        FillService fillService = new FillService();
        boolean success = fillService.fillMethod("myUsername", 4);
        assertTrue(success);

        conn = db.openConnection();
        PersonDAO pDao = new PersonDAO(conn);
        EventDAO eDao = new EventDAO(conn);
        assertEquals(31, pDao.findAll("myUsername").size());
        assertEquals(91, eDao.findAll("myUsername").size());
        db.closeConnection(false);
    }

    @Test
    public void fillMethodFail() throws DataAccessException {
        RegisterService registerService = new RegisterService();
        RegisterRequest registerRequest = new RegisterRequest("myUsername", "myPassword", "jaydenle@byu.edu",
                "Jay", "Lee", "m");
        RegisterResult registerResult = registerService.doService(registerRequest);
        assertTrue(registerResult.isSuccess());
        FillService fillService = new FillService();
        boolean success = fillService.fillMethod("invalid", 4);
        assertFalse(success);
    }

}
