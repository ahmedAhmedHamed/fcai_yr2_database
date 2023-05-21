import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * main wrapper class responsible for calling functions from other classes
 */
public class mainWrapper {
    Connection conn;
    mainWrapper() {
        connect_to_database();
    }

    /**
     * responsible for connecting to the database
     */
    private void connect_to_database() {
        try {
            // Connect to the MySQL database
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e)
            {
                System.out.println(e);
            }
            String url = "jdbc:mysql://localhost:3306/master";//name of scheme
            String user = "root";
            String password = "asd!12SSe*2AxOOwSfedSx,<;ls431SWfefasdfewasdfwaSasdasd";

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database!");
            e.printStackTrace();
        }
    }
}
