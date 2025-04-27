package sr.unasat.movies.dto;

public class moviegenresDto {
    private int detail_id;
    private int genre_id;

    public moviegenresDto() {
    }

    public moviegenresDto(int detail_id, int genre_id) {
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

    @Override
    public String toString() {
        return "moviegenresDto{" +
                "detail_id=" + detail_id +
                ", genre_id=" + genre_id +
                '}';
    }
}
