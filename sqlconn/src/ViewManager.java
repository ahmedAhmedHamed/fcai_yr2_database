import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewManager {
    Connection conn = null;
    Flights flight = null;

    ViewManager(Connection newConn) {
        conn = newConn;
    }

    public void showFlights() {
        flight = new Flights();
        int seats = 0;
        //TODO take input from gui
        ResultSet result = getFlightsFromDatabase(seats);
        try{
            while (result.next()) {
                //TODO insert widgets into gui
            }
        } catch (SQLException e) {
            System.out.println("Failed to show the flights from the database! :(");
            e.printStackTrace();
        }

    }

    private ResultSet getFlightsFromDatabase(int total_seats) {
        try {
            // prepare a statement into a string to insert a user into the table.
            String sql = "SELECT f.flight_id, f.flight_date, f.sourcee, f.destination, a.aircraft_name, a.total_seats FROM flights f INNER JOIN aircraft a ON f.aircraft_id = a.aircraft_id WHERE f.flight_date = '?' AND f.sourcee = '?' AND f.destination = '?' AND a.total_seats >= ?\n";
            PreparedStatement statement = conn.prepareStatement(sql);
            //preparing the string with the values from the current user
            statement.setString(1, flight.flight_date);
            statement.setString(2, flight.source);
            statement.setString(3, flight.destination);
            statement.setInt(4, total_seats);

            return statement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Failed to insert the new user into the database!");
            e.printStackTrace();
        }
    }
}
