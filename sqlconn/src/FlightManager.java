import java.sql.Connection;

public class FlightManager {
    Connection conn;
    Flights flight = null;

    FlightManager(Connection newConn) {
        conn = newConn;
    }


}
