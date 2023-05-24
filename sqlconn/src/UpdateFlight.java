import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateFlight {
    Connection conn = null;
    Flights flight = null;

    UpdateFlight(Connection newConn) {
        conn = newConn;
    }

    public void alter_flight() {
        Scanner sc = new Scanner(System.in);

        flight = new Flights();
        System.out.println("insert id of flight");
        flight.flight_id = sc.nextInt();
        sc.nextLine();
        System.out.println("insert new destination of plane");
        flight.destination = sc.nextLine();
        alter_flight_information();
    }

    private void alter_flight_information() {
        try {
            // prepare a statement into a string to insert a user into the table.
            String sql = "UPDATE flights SET destination = ? WHERE flight_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            //preparing the string with the values from the current user
            statement.setString(1, flight.destination);
            statement.setInt(2, flight.flight_id);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("success!");
            }
        } catch (SQLException e) {
            System.out.println("Failed to insert the new user into the database!");
            e.printStackTrace();
            flight = null;
        }
    }
}
