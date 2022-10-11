package service;

import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.EventDAO;
import model.Authtoken;
import model.Event;
import request.EventRequest;
import result.EventResult;
import result.EventAllResult;

/**
 * Service class that executes the event request
 */
public class EventService {
    public EventResult doService(String eventID, String username) {
        Database db = new Database();
        EventResult eventResult;
        try {
            db.openConnection();
            EventDAO eventDAO = new EventDAO(db.getConnection());
            Event event = eventDAO.find(eventID);
            db.closeConnection(true);

            if (event.getAssociatedUsername().equals(username)) eventResult = new EventResult(event, true);
            else eventResult = new EventResult("Error: Event unassociated with user.", false);

            return eventResult;
        } catch (DataAccessException e) {
            eventResult = new EventResult("Error: Data access exception.", false);
            e.printStackTrace();
            db.closeConnection(false);
            return eventResult;
        }
    }
}
