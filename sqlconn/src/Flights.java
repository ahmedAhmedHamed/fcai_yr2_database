import java.util.Date;

/**
 * storage class for the flights.
 * all the parameters are NOT NULL
 */
public class Flights {
    int flight_id; //primary key
    Date flight_date;
    String source; //len 10
    String destination; //len 50
    int aircraft_id; //is foreign key from 'aircraft'

    public void print_flight_info() {
        System.out.println("=============");

        System.out.println("flight_id");
        System.out.println(flight_id);
        System.out.println("flight_date");
        System.out.println(flight_date);
        System.out.println("source");
        System.out.println(source);
        System.out.println("destination");
        System.out.println(destination);
        System.out.println("aircraft_id");
        System.out.println(aircraft_id);

        System.out.println("=============");
    }
}
