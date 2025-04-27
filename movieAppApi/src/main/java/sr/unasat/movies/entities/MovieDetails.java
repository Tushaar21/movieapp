package sr.unasat.movies.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "moviedetails")
public class MovieDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private int detail_id;

    @OneToOne
    @JoinColumn(name = "movie_id")
    private Movies movie;

    @Column(name = "budget")
    private int budget;

    @Column(name = "box_office")
    private int box_office;

    @Column(name = "duration_minutes")
    private String duration_minutes;

    @ManyToMany
    @JoinTable(
            name = "movieGenres",
            joinColumns = @JoinColumn(name = "detail_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genres> genres;

    public MovieDetails() {
    }

    public MovieDetails(int detail_id, Movies movie, int budget, int box_office, String duration_minutes, List<Genres> genres) {
        this.detail_id = detail_id;
        this.movie = movie;
        this.budget = budget;
        this.box_office = box_office;
        this.duration_minutes = duration_minutes;
        this.genres = genres;
    }

    public int getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
    }

    public Movies getMovie() {
        return movie;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getBox_office() {
        return box_office;
    }

    public void setBox_office(int box_office) {
        this.box_office = box_office;
    }

    public String getDuration_minutes() {
        return duration_minutes;
    }

    public void setDuration_minutes(String duration_minutes) {
        this.duration_minutes = duration_minutes;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "movieDetails{" +
                "detail_id=" + detail_id +
                ", movie=" + movie +
                ", budget='" + budget + '\'' +
                ", box_office='" + box_office + '\'' +
                ", duration_minutes='" + duration_minutes + '\'' +
                '}';
    }
}
