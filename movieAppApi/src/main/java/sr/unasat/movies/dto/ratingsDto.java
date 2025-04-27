package sr.unasat.movies.dto;

public class ratingsDto {
    private int id;
    private int user_id;
    private int movie_id;
    private int score;
    private int upvotes;
    private int downvotes;

    public ratingsDto() {
    }

    public ratingsDto(int id, int user_id, int movie_id, int score, int upvotes, int downvotes) {
        this.id = id;
        this.user_id = user_id;
        this.movie_id = movie_id;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
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
        return "ratingsDto{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", movie_id='" + movie_id + '\'' +
                ", score='" + score + '\'' +
                ", upvotes='" + upvotes + '\'' +
                ", downvotes='" + downvotes + '\'' +
                '}';
    }
}
