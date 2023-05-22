import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
