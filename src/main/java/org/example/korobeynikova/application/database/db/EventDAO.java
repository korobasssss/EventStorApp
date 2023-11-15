package org.example.korobeynikova.application.database.db;

import org.example.korobeynikova.application.entity.EventEntity;
import org.example.korobeynikova.application.entity.enums.EventType;
import org.example.korobeynikova.di.annotation.DAO;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DAO
public class EventDAO {

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

    public void setEvent(EventEntity event) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO event (type, title, data, description) VALUES(?::event_type, ?, ?, ?)");

            preparedStatement.setString(1, event.getType().toString());
            preparedStatement.setString(2, event.getTitle());
            preparedStatement.setDate(3, (Date) event.getData().parse(event.getData().toString()));
            preparedStatement.setString(4, event.getDescription());

            preparedStatement.executeUpdate();
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
    }

    public EventEntity getEventById(Integer id){
        EventEntity eventEntity = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM event WHERE id=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            eventEntity = new EventEntity();

            eventEntity.setType(EventType.valueOf(resultSet.getString("type")));
            eventEntity.setTitle(resultSet.getString("title"));
            eventEntity.setData(new SimpleDateFormat(resultSet.getString("date")));
            eventEntity.setDescription(resultSet.getString("description"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return eventEntity;
    }

    public Integer size() {
        Integer size = 0;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT COUNT(*) FROM event");

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            size = resultSet.getInt("count");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return size;
    }

    public void updateById(Integer id, EventEntity newEvent){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE car SET brand=?, model=?, type=?::car_type, sold=? WHERE id=?");

            preparedStatement.setString(1, newEvent.getType().toString());
            preparedStatement.setString(2, newEvent.getTitle());
            preparedStatement.setDate(3, (Date) newEvent.getData().parse(newEvent.getData().toString()));
            preparedStatement.setString(4, newEvent.getDescription());
            preparedStatement.setInt(5, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Integer, EventEntity> getAll() {
        Map<Integer, EventEntity> events = new HashMap<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM event";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                EventEntity eventEntity = new EventEntity();
                Integer index = 0;

                index = resultSet.getInt("id");
                eventEntity.setType(EventType.valueOf(resultSet.getString("type")));
                eventEntity.setTitle(resultSet.getString("title"));
                eventEntity.setData(new SimpleDateFormat(resultSet.getString("date")));
                eventEntity.setDescription(resultSet.getString("description"));

                events.put(index, eventEntity);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return events;
    }

    public void deleteEvent(Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM event WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
