package sr.unasat.movies.dto;

public class moviesDto {
    private int movie_id;
    private String title;
    private String description;
    private int release_year;

    public moviesDto() {
    }

    public moviesDto(int movie_id, String title, String description, int release_year) {
        this.movie_id = movie_id;
        this.title = title;
        this.description = description;
        this.release_year = release_year;
    }

    public moviesDto(String title, String description, int release_year) {
        this.title = title;
        this.description = description;
        this.release_year = release_year;
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
