package sr.unasat.movies.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "moviegenres")
public class Moviegenres {

    @EmbeddedId
    private MovieGenresId id;

    @ManyToOne
    @JoinColumn(name = "detail_id", insertable = false, updatable = false)
    private MovieDetails movieDetails;

    @ManyToOne
    @JoinColumn(name = "genre_id", insertable = false, updatable = false)
    private Genres genre;

    public Moviegenres() {
    }

    public Moviegenres(MovieGenresId id, MovieDetails movieDetails, Genres genre) {
        this.id = id;
        this.movieDetails = movieDetails;
        this.genre = genre;
    }

    public MovieGenresId getId() {
        return id;
    }

    public void setId(MovieGenresId id) {
        this.id = id;
    }

    public MovieDetails getMovieDetails() {
        return movieDetails;
    }

    public void setMovieDetails(MovieDetails movieDetails) {
        this.movieDetails = movieDetails;
    }

    public Genres getGenre() {
        return genre;
    }

    public void setGenre(Genres genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Moviegenres{" +
                "id=" + id +
                ", movieDetails=" + movieDetails +
                ", genre=" + genre +
                '}';
    }
}
