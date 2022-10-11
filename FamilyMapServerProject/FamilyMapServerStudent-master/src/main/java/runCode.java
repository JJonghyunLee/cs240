import dataAccess.*;
import model.Authtoken;
import model.Person;
import model.User;
import result.PersonResult;

import java.sql.Connection;
import java.sql.SQLException;

public class runCode {
    public static void main(String args[]) throws DataAccessException, SQLException {
        Database db = new Database();
        Connection conn = db.openConnection();
        AuthtokenDAO authtokenDAO = new AuthtokenDAO(conn);
        PersonDAO personDAO = new PersonDAO(conn);
        EventDAO eventDAO = new EventDAO(conn);
        UserDAO userDAO = new UserDAO(conn);
        authtokenDAO.clear();
        personDAO.clear();
        eventDAO.clear();
        userDAO.clear();
        db.closeConnection(true);
    }
}
