import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddAircraft {
    Connection conn;
    Aircraft Aircraft = null;

    AddAircraft(Connection newConn) {
        conn = newConn;
    }

    public void add_aircraft() {
        Aircraft = new Aircraft();
        Scanner sc = new Scanner(System.in);

        System.out.print("insert aircraft name: ");
        Aircraft.aircraft_name = sc.nextLine();
        System.out.print("insert total seats: ");
        Aircraft.total_seats = sc.nextInt();
        //TODO add validation
        add_aircraft_to_database();
    }

    private void add_aircraft_to_database() {
        try {
            // prepare a statement into a string to insert a user into the table.
            String sql = "INSERT INTO aircraft (aircraft_name, total_seats) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            //preparing the string with the values from the current user
            statement.setString(1, Aircraft.aircraft_name);
            statement.setInt(2, Aircraft.total_seats);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new aircraft was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Failed to insert the new user into the database!");
            e.printStackTrace();
            Aircraft = null;
        }
    }
}
