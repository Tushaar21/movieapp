package sr.unasat.movies.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "movies")
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int movie_id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year", columnDefinition = "YEAR")
    private int release_year;

    @OneToOne(mappedBy = "movie", cascade = CascadeType.ALL)
    private MovieDetails movieDetails;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Ratings> ratings;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Comments> comments;

    public Movies() {
    }

    public Movies(int movie_id, String title, String description, int release_year, MovieDetails movieDetails, List<Ratings> ratings, List<Comments> comments) {
        this.movie_id = movie_id;
        this.title = title;
        this.description = description;
        this.release_year = release_year;
        this.movieDetails = movieDetails;
        this.ratings = ratings;
        this.comments = comments;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public MovieDetails getMovieDetails() {
        return movieDetails;
    }

    public void setMovieDetails(MovieDetails movieDetails) {
        this.movieDetails = movieDetails;
    }

    public List<Ratings> getRatings() {
        return ratings;
    }

    public void setRatings(List<Ratings> ratings) {
        this.ratings = ratings;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "moviesDto{" +
                "movie_id=" + movie_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", release_year='" + release_year + '\'' +
                '}';
    }
}
