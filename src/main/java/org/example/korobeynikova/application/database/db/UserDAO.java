package org.example.korobeynikova.application.database.db;

import org.example.korobeynikova.application.entity.UserEntity;
import org.example.korobeynikova.di.annotation.Autowired;
import org.example.korobeynikova.di.annotation.DAO;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
@DAO
public class UserDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/maindatabase";
    private static final String USERNAME = "postgres"; //idc
    private static final String PASSWORD = "postgres";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setUser(UserEntity userEntity) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO user_event (name, login, password, entityList) VALUES(?, ?, ?, ?)");

            preparedStatement.setString(1, userEntity.getName());
            preparedStatement.setString(2, userEntity.getLogin());
            preparedStatement.setString(3, userEntity.getPassword());
            preparedStatement.setString(4, userEntity.getEntityList());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public UserEntity getUserById(Integer id){
        UserEntity userEntity = null;

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM user_event WHERE id=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            userEntity = new UserEntity();

            userEntity.setName(resultSet.getString("name"));
            userEntity.setLogin(resultSet.getString("login"));
            userEntity.setPassword(resultSet.getString("password"));
            userEntity.setEntityList(resultSet.getString("eventsIndex"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userEntity;
    }

    public Integer size() {
        Integer size = 0;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT COUNT(*) FROM user_event");

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            size = resultSet.getInt("count");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return size;
    }

    public void deleteUserById(Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users_event WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateUser(Integer id, UserEntity newCustomer) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE customer SET name=?, surname=?, age=?, gender=? WHERE id=?");

            preparedStatement.setString(1, newCustomer.getName());
            preparedStatement.setString(2, newCustomer.getLogin());
            preparedStatement.setString(3, newCustomer.getPassword());
            preparedStatement.setString(4, newCustomer.getEntityList());
            preparedStatement.setInt(5, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Map<Integer, UserEntity> getAllUsers() {
        Map<Integer, UserEntity> customers = new HashMap<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM user_event";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                UserEntity userEntity = new UserEntity();
                Integer index = 0;

                index = resultSet.getInt("id");
                userEntity.setName(resultSet.getString("name"));
                userEntity.setLogin(resultSet.getString("login"));
                userEntity.setPassword(resultSet.getString("password"));
                userEntity.setEntityList(resultSet.getString("eventsIndex"));

                customers.put(index, userEntity);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return customers;
    }

}
