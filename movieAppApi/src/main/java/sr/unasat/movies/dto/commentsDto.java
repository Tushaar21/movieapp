package sr.unasat.movies.dto;

public class commentsDto {
    private int comment_id;
    private int user_id;
    private int movie_id;
    private String content;

    public commentsDto() {
    }

    public commentsDto(int comment_id, int user_id, int movie_id, String content) {
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.movie_id = movie_id;
        this.content = content;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "commentsDto{" +
                "comment_id=" + comment_id +
                ", user_id='" + user_id + '\'' +
                ", movie_id='" + movie_id + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
