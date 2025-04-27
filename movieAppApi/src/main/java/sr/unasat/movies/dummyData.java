package sr.unasat.movies;

import sr.unasat.movies.dao.*;
import sr.unasat.movies.dto.*;
import sr.unasat.movies.services.*;

public class dummyData {
    public static void insertDummyData() {
        // Initialize services
        UserServiceImpl userService = new UserServiceImpl(new UsersDao());
        MoviesServiceImpl movieService = new MoviesServiceImpl(new MoviesDao());
        MovieDetailsServiceImpl movieDetailsService = new MovieDetailsServiceImpl(new MovieDetailsDao());
        GenresServiceImpl genreService = new GenresServiceImpl(new GenresDao());
        MovieGenresServiceImpl movieGenresService = new MovieGenresServiceImpl(new MovieGenresDao());
        RatingServiceImpl ratingService = new RatingServiceImpl(new RatingsDao());
        CommentsServiceImpl commentService = new CommentsServiceImpl(new CommentsDao());

        try {
            // Users
            usersDto[] users = {
                    new usersDto("john_doe", "john@example.com", "password123"),
                    new usersDto("jane_smith", "jane@example.com", "password456"),
                    new usersDto("mike_wilson", "mike@example.com", "password789"),
                    new usersDto("sarah_brown", "sarah@example.com", "passwordabc"),
                    new usersDto("alex_jones", "alex@example.com", "passworddef")
            };
            for (usersDto user : users) {
                userService.save(user);
            }

            // Movies
            moviesDto[] movies = {
                    new moviesDto(0, "The Matrix", "A computer programmer discovers a dystopian reality", 1999),
                    new moviesDto(0, "Inception", "A thief enters dreams to steal secrets", 2010),
                    new moviesDto(0, "Interstellar", "Space explorers search for a new home for humanity", 2014),
                    new moviesDto(0, "The Dark Knight", "Batman faces his greatest nemesis", 2008),
                    new moviesDto(0, "Pulp Fiction", "Intersecting stories of Los Angeles criminals", 1994)
            };
            for (moviesDto movie : movies) {
                movieService.save(movie);
            }

            // Movie Details
            movieDetailsDto[] movieDetails = {
                    new movieDetailsDto(0, "1", "63000000", "463517383", "136"),
                    new movieDetailsDto(0, "2", "160000000", "836836967", "148"),
                    new movieDetailsDto(0, "3", "165000000", "701729206", "169"),
                    new movieDetailsDto(0, "4", "185000000", "1004558444", "152"),
                    new movieDetailsDto(0, "5", "8000000", "213928762", "154")
            };
            for (movieDetailsDto detail : movieDetails) {
                movieDetailsService.save(detail);
            }

            // Genres
            genresDto[] genres = {
                    new genresDto(0, "Science Fiction"),
                    new genresDto(0, "Action"),
                    new genresDto(0, "Drama"),
                    new genresDto(0, "Crime"),
                    new genresDto(0, "Thriller")
            };
            for (genresDto genre : genres) {
                genreService.save(genre);
            }

            // Movie Genres
            moviegenresDto[] movieGenres = {
                    new moviegenresDto(1, 1), // Matrix - Sci-Fi
                    new moviegenresDto(2, 2), // Inception - Action
                    new moviegenresDto(3, 3), // Interstellar - Drama
                    new moviegenresDto(4, 4), // Dark Knight - Crime
                    new moviegenresDto(5, 5)  // Pulp Fiction - Thriller
            };
            for (moviegenresDto movieGenre : movieGenres) {
                movieGenresService.save(movieGenre);
            }

            // Ratings
            ratingsDto[] ratings = {
                    new ratingsDto(0, 1, 1, 9, 0, 0),
                    new ratingsDto(0, 2, 2, 8, 0, 0),
                    new ratingsDto(0, 3, 3, 9, 0, 0),
                    new ratingsDto(0, 4, 4, 10, 0, 0),
                    new ratingsDto(0, 5, 5, 8, 0, 0),
                    new ratingsDto(0, 5, 1, 4, 0, 0),
                    new ratingsDto(0, 5, 2, 5, 0, 0),
                    new ratingsDto(0, 4, 3, 9, 0, 0),
                    new ratingsDto(0, 4, 1, 8, 0, 0),
                    new ratingsDto(0, 1, 2, 5, 0, 0),
                    new ratingsDto(0, 3, 1, 7, 0, 0),
                    new ratingsDto(0, 3, 2, 7, 0, 0),
                    new ratingsDto(0, 2, 1, 9, 0, 0)
            };
            for (ratingsDto rating : ratings) {
                ratingService.save(rating);
            }

            // Comments
            commentsDto[] comments = {
                    new commentsDto(0, 1, 1, "Mind-blowing special effects and story!"),
                    new commentsDto(0, 2, 2, "Complex plot but worth watching multiple times."),
                    new commentsDto(0, 3, 3, "Beautiful visuals and emotional story."),
                    new commentsDto(0, 4, 4, "Heath Ledger's performance is legendary."),
                    new commentsDto(0, 5, 5, "A masterpiece of nonlinear storytelling."),
                    new commentsDto(0, 1, 1, "Great movie!"),
                    new commentsDto(0, 2, 1, "I loved the storyline."),
                    new commentsDto(0, 3, 2, "Not my type of movie."),
                    new commentsDto(0, 4, 2, "Amazing visuals!"),
                    new commentsDto(0, 5, 3, "Could have been better."),
                    new commentsDto(0, 1, 3, "Enjoyed every moment."),
                    new commentsDto(0, 2, 4, "A must-watch!"),
                    new commentsDto(0, 3, 4, "Boring and slow."),
                    new commentsDto(0, 4, 5, "Fantastic acting."),
                    new commentsDto(0, 5, 5, "Highly recommended.")
            };
            for (commentsDto comment : comments) {
                commentService.save(comment);
            }

            System.out.println("Dummy data inserted successfully!");

        } catch (Exception e) {
            System.err.println("Error inserting dummy data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
