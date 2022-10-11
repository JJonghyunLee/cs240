package request;

import model.Event;
import model.Person;
import model.User;

import java.util.List;

/**
 * Object representing a load request
 * Clears the database and loads a different set of data to the database
 */
public class LoadRequest {
    /**
     * List of users to load
     */
    List<User> users;
    /**
     * List of people to load
     */
    List<Person> persons;
    /**
     * List of events to load
     */
    List<Event> events;

    /**
     * Constructor for the load request
     *
     * @param userList   List of users to load
     * @param personList List of people to load
     * @param eventList  List of events to load
     */
    public LoadRequest(List<User> userList, List<Person> personList, List<Event> eventList) {
        this.users = userList;
        this.persons = personList;
        this.events = eventList;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUserList(List<User> users) {
        this.users = users;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
