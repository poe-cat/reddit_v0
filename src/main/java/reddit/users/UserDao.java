package reddit.users;

import reddit.db_connection.Utils;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class UserDao {

    static Properties properties = Utils.getProperties();

    private Connection connection;
    private final String tableName = "employees";

    public UserDao() {
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
                            + port + "/" + db + "?verifyServerCertificate=false&useSSL=true", user, pass);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new LinkedList<User>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "select * from " + tableName;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                Integer age = resultSet.getInt("age");

                User user = new User(id, name, lastname, age);
                users.add(user);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void createUser(User user) {
        PreparedStatement statement;
        try {
            String query = "insert into " + tableName + " (name, lastname, age) values(?, ?, ?)";
            statement = connection.prepareStatement(query);

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setInt(3, user.getAge());

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String lastname) {
        PreparedStatement statement;
        try {
            String query = "delete from " + tableName + " where lastname=?";
            statement = connection.prepareStatement(query);

            statement.setString(1, lastname);

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        PreparedStatement statement;
        try {
            String query = "update " + tableName + " set name = ?, lastname = ?, age = ? where id=?";
            statement = connection.prepareStatement(query);

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setInt(3, user.getAge());
            statement.setInt(4, user.getId());

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
