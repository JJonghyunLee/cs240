package service;

import dataAccess.*;
import model.Authtoken;
import model.Event;
import model.Person;
import model.User;
import request.LoadRequest;
import result.LoadResult;

/**
 * Service class that executes a load request and returns a load result
 */
public class LoadService {
    public LoadResult doService(LoadRequest request) throws DataAccessException {
        Database db = new Database();
        LoadResult result;
        int userCount = 0;
        int eventCount = 0;
        int personCount = 0;
        try {
            UserDAO userDAO = new UserDAO(db.getConnection());
            EventDAO eventDAO = new EventDAO(db.getConnection());
            PersonDAO personDAO = new PersonDAO(db.getConnection());
            AuthtokenDAO authtokenDAO = new AuthtokenDAO(db.getConnection());
            userDAO.clear();
            eventDAO.clear();
            personDAO.clear();
            authtokenDAO.clear();

            for (User user : request.getUsers()) {
                userDAO.insert(user);
                Authtoken authtoken = new Authtoken(user.getUsername());
                authtokenDAO.insert(authtoken);
                userCount++;
            }
            for (Event event : request.getEvents()) {
                eventDAO.insert(event);
                eventCount++;
            }
            for (Person person : request.getPersons()) {
                personDAO.insert(person);
                personCount++;
            }
            db.closeConnection(true);
            result = new LoadResult("Successfully added " + userCount + " users, " + personCount +
                    " persons, and " + eventCount + "events to the database.", true);
            return result;
        } catch (DataAccessException e) {
            e.printStackTrace();
            db.closeConnection(false);
            result = new LoadResult("Error: Data could not be accessed.", false);
            return result;
        }
    }
}
