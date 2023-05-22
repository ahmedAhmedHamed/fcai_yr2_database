import java.sql.Connection;

public class FlightManager {
    Connection conn;
    Flights flight = null;

    FlightManager(Connection newConn) {
        conn = newConn;
    }

    public void update_flight() {
        UpdateFlight update_flight = new UpdateFlight(conn);
        update_flight.alter_flight();
    }

    public void add_new_flight() {
        AddFlight add_flight = new AddFlight(conn);
        add_flight.add_flight();
    }

}
