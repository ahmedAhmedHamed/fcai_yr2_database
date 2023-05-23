import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * class responsible for logging a user in
 */
public class Login {
    User current_user = null;
    Connection conn;
    Login(Connection newConn) {
        conn = newConn;
    }


    private String[] loginGUI() {
        JFrame frame = new JFrame("Login");
        JPanel panel = new JPanel();
        String[] details = new String[2];
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(panel);
        panel.setLayout(null);

        JLabel username_label = new JLabel("username");
        JLabel password_label = new JLabel("password");
        username_label.setBounds(10, 20, 80, 25);
        panel.add(username_label);

        JTextField username_textField = new JTextField(50);
        username_textField.setBounds(100, 20, 165, 25);
        panel.add(username_textField);

        JTextField password_textField = new JTextField(10);
        username_textField.setBounds(100, 20, 165, 25);
        panel.add(password_textField);

        frame.setVisible(true);
        return details;
    }
    /**
     * login - logs the user in, gets the users from the database and searches for the user inserted.
     */
    public void login() {
        String username;
        String password;

        loginGUI();
//        username = sc.nextLine();
//        password = sc.nextLine();
//
//        ResultSet users = getUsers();
//
//        if (users == null) {
//            System.out.println("an error occurred while getting the users from sql table");
//            return;
//        }
//
//        try {
//            while (users.next()) {
//                User current = parseRowIntoUser(users);
//                if (current == null)
//                    continue;
//                if (current.username.equals(username)) {
//                    if (current.password.equals(password)) {
//                        current_user = current;
//                        return;
//                    }
//                    System.out.println("wrong username or password");
//                    return;
//                }
//            }
//        } catch(SQLException e) {
//            System.out.println(e);
//        }
//
//        System.out.println("wrong username or password");
    }

    /**
     * transforms a resultset user into a User user
     * @param user : resultset to be converted
     * @return : returns null on failure, the User on success
     */
    private User parseRowIntoUser(ResultSet user) {
        User newUser = new User();

        try {
            newUser.user_id = user.getInt(1);
            newUser.username = user.getString(2);
            newUser.password = user.getString(3);
            newUser.user_type = user.getString(4);
            return (newUser);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return (null);
    }

    /**
     * gets all the users from the sql table and returns them in a resultset
     * @return : Resultset if successful, null if unsuccessful.
     */
    private ResultSet getUsers() {
        try {
            // Insert the new user into the database
            String sql = "SELECT * FROM users";
            PreparedStatement statement = conn.prepareStatement(sql);

            return (statement.executeQuery());
        } catch (SQLException e) {
            System.out.println("Failed to insert the new user into the database!");
            e.printStackTrace();
        }
        return (null);
    }
}
