import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateFlight {
    Connection conn = null;
    Flights flight = null;

    UpdateFlight(Connection newConn) {
        conn = newConn;
    }

    public void alter_flight() {
        flight = new Flights();
        //TODO put in aircraft the id and the number of seats to be set.
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
                System.out.println("A new aircraft was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Failed to insert the new user into the database!");
            e.printStackTrace();
            flight = null;
        }
    }
}
