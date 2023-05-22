/**
 * all variables are not null
 */
public class Booking {
    int booking_id; //primary key
    int user_id; //foreign key from 'users'
    int flight_id; //foreign key from 'flights'
    String class_type; //len 10
    int seats_booked;

    public void print_booking_information() {
        System.out.println("=============");

        System.out.println("booking_id");
        System.out.println(booking_id);
        System.out.println("user_id");
        System.out.println(user_id);
        System.out.println("flight_id");
        System.out.println(flight_id);
        System.out.println("class_type");
        System.out.println(class_type);
        System.out.println("seats_booked");
        System.out.println(seats_booked);

        System.out.println("=============");
    }
}
