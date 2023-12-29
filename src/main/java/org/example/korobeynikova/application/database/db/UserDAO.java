package org.example.korobeynikova.application.database.db;

import org.example.korobeynikova.application.entity.UserEntity;
import org.example.korobeynikova.di.annotation.DAO;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@DAO
public class UserDAO {
    private Connection connection = ConnectionDB.getConnection();

    public void addUser(UserEntity userEntity) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO user_event (name, login, password, eventsindex) VALUES(?, ?, ?, ?)");

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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user_event WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addEventToUser(Integer userId, Integer eventId) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE user_event SET name=?, login=?, password=?, eventsindex=? WHERE id=?");

            UserEntity user = getUserById(userId);
            String newUsersEvent = user.getEntityList();
            if (Objects.equals(newUsersEvent, "")) {
                newUsersEvent = String.valueOf(eventId);
            } else {
                newUsersEvent += " " + eventId;
            }
            user.setEntityList(newUsersEvent);


            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEntityList());
            preparedStatement.setInt(5, userId);

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
                userEntity.setEntityList(resultSet.getString("eventsindex"));

                customers.put(index, userEntity);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return customers;
    }

}
