package sr.unasat.movies.testServiceImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import sr.unasat.movies.config.JPAconfig;
import sr.unasat.movies.dao.*;
import sr.unasat.movies.dto.*;
import sr.unasat.movies.entities.MovieGenresId;
import sr.unasat.movies.services.*;

import java.util.List;
import java.util.Optional;

public class TestServiceImpl {
    public void testUsers(){
        EntityManagerFactory entityManagerFactory = JPAconfig.getEntityMangerFactory();
        EntityManager entityManager = JPAconfig.getEntityManger();

        UsersDao usersDao = new UsersDao();
        UserServiceImpl userService = new UserServiceImpl(usersDao);

        // Fetching all users
        System.out.println("\nFetching all users...");
        List<usersDto> allUsers = userService.findAll();
        for (usersDto user : allUsers) {
            System.out.println(user);
        }

        // add user to user table
        usersDto nemUser = new usersDto( "john", "doe@email.com", "12345678");
        userService.save(nemUser);

        // Fetching all users
        System.out.println("\nFetching all users...");
        List<usersDto> allUsers2 = userService.findAll();
        for (usersDto user : allUsers2) {
            System.out.println(user);
        }

        // update user
        usersDto userToUpdate = new usersDto(1, "john", "john@email.com", "12345678");
        userService.update(userToUpdate);

        // Fetching all users
        System.out.println("\nFetching all users...");
        List<usersDto> allUsers3 = userService.findAll();
        for (usersDto user : allUsers3) {
            System.out.println(user);
        }

        // delete user
        userService.deleteById(1);

        // Fetching all users
        System.out.println("\nFetching all users...");
        List<usersDto> allUsers4 = userService.findAll();
        for (usersDto user : allUsers4) {
            System.out.println(user);
        }

        entityManager.close();
        entityManagerFactory.close();
    }

    public void testMovies(){
        // Movies test
        MoviesDao moviesDao = new MoviesDao();
        MoviesServiceImpl movieService = new MoviesServiceImpl(moviesDao);

        // Create movie
        moviesDto newMovie = new moviesDto(0, "Inception", "A mind-bending thriller", 2010);
        movieService.save(newMovie);

        // Read all movies
        List<moviesDto> allMovies = movieService.findAll();
        for (moviesDto movie : allMovies) {
            System.out.println(movie);
        }

        // Read movie by id
        Optional<moviesDto> movieById = movieService.findById(1);
        movieById.ifPresent(System.out::println);

        // Update movie
        moviesDto movieToUpdate = new moviesDto(1, "Inception Updated", "A mind-bending sci-fi thriller", 2010);
        movieService.update(movieToUpdate);

        // Delete movie
        movieService.deleteById(1);

    }

    public void testMovieDetails(){
        // MovieDetails test
        MovieDetailsDao movieDetailsDao = new MovieDetailsDao();
        MovieDetailsServiceImpl movieDetailsService = new MovieDetailsServiceImpl(movieDetailsDao);

        // Create movie details
        movieDetailsDto newDetails = new movieDetailsDto(0, "1", "185000000", "836800000", "148");
        movieDetailsService.save(newDetails);

        // Read all movie details
        List<movieDetailsDto> allDetails = movieDetailsService.findAll();
        for (movieDetailsDto details : allDetails) {
            System.out.println(details);
        }

        // Read details by id
        Optional<movieDetailsDto> detailsById = movieDetailsService.findById(1);
        detailsById.ifPresent(System.out::println);

        // Update details
        movieDetailsDto detailsToUpdate = new movieDetailsDto(1, "1", "200000000", "850000000", "150");
        movieDetailsService.update(detailsToUpdate);

        // Delete details
        movieDetailsService.deleteById(1);
    }

    public void testGenres(){
        // Genres test
        GenresDao genresDao = new GenresDao();
        GenresServiceImpl genreService = new GenresServiceImpl(genresDao);

        // Create genre
        genresDto newGenre = new genresDto(0, "Science Fiction");
        genreService.save(newGenre);

        // Read all genres
        List<genresDto> allGenres = genreService.findAll();
        for (genresDto genre : allGenres) {
            System.out.println(genre);
        }

        // Read genre by id
        Optional<genresDto> genreById = genreService.findById(1);
        genreById.ifPresent(System.out::println);

        // Update genre
        genresDto genreToUpdate = new genresDto(1, "Sci-Fi");
        genreService.update(genreToUpdate);

        // Delete genre
        genreService.deleteById(1);
    }

    public void testMovieGenres() {
        MovieGenresDao movieGenresDao = new MovieGenresDao();
        MovieGenresServiceImpl movieGenresService = new MovieGenresServiceImpl(movieGenresDao);

        try {
            // Create a new movie-genre association
            moviegenresDto newMovieGenre = new moviegenresDto(
                    1, // detail_id (assuming movie detail with ID 1 exists)
                    1  // genre_id (assuming genre with ID 1 exists)
            );

            // Save the association
            movieGenresService.save(newMovieGenre);
            System.out.println("Movie-Genre association created: " + newMovieGenre);

            // Get all movie-genre associations
            List<moviegenresDto> allMovieGenres = movieGenresService.findAll();
            System.out.println("All movie-genre associations: " + allMovieGenres);

            // Get association by composite id
            MovieGenresId id = new MovieGenresId(1, 1);
            Optional<moviegenresDto> foundMovieGenre = movieGenresService.findById(id);
            foundMovieGenre.ifPresent(mg -> System.out.println("Found movie-genre: " + mg));

            // Update the association (though in practice this might not be common)
            if (foundMovieGenre.isPresent()) {
                moviegenresDto updateMovieGenre = foundMovieGenre.get();
                movieGenresService.update(updateMovieGenre);
                System.out.println("Updated movie-genre association: " + updateMovieGenre);
            }

            // Delete the association
            movieGenresService.deleteById(id);
            System.out.println("Movie-Genre association deleted");

        } catch (Exception e) {
            System.err.println("Error in MovieGenres test: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void testComments(){
        // Comments test
        CommentsDao commentsDao = new CommentsDao();
        CommentsServiceImpl commentService = new CommentsServiceImpl(commentsDao);

        // Create comment
        commentsDto newComment = new commentsDto(0, 1, 1, "Great movie!");
        commentService.save(newComment);

        // Read all comments
        List<commentsDto> allComments = commentService.findAll();
        for (commentsDto comment : allComments) {
            System.out.println(comment);
        }

        // Read comment by id
        Optional<commentsDto> commentById = commentService.findById(1);
        commentById.ifPresent(System.out::println);

        // Update comment
        commentsDto commentToUpdate = new commentsDto(1, 1, 1, "Amazing movie!");
        commentService.update(commentToUpdate);

        // Delete comment
        commentService.deleteById(1);
    }

    public void testRatings(){
        // Ratings test
        RatingsDao ratingsDao = new RatingsDao();
        RatingServiceImpl ratingService = new RatingServiceImpl(ratingsDao);

        // Create rating
        ratingsDto newRating = new ratingsDto(0, 1, 1, 9, 100, 5);
        ratingService.save(newRating);

        // Read all ratings
        List<ratingsDto> allRatings = ratingService.findAll();
        for (ratingsDto rating : allRatings) {
            System.out.println(rating);
        }

        // Read rating by id
        Optional<ratingsDto> ratingById = ratingService.findById(1);
        ratingById.ifPresent(System.out::println);

        // Update rating
        ratingsDto ratingToUpdate = new ratingsDto(1, 1, 1, 10, 150, 7);
        ratingService.update(ratingToUpdate);

        // Delete rating
        ratingService.deleteById(1);
    }

}
