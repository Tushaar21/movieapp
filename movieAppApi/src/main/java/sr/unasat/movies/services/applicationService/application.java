package sr.unasat.movies.services.applicationService;


import sr.unasat.movies.dao.CommentsDao;
import sr.unasat.movies.dao.MoviesDao;
import sr.unasat.movies.dao.RatingsDao;
import sr.unasat.movies.dao.UsersDao;
import sr.unasat.movies.dto.commentsDto;
import sr.unasat.movies.dto.moviesDto;
import sr.unasat.movies.dto.ratingsDto;
import sr.unasat.movies.dto.usersDto;
import sr.unasat.movies.services.*;

import java.util.List;
import java.util.Scanner;

public class application {
    private final MoviesServiceImpl moviesService = new MoviesServiceImpl(new MoviesDao());
    private final RatingServiceImpl ratingService = new RatingServiceImpl(new RatingsDao());
    private final CommentsServiceImpl commentsService = new CommentsServiceImpl(new CommentsDao());
    private final Scanner scanner = new Scanner(System.in);


    // Method to display all movies (title, beschrijving, release jaar) with numbered option and a go back option.
    public void displayAllMovies(int userId) {
        while (true) {
            List<moviesDto> moviesList = moviesService.findAll();
            System.out.println("\n----- List of Movies -----");
            int index = 1;
            for (moviesDto movie : moviesList) {
                System.out.println( index + ". " +
                                    movie.getTitle() + " | " +
                                    movie.getDescription() + " | " +
                                    movie.getRelease_year());
                index++;
            }
            System.out.println("\n0. Go Back");
            System.out.print("Enter the number of the movie to view details or 0 to go back: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 0) {
                break;
            } else if (choice > 0 && choice <= moviesList.size()) {
                // Pass the movie id to display full details.
                moviesDto selectedMovie = moviesList.get(choice - 1);
                displayMovieDetails(selectedMovie.getMovie_id(), userId);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void displayMovieDetails(int movieId, int userId) {
        while (true) {
            moviesDto movie = moviesService.findById(movieId).orElse(null);
            if (movie == null) {
                System.out.println("Movie not found.");
                return;
            }
            System.out.println("\n----- Movie Details -----");
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Description: " + movie.getDescription());
            System.out.println("Release Year: " + movie.getRelease_year());

            double averageRating = ratingService.getAverageRating(movieId);
            System.out.println("Average Rating: " + String.format("%.1f", averageRating) + " / 10");
            UserServiceImpl userService = new UserServiceImpl(new UsersDao());
            List<commentsDto> commentsList = commentsService.findByMovieId(movieId);
            System.out.println("\nComments:");
            for (commentsDto comment : commentsList) {
                usersDto user = userService.findById(comment.getUser_id()).orElse(null);
                if (user != null) {
                    System.out.println(user.getUsername() + ": " + comment.getContent());
                } else {
                    System.out.println("Unknown User: " + comment.getContent());
                }
            }

            if(userId == 0){
                System.out.println("\nPlease login to rate or comment on this movie.");
                System.out.println("1. login page");
                System.out.println("0. Go Back");
                System.out.print("Enter your choice: ");
                int action = scanner.nextInt();
                scanner.nextLine();
                switch (action) {
                    case 0:
                        return;
                    case 1:
                        ServiceImpl service = new ServiceImpl();
                        service.start();
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }else {
                System.out.println("\n1. Rate this movie");
                System.out.println("2. Add a comment");
                System.out.println("0. Go Back");
                System.out.print("Enter your choice: ");
                int action = scanner.nextInt();
                scanner.nextLine();
                switch (action) {
                    case 0:
                        return;
                    case 1:
                        System.out.print("Enter your score (1-10): ");
                        int score = scanner.nextInt();
                        scanner.nextLine();
                        ratingsDto existingRating = ratingService.findByUserIdAndMovieId(userId, movieId);
                        if (existingRating != null) {
                            existingRating.setScore(score);
                            ratingService.update(existingRating);
                            System.out.println("Your rating has been updated.");
                        } else {
                            ratingsDto newRating = new ratingsDto(0, userId, movieId, score, 0, 0);
                            ratingService.save(newRating);
                            System.out.println("Thank you, your rating is submitted.");
                        }
                        break;
                    case 2:
                        System.out.print("Enter your comment: ");
                        String commentText = scanner.nextLine();
                        commentsDto newComment = new commentsDto(0, userId, movieId, commentText);
                        commentsService.save(newComment);
                        System.out.println("Your comment has been added.");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        }
    }
}
