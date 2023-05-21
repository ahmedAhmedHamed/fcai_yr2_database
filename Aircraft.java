/**
 * all variables are not null
 */
public class Aircraft {
    int aircraft_id; //primary key
    String aircraft_name; //len 50
    int total_seats;

    public void print_aircraft_information() {
        System.out.println("=============");

        System.out.println("aircraft_id");
        System.out.println(aircraft_id);
        System.out.println("aircraft_name");
        System.out.println(aircraft_name);
        System.out.println("total_seats");
        System.out.println(total_seats);

        System.out.println("=============");
    }
}
