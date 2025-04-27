package sr.unasat.movies.dto;

public class movieDetailsDto {
    private int detail_id;
    private String movie_id;
    private String budget;
    private String box_office;
    private String duration_minutes;

    public movieDetailsDto() {
    }

    public movieDetailsDto(int detail_id, String movie_id, String budget, String box_office, String duration_minutes) {
        this.detail_id = detail_id;
        this.movie_id = movie_id;
        this.budget = budget;
        this.box_office = box_office;
        this.duration_minutes = duration_minutes;
    }

    public movieDetailsDto(String movie_id, String budget, String box_office, String duration_minutes) {
        this.movie_id = movie_id;
        this.budget = budget;
        this.box_office = box_office;
        this.duration_minutes = duration_minutes;
    }

    public int getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getBox_office() {
        return box_office;
    }

    public void setBox_office(String box_office) {
        this.box_office = box_office;
    }

    public String getDuration_minutes() {
        return duration_minutes;
    }

    public void setDuration_minutes(String duration_minutes) {
        this.duration_minutes = duration_minutes;
    }

    @Override
    public String toString() {
        return "movieDetailsDto{" +
                "detail_id=" + detail_id +
                ", movie_id='" + movie_id + '\'' +
                ", budget='" + budget + '\'' +
                ", box_office='" + box_office + '\'' +
                ", duration_minutes='" + duration_minutes + '\'' +
                '}';
    }
}
