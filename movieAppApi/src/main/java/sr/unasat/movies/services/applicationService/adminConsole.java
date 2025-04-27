package sr.unasat.movies.services.applicationService;

import java.util.Scanner;

public class adminConsole {
    public void admin_Console() {
        Scanner scanner = new Scanner(System.in);
        applicationManagement app = new applicationManagement();
        boolean exit = false;
        while (!exit) {
            System.out.println("Admin Console");
            System.out.println("1. Manage Genres");
            System.out.println("2. Manage Movies");
            System.out.println("3. Manage Movie Details");
            System.out.println("4. Manage Ratings");
            System.out.println("5. Manage Users");
            System.out.println("6. Manage Comments");
            System.out.println("7. Back");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    do {
                        app.manageGenres(scanner);
                        System.out.print("Enter 'b' to go back or any other key to continue: ");
                    } while (!scanner.nextLine().equalsIgnoreCase("b"));
                    break;
                case 2:
                    do {
                        app.manageMovies(scanner);
                        System.out.print("Enter 'b' to go back or any other key to continue: ");
                    } while (!scanner.nextLine().equalsIgnoreCase("b"));
                    break;
                case 3:
                    do {
                        app.manageMovieDetails(scanner);
                        System.out.print("Enter 'b' to go back or any other key to continue: ");
                    } while (!scanner.nextLine().equalsIgnoreCase("b"));
                    break;
                case 4:
                    do {
                        app.manageRatings(scanner);
                        System.out.print("Enter 'b' to go back or any other key to continue: ");
                    } while (!scanner.nextLine().equalsIgnoreCase("b"));
                    break;
                case 5:
                    do {
                        app.manageUsers(scanner);
                        System.out.print("Enter 'b' to go back or any other key to continue: ");
                    } while (!scanner.nextLine().equalsIgnoreCase("b"));
                    break;
                case 6:
                    do {
                        app.manageComments(scanner);
                        System.out.print("Enter 'b' to go back or any other key to continue: ");
                    } while (!scanner.nextLine().equalsIgnoreCase("b"));
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
