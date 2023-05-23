import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * TODO make Performing operations on flights: booking, cancelling, changing flight class.)
 * main wrapper class responsible for calling functions from other classes
 */
public class MainWrapper {
    Connection conn;
    User current_user = null;
    AccountManager AccountManager = null;
    AircraftManager AircraftManager = null;
    FlightManager flight_manager = null;

    /**
     * connects to database and enters the input loop
     */
    MainWrapper() {
        connect_to_database();
        AccountManager = new AccountManager(conn);
        AircraftManager = new AircraftManager(conn);

        while (true) {
            handleSignal();
        }
    }

    public void handleSignal() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("insert choice");
        System.out.println("0 for registration");
        System.out.println("1 for login");
        System.out.println("2 for showing which user is shown");
        System.out.println("3 to enter a new aircraft");
        System.out.println("4 for add aircraft");
        System.out.println("5 for show flights");
        System.out.println("6 for add flights");
        System.out.println("7 for update flights");

        System.out.println("-1 TO EXIT");

        int choice = sc.nextInt();

        if (choice == 0) {
            register();
        }
        else if (choice == 1) {
            login();
        }
        else if (choice == 2) {
            if (current_user == null) {
                System.out.println("no user is logged in at the moment");
                return;
            }
            current_user.print_user_information();
        } else if (choice == 3) {
            if (current_user == null || !current_user.user_type.equals("admin")) {
                System.out.println("insufficient permissions");
                return;
            }
            AircraftManager.add_aircraft();
        } else if (choice == 4) {
            if (current_user == null || !current_user.user_type.equals("admin")) {
                System.out.println("insufficient permissions");
                return;
            }
            AircraftManager.update_aircraft();
        } else if (choice == 5) {
            ViewManager VM = new ViewManager(conn);
            VM.showFlights();
        } else if (choice == 6) {
            if (current_user == null || !current_user.user_type.equals("admin")) {
                System.out.println("insufficient permissions");
                return;
            }
            flight_manager = new FlightManager(conn);
            flight_manager.add_new_flight();
        } else  if (choice == 7) {
            if (current_user == null || !current_user.user_type.equals("admin")) {
                System.out.println("insufficient permissions");
                return;
            }
            flight_manager = new FlightManager(conn);
            flight_manager.update_flight();
        }else if (choice == -1) {
            System.exit(0);
        }
        else {
            System.out.println("undefined choice");
        }
    }

    /**
     * logs the user in using account manager class
     */
    private void login() {
        AccountManager.login();
        current_user = AccountManager.current_user;
    }

    /**
     * registers a new user
     */
    private void register() {
        AccountManager.register();
        current_user = AccountManager.current_user;
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
            System.exit(0);
        }
    }
}
