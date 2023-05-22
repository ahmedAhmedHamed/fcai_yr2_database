import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * this class can only be accessed by an admin
 */
public class AircraftManager {
    Connection conn;
    User current_user;
    Aircraft Aircraft = null;
    AddAircraft add_air_craft = null;

    AircraftManager(Connection newConn) {
        conn = newConn;
    }

    public void add_aircraft() {
        add_air_craft = new AddAircraft(conn);
        add_air_craft.add_aircraft();
        Aircraft = add_air_craft.Aircraft;
    }

    public void update_aircraft() {
        UpdateAircraft update_aircraft = new UpdateAircraft(conn);
        update_aircraft.alter_aircraft();
    }

}
