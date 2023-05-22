import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * class responsible for handling everything related to user accounts
 */
public class AccountManager {
    Connection conn;
    User current_user;

    /**
     * constructs account manager
     * @param newConn : mysql connection
     */
    AccountManager(Connection newConn) {
        conn = newConn;
    }

    /**
     * registers the user
     */
    public void register() {
        Scanner sc = new Scanner(System.in);
        current_user = new User();
        System.out.println("insert your username");
        current_user.username = sc.nextLine();

        //TODO make a function to check if username is correct.

        System.out.println("insert your password, its length needs to be between 3 and 10 (inclusive).");
        current_user.password = sc.nextLine();

        while (current_user.password.length() > 10 || current_user.password.length() < 3) {
            System.out.println("invalid password length.");
            System.out.println("its length needs to be between 3 and 10 (inclusive).");
            current_user.password = sc.nextLine();
        }

        current_user.user_type = "user";

        addNewUserToDatabase();
    }

    /**
     * adds the current user to the database
     */
    private void addNewUserToDatabase() {
        try {
            // Insert the new user into the database
            String sql = "INSERT INTO user (username, password, userType) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            // her will inset this info in table in database
            statement.setString(1, current_user.username);
            statement.setString(2, current_user.password);
            statement.setString(3, current_user.user_type);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Failed to insert the new user into the database!");
            e.printStackTrace();
        }
    }
}
