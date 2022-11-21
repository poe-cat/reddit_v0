package reddit.posts;

import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Setter
public class Post {

    private Integer postID;
    private String postContent;
    private DateTimeFormatter postDate;
    private Integer postRate;

    private Integer userID;

    public Post(Integer postID, String postContent, DateTimeFormatter postDate, Integer postRate, Integer userID) {
        this.postID = postID;
        this.postContent = postContent;
        this.postDate = postDate;
        this.postRate = postRate;
        this.userID = userID;
    }

    public Post(String postContent, DateTimeFormatter postDate, Integer postRate, Integer userID) {
        this.postContent = postContent;
        this.postDate = postDate;
        this.postRate = postRate;
        this.userID = userID;
    }

    public Integer getPostID() {
        return postID;
    }

    public String getPostContent() {
        return postContent;
    }

    public DateTimeFormatter getPostDate() {
        return postDate;
    }

    public Integer getPostRate() {
        return postRate;
    }

    public Integer getUserID() {
        return userID;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postID=" + postID +
                ", postContent='" + postContent + '\'' +
                ", postDate=" + postDate +
                ", postRate=" + postRate +
                ", userID=" + userID +
                '}';
    }
}
