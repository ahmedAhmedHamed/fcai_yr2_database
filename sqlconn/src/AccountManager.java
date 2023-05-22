import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * class responsible for handling everything related to user accounts
 */
public class AccountManager {
    Connection conn;
    User current_user;
    Register Register = null;

    /**
     * constructs account manager
     * @param newConn : mysql connection
     */
    AccountManager(Connection newConn) {
        conn = newConn;
    }

    public void register() {
        Register = new Register(conn);
        Register.register();
        current_user = Register.current_user;
    }

    public void login() {
        Register = new Register(conn);
        Register.register();
        current_user = Register.current_user;
    }


}
