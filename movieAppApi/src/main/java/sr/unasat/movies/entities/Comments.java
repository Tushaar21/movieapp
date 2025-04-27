package sr.unasat.movies.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int comment_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movies movie;

    @Column(name = "content")
    private String content;

    public Comments() {
    }

    public Comments(int comment_id, Users user, Movies movie, String content) {
        this.comment_id = comment_id;
        this.user = user;
        this.movie = movie;
        this.content = content;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Movies getMovie() {
        return movie;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "comments{" +
                "comment_id=" + comment_id +
                ", user=" + user +
                ", movie=" + movie +
                ", content='" + content + '\'' +
                '}';
    }
}
