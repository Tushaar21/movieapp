package sr.unasat.movies.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ratings")
public class Ratings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user_id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movies movie;

    @Column(name = "score")
    private int score;

    @Column(name = "upvotes")
    private int upvotes;

    @Column(name = "downvotes")
    private int downvotes;

    public Ratings() {
    }

    public Ratings(int id, Users user_id, Movies movie, int score, int upvotes, int downvotes) {
        this.id = id;
        this.user_id = user_id;
        this.movie = movie;
        this.score = score;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUser_id() {
        return user_id;
    }

    public void setUser_id(Users user_id) {
        this.user_id = user_id;
    }

    public Movies getMovie() {
        return movie;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    @Override
    public String toString() {
        return "ratings{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", movie=" + movie +
                ", score='" + score + '\'' +
                ", upvotes='" + upvotes + '\'' +
                ", downvotes='" + downvotes + '\'' +
                '}';
    }
}
