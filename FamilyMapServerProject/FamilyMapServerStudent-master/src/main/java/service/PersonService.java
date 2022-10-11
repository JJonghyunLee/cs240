package service;

import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.PersonDAO;
import model.Authtoken;
import model.Person;
import result.PersonResult;

/**
 * Service class that executes the person request
 */
public class PersonService {

    public PersonResult doService(String personID, String username) {
        Database db = new Database();
        PersonResult personResult;
        try {
            db.openConnection();
            PersonDAO personDAO = new PersonDAO(db.getConnection());
            Person person = personDAO.find(personID);
            String associatedUsername = person.getAssociatedUsername();
            if (person.getAssociatedUsername().equals(username)) personResult = new PersonResult(person, true);
            else personResult = new PersonResult("Error: Person unassociated with user.", false);
            db.closeConnection(true);
            return personResult;
        } catch (DataAccessException e) {
            e.printStackTrace();
            personResult = new PersonResult("Error: Data access exception", false);
            db.closeConnection(true);
            return personResult;
        }
    }
}
