package service;

import com.google.gson.Gson;
import dataAccess.*;
import model.*;
import request.FillRequest;
import result.FillResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.util.List;
import java.util.Random;

/**
 * Generates ancestors for a user and adds to database
 */
public class FillService {

    public FillResult doService(String username, int generation) throws DataAccessException {
        boolean success = fillMethod(username, generation);
        int numPerson = getPersonNum(generation) + 1;
        int numEvents = getEventNum(numPerson) + 1;
        FillResult result;
        if (success)
            result = new FillResult("Successfully added " + numPerson + " persons and " + numEvents + " to the database.", true);
        else result = new FillResult("Error: Data access exception.", false);
        return result;
    }

    public boolean fillMethod(String username, int generation) {
        Database db = new Database();
        try {
            Connection conn = db.openConnection();
            PersonDAO personDAO = new PersonDAO(conn);
            UserDAO userDAO = new UserDAO(conn);
            User user = userDAO.find(username);
            if (user == null) throw new DataAccessException("Error: User does not exist.");
            String personID = user.getPersonID();
            personDAO.clearAncestors(username);
            personDAO = new PersonDAO(conn);
            Person person = new Person(user);
            EventDAO eventDAO = new EventDAO(conn);
            generateUserBirth(person, eventDAO);
            db.closeConnection(true);
            conn = db.openConnection();
            personDAO = new PersonDAO(conn);
            personDAO.insert(person);
            db.closeConnection(true);
            generateAncestors(username, personID, generation);
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            db.closeConnection(false);
            return false;
        }
    }

    private void generateAncestors(String username, String personID, int generation) throws DataAccessException {
        Database db = new Database();
        Connection conn = db.getConnection();

        Person father = new Person(username, "m");
        Person mother = new Person(username, "f");
        father.setSpouseID(mother.getPersonID());
        mother.setSpouseID(father.getPersonID());

        EventDAO eventDAO = new EventDAO(conn);
        PersonDAO personDAO = new PersonDAO(conn);
        Person child = personDAO.find(personID);
        int childBirthYear = eventDAO.getBirthEvent(child).getYear();
        generateBirthEvent(father, childBirthYear, eventDAO);
        generateBirthEvent(mother, childBirthYear, eventDAO);
        db.closeConnection(true);

        conn = db.openConnection();
        eventDAO = new EventDAO(conn);
        generateMarriageEvent(father, mother, childBirthYear, eventDAO);
        db.closeConnection(true);

        conn = db.openConnection();
        eventDAO = new EventDAO(conn);
        personDAO = new PersonDAO(conn);
        generateDeathEvent(father, eventDAO);
        generateDeathEvent(mother, eventDAO);
        personDAO.insert(father);
        personDAO.insert(mother);
        personDAO.setMother(personID, mother.getPersonID());
        personDAO.setFather(personID, father.getPersonID());
        db.closeConnection(true);
        generation--;
        if (generation > 0) {
            generateAncestors(username, mother.getPersonID(), generation);
            generateAncestors(username, father.getPersonID(), generation);
        }
    }

    private void generateUserBirth(Person person, EventDAO eventDAO) throws DataAccessException {
        Location location = getRandomLocation();
        Event event = new Event(person, location);
        eventDAO.insert(event);
    }

    private void generateBirthEvent(Person person, int childBirthYear, EventDAO eventDAO) throws DataAccessException {
        Location location = getRandomLocation();
        Event eventBirth = new Event(person, location, "birth", childBirthYear);
        eventDAO.insert(eventBirth);
    }

    private void generateMarriageEvent(Person mother, Person father, int childBirthYear, EventDAO eventDAO) throws DataAccessException {
        int fatherBirthYear = eventDAO.getBirthEvent(father).getYear();
        int motherBirthYear = eventDAO.getBirthEvent(mother).getYear();

        int olderYear;
        if (fatherBirthYear > motherBirthYear)
            olderYear = fatherBirthYear;
        else
            olderYear = motherBirthYear;

        int marriageYear = (int) (Math.random() * 30 + (olderYear + 13));
        Location location = getRandomLocation();
        Event fatherEventMarriage = new Event(father, location, "marriage", marriageYear);
        Event motherEventMarriage = new Event(mother, location, "marriage", marriageYear);
        eventDAO.insert(fatherEventMarriage);
        eventDAO.insert(motherEventMarriage);
    }

    private void generateDeathEvent(Person person, EventDAO eventDAO) throws DataAccessException {
        int birthYear = eventDAO.getBirthEvent(person).getYear();
        int marriageYear = eventDAO.getMarriageEvent(person).getYear();
        Location location = getRandomLocation();
        Event eventDeath = new Event(person, location, "death", birthYear, marriageYear);
        eventDAO.insert(eventDeath);
    }

    private int getPersonNum(int generation) {
        int total = 0;
        for (int i = generation; i > 0; i--) {
            int square = (int) Math.pow(2, i);
            total += square;
        }
        return total;
    }

    private int getEventNum(int personNum) {
        return personNum * 3;
    }

    private Location getRandomLocation() {
        File filePath = new File("C:\\Users\\이종현\\FamilyMapServerProject\\FamilyMapServerStudent-master\\json\\locations.json");
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            Gson gson = new Gson();
            LocationData locationData = gson.fromJson(br, LocationData.class);
            List<Location> list = locationData.getList();
            Random rand = new Random();
            Location location = list.get(rand.nextInt(list.size()));
            return location;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error: File not found");
            return null;
        }
    }
}