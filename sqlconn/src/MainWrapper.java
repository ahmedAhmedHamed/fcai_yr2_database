import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * TODO make Performing operations on flights: booking, cancelling, changing flight class.)
 * main wrapper class responsible for calling functions from other classes
 */
public class MainWrapper implements ActionListener {
    Connection conn;
    User current_user = null;
    AccountManager AccountManager = null;
    AircraftManager AircraftManager = null;
    //GUI
    JFrame frame;
    JButton login_button;
    JPanel panel;
    JButton register_button;

    /**
     * connects to database and enters the input loop
     */
    MainWrapper() {
        connect_to_database();
        AccountManager = new AccountManager(conn);
        AircraftManager = new AircraftManager(conn);
        setupGUI();
    }

    private void setupGUI() {
        frame = new JFrame("My First GUI");
        login_button = new JButton("Login");
        register_button = new JButton("Register");

        login_button.addActionListener(this);//e.getActionCommand() == Login (String)
        register_button.addActionListener(this);//e.getActionCommand() == Register (String)

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(login_button);
        panel.add(register_button);

        frame.add(panel, BorderLayout.CENTER);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       handleSignal(e.getActionCommand());
    }

    public void handleSignal(String signal) {

        if (signal.equals("Login"))
        {
            login();
        }
    }

    /**
     * logs the user in using account manager class
     */
    private void login() {
        panel.removeAll();
//        AccountManager.login();
//        current_user = AccountManager.current_user;
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
