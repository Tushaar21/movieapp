package sr.unasat.movies.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "genres")
public class Genres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private int genre_id;

    @Column(name = "name")
    private String name;

    public Genres() {
    }

    public Genres(int genre_id, String name) {
        this.genre_id = genre_id;
        this.name = name;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "genresDtp{" +
                "genre_id='" + genre_id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
