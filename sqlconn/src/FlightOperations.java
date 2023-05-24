import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class FlightOperations {
    Connection conn = null;
    Flights flight = null;
    Booking booking = null;

    FlightOperations(Connection newConn) {
        conn = newConn;
    }

    public void book_flight() {
        booking = new Booking();
        Scanner sc = new Scanner(System.in);
        //TODO take input and place it in the booking
        System.out.println("insert classtype");
        booking.class_type = sc.nextLine();
        System.out.println("insert user ID");
        booking.user_id = sc.nextInt();
        System.out.println("insert flight ID");
        booking.flight_id = sc.nextInt();
        System.out.println("insert seats booked");
        booking.seats_booked = sc.nextInt();
        add_booking_to_database();
    }

    public void cancelBooking() {
        String sql = "DELETE FROM booking WHERE booking_id = ?";
        int id = 0;
        System.out.println("insert ID");
        Scanner sc = new Scanner(System.in);
        id = sc.nextInt();
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void change_booking_class() {
        int id = 0;
        String new_class_type = "";
        Scanner sc = new Scanner(System.in);
        id = sc.nextInt();
        new_class_type = sc.nextLine();
        //TODO take input from GUI
        try {
            // prepare a statement into a string to insert a user into the table.
            String sql = "UPDATE booking SET class_type = '?' WHERE booking_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            //preparing the string with the values from the current user
            statement.setString(1, new_class_type);
            statement.setInt(2, id);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("classtype was changed successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void add_booking_to_database() {
        try {
            // prepare a statement into a string to insert a user into the table.
            String sql = "INSERT INTO booking (booking_id, user_id, flight_id, class_type, seats_booked) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            //preparing the string with the values from the current user
            statement.setInt(1, booking.booking_id);
            statement.setInt(2, booking.user_id);
            statement.setInt(3, booking.flight_id);
            statement.setString(4, booking.class_type);
            statement.setInt(5, booking.seats_booked);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new booking was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Failed to insert the new booking into the database!");
            e.printStackTrace();
            flight = null;
        }
    }
}
