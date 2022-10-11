package dataAccess;

import model.Authtoken;
import model.Event;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data access object for Authtokens
 */
public class AuthtokenDAO {
    /**
     * Connection to the database
     */
    private final Connection conn;

    /**
     * Constructor for the AuthtokenDAO class
     *
     * @param conn Connection to the database
     */
    public AuthtokenDAO(Connection conn) {
        this.conn = conn;
    }

    public Authtoken findByUsername(String username) throws DataAccessException {
        Authtoken token = null;
        ResultSet rs;
        String sql = "SELECT * FROM Authtoken WHERE username = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                token = new Authtoken(rs.getString("authtoken"), rs.getString("username"));
                return token;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding authtoken in the database");
        }
    }

    public Authtoken findByAuthtoken(String authtoken) throws DataAccessException {
        Authtoken token = null;
        ResultSet rs;
        String sql = "SELECT * FROM Authtoken WHERE authtoken = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authtoken);
            rs = stmt.executeQuery();
            if (rs.next()) {
                token = new Authtoken(rs.getString("authtoken"), rs.getString("username"));
                return token;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding authtoken in the database");
        }
    }

    public void insert(Authtoken authtoken) throws DataAccessException {
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO Authtoken (authtoken, username) VALUES(?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, authtoken.getAuthtoken());
            stmt.setString(2, authtoken.getUsername());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting an authtoken into the database");
        }
    }

    /**
     * Clears all events from the database
     *
     * @throws DataAccessException Error thrown when database cannot be accessed
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM Authtoken";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the Authtoken table");
        }
    }
}
