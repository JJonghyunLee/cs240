package service;

import dataAccess.*;
import model.Authtoken;
import model.Person;
import model.User;
import request.RegisterRequest;
import result.RegisterResult;

/**
 * Service class that executes a register request and returns a register result
 */
public class RegisterService {

    public RegisterResult doService(RegisterRequest request) throws DataAccessException {
        Database db = null;
        try {
            db = new Database();
            UserDAO userDAO = new UserDAO(db.getConnection());
            User user = new User(request);
            userDAO.insert(user);
            AuthtokenDAO authtokenDAO = new AuthtokenDAO(db.getConnection());
            Authtoken authtoken = new Authtoken(request);
            authtokenDAO.insert(authtoken);
            db.closeConnection(true);
            RegisterResult result = new RegisterResult(authtoken.getAuthtoken(), user.getUsername(), user.getPersonID(), true);
            return result;
        } catch (DataAccessException e) {
            e.printStackTrace();
            db.closeConnection(false);
            RegisterResult result = new RegisterResult("Error: Data could not be accessed.", false);
            return result;
        }
    }
}
