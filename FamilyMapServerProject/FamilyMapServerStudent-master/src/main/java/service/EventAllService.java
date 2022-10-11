package service;

import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.EventDAO;
import model.Authtoken;
import model.Event;
import model.Person;
import result.EventAllResult;
import result.PersonAllResult;

import java.sql.Connection;
import java.util.List;

/**
 * Service class that executes the user event request
 */
public class EventAllService {

    public EventAllResult doService(String username) {
        EventAllResult result;
        List<Event> list = eventAllMethod(username);
        if (list != null) result = new EventAllResult(list, true);
        else result = new EventAllResult("Error: Data access exception.", false);
        return result;
    }

    private List<Event> eventAllMethod(String username) {
        Database db = new Database();
        try {
            Connection conn = db.openConnection();
            EventDAO eventDAO = new EventDAO(conn);
            List<Event> list = eventDAO.findAll(username);
            db.closeConnection(true);
            return list;
        } catch (DataAccessException e) {
            e.printStackTrace();
            db.closeConnection(false);
            return null;
        }
    }

    /**
     * Returns the authtoken of the current user
     *
     * @return Authtoken of the current user
     */
    public Authtoken getAuthtoken() {
        return null;
    }

    /**
     * Uses the authtoken to find all family members of a user and returns data as a user family result object
     *
     * @param authtoken Authtoken of the current user
     * @return Object representing the user event result
     */
    public EventAllResult getEvents(Authtoken authtoken) {
        return null;
    }

}
