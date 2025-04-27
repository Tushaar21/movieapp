package sr.unasat.movies.dto;

public class genresDto {
    private int genre_id;
    private String name;

    public genresDto(int genre_id, String name) {
        this.genre_id = genre_id;
        this.name = name;
    }

    public genresDto(String name) {
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
