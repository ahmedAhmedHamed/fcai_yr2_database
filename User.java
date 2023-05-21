/**
 * all variables are not null
 */
public class User {
    int user_id; //primary key
    String username; //len 50
    String password; //len 10
    String user_type; //len 10

    public void print_user_information() {
        System.out.println("=============");

        System.out.println("user_id");
        System.out.println(user_id);
        System.out.println("username");
        System.out.println(username);
        System.out.println("password");
        System.out.println(password);
        System.out.println("user_type");
        System.out.println(user_type);

        System.out.println("=============");
    }
}
