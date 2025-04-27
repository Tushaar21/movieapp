package sr.unasat.movies.services.applicationService;

import sr.unasat.movies.dao.*;
import sr.unasat.movies.dto.*;
import sr.unasat.movies.services.*;

import java.util.List;
import java.util.Scanner;

public class applicationManagement {
    private final GenresServiceImpl genresService = new GenresServiceImpl(new GenresDao());
    private final MovieDetailsServiceImpl movieDetailsService = new MovieDetailsServiceImpl(new MovieDetailsDao());
    private final MoviesServiceImpl moviesService = new MoviesServiceImpl(new MoviesDao());
    private final RatingServiceImpl ratingService = new RatingServiceImpl(new RatingsDao());
    private final UserServiceImpl userService = new UserServiceImpl(new UsersDao());
    private final CommentsServiceImpl commentsService = new CommentsServiceImpl(new CommentsDao());

    public void manageGenres(Scanner scanner) {
        System.out.println("Manage Genres");
        System.out.println("1. View All Genres");
        System.out.println("2. Add Genre");
        System.out.println("3. Update Genre");
        System.out.println("4. Delete Genre");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                List<genresDto> genres = genresService.findAll();
                genres.forEach(System.out::println);
                break;
            case 2:
                System.out.print("Enter genre name: ");
                String name = scanner.nextLine();
                genresService.save(new genresDto( name));
                break;
            case 3:
                System.out.print("Enter genre ID to update: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // consume newline
                System.out.print("Enter new genre name: ");
                String newName = scanner.nextLine();
                genresService.update(new genresDto(id, newName));
                System.out.println("Genre updated successfully!" + id + newName);
                break;
            case 4:
                System.out.print("Enter genre ID to delete: ");
                int deleteId = scanner.nextInt();
                genresService.deleteById(deleteId);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void manageMovies(Scanner scanner) {
        System.out.println("Manage Movies");
        System.out.println("1. View All Movies");
        System.out.println("2. Add Movie");
        System.out.println("3. Update Movie");
        System.out.println("4. Delete Movie");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                List<moviesDto> movies = moviesService.findAll();
                movies.forEach(System.out::println);
                break;
            case 2:
                System.out.print("Enter movie title: ");
                String title = scanner.nextLine();
                System.out.print("Enter movie description: ");
                String description = scanner.nextLine();
                System.out.print("Enter movie release year: ");
                int releaseYear = scanner.nextInt();
                moviesService.save(new moviesDto( title, description, releaseYear));
                break;
            case 3:
                System.out.print("Enter movie ID to update: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // consume newline
                System.out.print("Enter new movie title: ");
                String newTitle = scanner.nextLine();
                System.out.print("Enter new movie description: ");
                String newDescription = scanner.nextLine();
                System.out.print("Enter new movie release year: ");
                int newReleaseYear = scanner.nextInt();
                moviesService.update(new moviesDto(id, newTitle, newDescription, newReleaseYear));
                break;
            case 4:
                System.out.print("Enter movie ID to delete: ");
                int deleteId = scanner.nextInt();
                moviesService.deleteById(deleteId);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void manageMovieDetails(Scanner scanner) {
        System.out.println("Manage Movie Details");
        System.out.println("1. View All Movie Details");
        System.out.println("2. Add Movie Detail");
        System.out.println("3. Update Movie Detail");
        System.out.println("4. Delete Movie Detail");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                List<movieDetailsDto> details = movieDetailsService.findAll();
                details.forEach(System.out::println);
                break;
            case 2:
                System.out.print("Enter movie ID: ");
                String movieId = scanner.nextLine();
                System.out.print("Enter budget: ");
                String budget = scanner.nextLine();
                System.out.print("Enter box office: ");
                String boxOffice = scanner.nextLine();
                System.out.print("Enter duration in minutes: ");
                String duration = scanner.nextLine();
                movieDetailsService.save(new movieDetailsDto( movieId, budget, boxOffice, duration));
                break;
            case 3:
                System.out.print("Enter detail ID to update: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // consume newline
                System.out.print("Enter new movie ID: ");
                String newMovieId = scanner.nextLine();
                System.out.print("Enter new budget: ");
                String newBudget = scanner.nextLine();
                System.out.print("Enter new box office: ");
                String newBoxOffice = scanner.nextLine();
                System.out.print("Enter new duration in minutes: ");
                String newDuration = scanner.nextLine();
                movieDetailsService.update(new movieDetailsDto(id, newMovieId, newBudget, newBoxOffice, newDuration));
                break;
            case 4:
                System.out.print("Enter detail ID to delete: ");
                int deleteId = scanner.nextInt();
                movieDetailsService.deleteById(deleteId);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void manageRatings(Scanner scanner) {
        System.out.println("Manage Ratings");
        System.out.println("1. View All Ratings");
        System.out.println("2. Add Rating");
        System.out.println("3. Update Rating");
        System.out.println("4. Delete Rating");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                List<ratingsDto> ratings = ratingService.findAll();
                ratings.forEach(System.out::println);
                break;
            case 2:
                System.out.print("Enter user ID: ");
                int userId = scanner.nextInt();
                System.out.print("Enter movie ID: ");
                int movieId = scanner.nextInt();
                System.out.print("Enter score: ");
                int score = scanner.nextInt();
                System.out.print("Enter upvotes: ");
                int upvotes = scanner.nextInt();
                System.out.print("Enter downvotes: ");
                int downvotes = scanner.nextInt();
                ratingService.save(new ratingsDto(0, userId, movieId, score, upvotes, downvotes));
                break;
            case 3:
                System.out.print("Enter rating ID to update: ");
                int id = scanner.nextInt();
                System.out.print("Enter new score: ");
                int newScore = scanner.nextInt();
                System.out.print("Enter new upvotes: ");
                int newUpvotes = scanner.nextInt();
                System.out.print("Enter new downvotes: ");
                int newDownvotes = scanner.nextInt();
                ratingService.update(new ratingsDto(id, 0, 0, newScore, newUpvotes, newDownvotes));
                break;
            case 4:
                System.out.print("Enter rating ID to delete: ");
                int deleteId = scanner.nextInt();
                ratingService.deleteById(deleteId);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void manageUsers(Scanner scanner) {
        System.out.println("Manage Users");
        System.out.println("1. View All Users");
        System.out.println("2. Add User");
        System.out.println("3. Update User");
        System.out.println("4. Delete User");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                List<usersDto> users = userService.findAll();
                users.forEach(System.out::println);
                break;
            case 2:
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                userService.save(new usersDto(0, username, email, password));
                break;
            case 3:
                int id = scanner.nextInt();
                scanner.nextLine(); // consume newline
                System.out.print("Enter new username: ");
                String newUsername = scanner.nextLine();
                System.out.print("Enter new email: ");
                String newEmail = scanner.nextLine();
                System.out.print("Enter new password: ");
                String newPassword = scanner.nextLine();
                userService.update(new usersDto(id, newUsername, newEmail, newPassword));
                break;
            case 4:
                System.out.print("Enter user ID to delete: ");
                int deleteId = scanner.nextInt();
                userService.deleteById(deleteId);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void manageComments(Scanner scanner) {
        System.out.println("Manage Comments");
        System.out.println("1. View All Comments");
        System.out.println("2. Add Comment");
        System.out.println("3. Update Comment");
        System.out.println("4. Delete Comment");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                List<commentsDto> comments = commentsService.findAll();
                comments.forEach(System.out::println);
                break;
            case 2:
                System.out.print("Enter user ID: ");
                int userId = scanner.nextInt();
                System.out.print("Enter movie ID: ");
                int movieId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter content: ");
                String content = scanner.nextLine();
                commentsService.save(new commentsDto(0, userId, movieId, content));
                break;
            case 3:
                System.out.print("Enter comment ID to update: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter new content: ");
                String newContent = scanner.nextLine();
                commentsService.update(new commentsDto(id, 0, 0, newContent));
                break;
            case 4:
                System.out.print("Enter comment ID to delete: ");
                int deleteId = scanner.nextInt();
                commentsService.deleteById(deleteId);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

}
