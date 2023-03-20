package reddit;

import console_style.ConsoleColors;
import reddit.users.User;
import reddit.users.UserDao;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static UserDao userDao = new UserDao();

    public static void createUser() {

        String name;
        String lastname;
        Integer age;

        System.out.println("Type a name: ");
        name = scanner.next();

        System.out.println("Type a lastname: ");
        lastname = scanner.next();

        System.out.println("Type your age: ");
        age = scanner.nextInt();

        User user = new User(name, lastname, age);
        userDao.createUser(user);
        System.out.println("Created user: " + user.toString());
    }

    public static void deleteUser() {
        String lastname;

        System.out.println("Type a lastname: ");
        lastname = scanner.next();

        userDao.deleteUser(lastname);
        System.out.println("Deleted usera: " + lastname);
    }

    public static void calculate() {
        Integer x, y;

        System.out.println("Type first number: ");
        x = scanner.nextInt();

        System.out.println("Type second number: ");
        y = scanner.nextInt();

        System.out.println("Sum: " + Calculator.add(x, y));
        System.out.println("Subtract: " + Calculator.subtract(x, y));
        System.out.println("Multiply: " + Calculator.multiply(x, y));
        System.out.println("Divide: " + Calculator.divide(x, y));
    }

    public static void updateUser() {
        String lastname, name;
        Integer id, age;

        System.out.println("Type user id to update: ");
        id = scanner.nextInt();

        System.out.println("Type a new name: ");
        name = scanner.next();

        System.out.println("Type a new lastname: ");
        lastname = scanner.next();


        System.out.println("Type a new age: ");
        age = scanner.nextInt();

        User user = new User(id, name,lastname , age);
        userDao.updateUser(user);
        System.out.println("Updated user: " + user);
    }

    public static void main(String[] args) throws IOException {

        char choice, ignore;

        for(;;) {
            do {
                System.out.println(ConsoleColors.YELLOW_BRIGHT + "\nWhat do you want to do?\n" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.PURPLE_BRIGHT + "Create new user - tap 1");
                System.out.println("Delete user - tap 2");
                System.out.println("Display all users - tap 3");
                System.out.println("Update user - tap 4");
                System.out.println("Use calculator - tap 5");
                System.out.println("Exit - tap x\n" + ConsoleColors.RESET);

                choice = (char) System.in.read();

                do {
                    ignore = (char) System.in.read();
                } while (ignore != '\n');
            } while (choice < '1' | choice > '5' & choice != 'x');

            if (choice == 'x') break;

            System.out.println("\n");

            switch (choice) {
                case '1' -> createUser();
                case '2' -> deleteUser();
                case '3' -> {
                    userDao = new UserDao();
                    List<User> allUsers = userDao.getAllUsers();
                    for(User user : allUsers) {
                        System.out.println(ConsoleColors.BLUE_BRIGHT + user.getId() + ". " + user.getFirstName() + " "
                                + user.getLastName() + ", " + user.getAge());
                    }
                }
                case '4' -> {
                    userDao = new UserDao();
                    System.out.println(userDao.getAllUsers());
                    updateUser();
                }
                case '5' -> calculate();
            }
        }
    }
}
