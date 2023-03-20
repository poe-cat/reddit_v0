package reddit.posts;

import reddit.db_connection.Utils;
import reddit.users.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class PostDao {

    static Properties properties = Utils.getProperties();

    private Connection connection;
    private final String tableName = "post";

    public PostDao() {
        init();
    }

    public void init() {

        try {
            String driver = properties.getProperty("db.driver");
            String port = properties.getProperty("db.port");
            String db = properties.getProperty("db.db");
            String user = properties.getProperty("db.user");
            String pass = properties.getProperty("db.pass");
            String server = properties.getProperty("db.server");

            Class.forName(driver);
            connection = DriverManager.getConnection("jdbc:mysql://" + server + ":"
                            + port + "/" + db + "?verifyServerCertificate=false&useSSL=true&createDatabaseIfNotExist=true",
                    user, pass);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public List<Post> getAllPosts() {
        List<Post> posts = new LinkedList<Post>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "select * from " + tableName;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Integer postID = resultSet.getInt("post_id");
                String postContent = resultSet.getString("post_content");
                //TODO: change date format, it's terrible
                DateTimeFormatter postDate = DateTimeFormatter.ofPattern(resultSet.getString("post_date"));
                Integer postRate = resultSet.getInt("post_rate");
                Integer userID = resultSet.getInt("user_id");

                Post post = new Post(postID, postContent, postDate, postRate, userID);
                posts.add(post);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }
}
