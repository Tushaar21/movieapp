package sr.unasat.movies.services.applicationService;

import sr.unasat.movies.dao.UsersDao;
import sr.unasat.movies.dto.usersDto;
import sr.unasat.movies.services.UserServiceImpl;

import java.util.Scanner;

public class REG_Verification {
    private final UserServiceImpl userService = new UserServiceImpl(new UsersDao());

    public void registerUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.println("Enter email: ");
        String email = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        usersDto newUser = new usersDto(username, email, password);
        userService.save(newUser);

        System.out.println("User registered successfully!");
        System.out.println("Please login to continue.");
    }


    public void loginUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        UsersDao usersDao = new UsersDao();
        UserServiceImpl userService = new UserServiceImpl(usersDao);

        boolean loginSuccess = false;
        for (usersDto user : userService.findAll()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login successful! Welcome, " + user.getUsername());
                loginSuccess = true;
                if (user.getId()== 1){
                    adminConsole admin = new adminConsole();
                    admin.admin_Console();
                }else {
                    application app = new application();
                    app.displayAllMovies(user.getId());
                }
            }
        }
        if (!loginSuccess) {
            System.out.println("Login failed! Invalid username or password.");
        }
    }
}
