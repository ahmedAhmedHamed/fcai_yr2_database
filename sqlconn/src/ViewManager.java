import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ViewManager {
    Connection conn = null;
    Flights flight = null;

    ViewManager(Connection newConn) {
        conn = newConn;
    }

    public void print_report() {
        try{
            ResultSet Rflights = conn.prepareStatement("SELECT COUNT(*) FROM flights").executeQuery();
            ResultSet Rbookings = conn.prepareStatement("SELECT COUNT(*) FROM booking").executeQuery();
            ResultSet Rusers = conn.prepareStatement("SELECT COUNT(*) FROM users").executeQuery();
            ResultSet Raircrafts = conn.prepareStatement("SELECT COUNT(*) FROM aircraft").executeQuery();

            if (Rflights.next())
            {
                int flights = Rflights.getInt(1);
                System.out.print("number of flights: ");
                System.out.println(flights);
            }

            if (Rbookings.next())
            {
                int bookings = Rbookings.getInt(1);
                System.out.print("number of bookings: ");
                System.out.println(bookings);
            }

            if (Rusers.next())
            {
                int users = Rusers.getInt(1);
                System.out.print("number of users: ");
                System.out.println(users);
            }

            if (Raircrafts.next())
            {
                int aircrafts = Raircrafts.getInt(1);
                System.out.print("number of aircrafts: ");
                System.out.println(aircrafts);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showFlights() {
        flight = new Flights();
        int seats = 0;
        //TODO take input from gui
        Scanner sc = new Scanner(System.in);
        System.out.println("insert the minimum number of seats");
        seats = sc.nextInt();
        sc.nextLine();
        System.out.println("insert the flight date");
        flight.flight_date = sc.nextLine();
        System.out.println("insert the destination");
        flight.destination = sc.nextLine();
        System.out.println("insert the source");
        flight.source = sc.nextLine();
        ResultSet result = getFlightsFromDatabase(seats);
        if (result == null) {
            System.out.println("invalid query");
            return;
        }
        try{
            while (result.next()) {
                System.out.println(result.getInt(1));
                System.out.println(result.getString(2));
                System.out.println(result.getString(3));
                System.out.println(result.getString(4));
                System.out.println(result.getInt(5));
                System.out.println("_______");
            }
        } catch (SQLException e) {
            System.out.println("Failed to show the flights from the database! :(");
            e.printStackTrace();
        }

    }
    public void show_all_flights() {
        ResultSet result = get_all_flights();

        try{
            while (result.next()) {
                System.out.println(result.getInt(1));
                System.out.println(result.getString(2));
                System.out.println(result.getString(3));
                System.out.println(result.getString(4));
                System.out.println(result.getInt(5));
                System.out.println("_______");
            }
        } catch (SQLException e) {
            System.out.println("Failed to show the flights from the database! :(");
            e.printStackTrace();
        }
    }

    public void show_booking() {
        ResultSet result = getBookingsFromDatabase();
        try{
            while (result.next()) {
                System.out.println(result.getInt(1));
                System.out.println(result.getInt(2));
                System.out.println(result.getInt(3));
                System.out.println(result.getString(4));
                System.out.println(result.getInt(5));
                System.out.println("_______");
            }
        } catch (SQLException e) {
            System.out.println("Failed to show the flights from the database! :(");
            e.printStackTrace();
        }
    }

    private ResultSet getFlightsFromDatabase(int total_seats) {
        try {
            // prepare a statement into a string to insert a user into the table.
            String sql = "SELECT f.flight_id, f.flight_date, f.sourcee, f.destination, a.aircraft_name, a.total_seats FROM flights f INNER JOIN aircraft a ON f.aircraft_id = a.aircraft_id WHERE f.flight_date = ? AND f.sourcee = ? AND f.destination = ? AND a.total_seats >= ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            //preparing the string with the values from the current user
            statement.setString(1, flight.flight_date);
            statement.setString(2, flight.source);
            statement.setString(3, flight.destination);
            statement.setInt(4, total_seats);

            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ResultSet get_all_flights() {
        try {
            String sql = "SELECT * FROM flights";
            PreparedStatement statement = conn.prepareStatement(sql);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ResultSet getBookingsFromDatabase() {
        try {
            // prepare a statement into a string to insert a user into the table.
            String sql = "SELECT * from booking";
            PreparedStatement statement = conn.prepareStatement(sql);
            //preparing the string with the values from the current user

            return statement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Failed to insert the new user into the database!");
            e.printStackTrace();
        }
        return null;
    }
}
