import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddFlight {

    Connection conn;
    Flights flight = null;

    AddFlight(Connection newConn) {
        conn = newConn;
    }

    public void add_flight() {
        flight = new Flights();
        //TODO add input with gui
        Scanner sc = new Scanner(System.in);
        System.out.println("insert destination");
        flight.destination = sc.nextLine();
        System.out.println("insert flightdate");
        flight.flight_date = sc.nextLine();
        System.out.println("insert source");
        flight.source = sc.nextLine();
        System.out.println("insert aircraft id");
        flight.flight_id = sc.nextInt();
        add_flight_to_database();
    }

    private void add_flight_to_database() {
        try {
            // prepare a statement into a string to insert a user into the table.
            String sql = "INSERT INTO flights (flight_date, sourcee, destination, aircraft_id) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            //preparing the string with the values from the current user
            statement.setString(1, flight.flight_date);
            statement.setString(2, flight.source);
            statement.setString(3, flight.destination);
            statement.setInt(4, flight.aircraft_id);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new aircraft was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Failed to insert the new user into the database!");
            e.printStackTrace();
            flight = null;
        }
    }
}
