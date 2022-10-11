package service;

import dataAccess.*;
import result.ClearResult;

/**
 * Service class that executes a clear request and returns a clear result
 */
public class ClearService {

    /**
     * Calls the database class and executes clear
     *
     * @return ClearResult object representing the result of the execution
     */
    public ClearResult doService() throws DataAccessException {
        ClearResult result;
        Database db = new Database();
        try {
            AuthtokenDAO authtokenDAO = new AuthtokenDAO(db.getConnection());
            authtokenDAO.clear();
            EventDAO eventDAO = new EventDAO(db.getConnection());
            eventDAO.clear();
            UserDAO userDAO = new UserDAO(db.getConnection());
            userDAO.clear();
            PersonDAO personDAO = new PersonDAO(db.getConnection());
            personDAO.clear();
            db.closeConnection(true);
            result = new ClearResult("clear succeeded.", true);
        } catch (DataAccessException e) {
            e.printStackTrace();
            db.closeConnection(false);
            result = new ClearResult("Error: Data access exception", false);
        }
        return result;
    }
}
