package service;

import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.PersonDAO;
import model.Authtoken;
import model.Person;
import result.PersonAllResult;

import java.sql.Connection;
import java.util.List;

/**
 * Service class that executes the family request class
 */
public class PersonAllService {

    public PersonAllResult doService(String username) {
        PersonAllResult result;
        List<Person> list = personAllMethod(username);
        if (list != null) result = new PersonAllResult(list, true);
        else result = new PersonAllResult("Error: Data access exception.", false);
        return result;
    }

    private List<Person> personAllMethod(String username) {
        Database db = new Database();
        try {
            Connection conn = db.openConnection();
            PersonDAO personDAO = new PersonDAO(conn);
            List<Person> list = personDAO.findAll(username);
            db.closeConnection(true);
            return list;
        } catch (DataAccessException e) {
            e.printStackTrace();
            db.closeConnection(false);
            return null;
        }
    }
}
