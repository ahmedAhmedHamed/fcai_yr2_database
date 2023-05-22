import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateAircraft {
    Connection conn = null;
    Aircraft Aircraft = null;

    UpdateAircraft(Connection newConn) {
        conn = newConn;
    }

    public void alter_aircraft() {
        Aircraft = new Aircraft();
        //TODO put in aircraft the id and the number of seats to be set.
        alter_aircraft_information();
    }

    private void alter_aircraft_information() {
        try {
            // prepare a statement into a string to insert a user into the table.
            String sql = "UPDATE aircraft SET total_seats = ? WHERE aircraft_id = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            //preparing the string with the values from the current user
            statement.setInt(1, Aircraft.total_seats);
            statement.setInt(2, Aircraft.aircraft_id);
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
