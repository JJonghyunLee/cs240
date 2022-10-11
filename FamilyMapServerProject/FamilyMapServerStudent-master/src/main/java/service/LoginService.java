package service;

import dataAccess.AuthtokenDAO;
import dataAccess.DataAccessException;
import dataAccess.Database;
import dataAccess.UserDAO;
import model.Authtoken;
import model.User;
import request.LoginRequest;
import result.LoginResult;

/**
 * Service class that executes a login request and returns a login result
 */
public class LoginService {
    public LoginResult doService(LoginRequest request) throws DataAccessException {
        LoginResult result;
        Database db = new Database();
        try {
            UserDAO userDAO = new UserDAO(db.getConnection());
            String username = request.getUsername();
            User user = userDAO.find(request.getUsername());
            if (user != null && request.getPassword().equals(user.getPassword())) {
                AuthtokenDAO authtokenDAO = new AuthtokenDAO(db.getConnection());
                Authtoken authtoken = authtokenDAO.findByUsername(user.getUsername());
                result = new LoginResult(authtoken.getAuthtoken(), user.getUsername(), user.getPersonID(), true);
            } else {
                result = new LoginResult("Error: Invalid username or password.", false);
            }
            db.closeConnection(true);
            return result;
        } catch (DataAccessException e) {
            e.printStackTrace();
            result = new LoginResult("Error: Data could not be accessed.", false);
            db.closeConnection(false);
            return result;
        }
    }
}
