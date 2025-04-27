package sr.unasat.movies.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MovieGenresId implements Serializable {
    private int detail_id;
    private int genre_id;

    public MovieGenresId() {
    }

    public MovieGenresId(int detail_id, int genre_id) {
        this.detail_id = detail_id;
        this.genre_id = genre_id;
    }

    public int getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    //equals and hashcode methods

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // If both objects are the same reference
        if (obj == null || getClass() != obj.getClass()) return false; // Check if obj is of the same class

        MovieGenresId that = (MovieGenresId) obj;

        return detail_id == that.detail_id && genre_id == that.genre_id;
    }

    @Override
    public int hashCode() {
        return detail_id + genre_id;
    }
}
