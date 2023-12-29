package org.example.korobeynikova.application.database.db;

import org.example.korobeynikova.application.entity.EventEntity;
import org.example.korobeynikova.application.entity.enums.EventType;
import org.example.korobeynikova.di.annotation.DAO;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@DAO
public class EventDAO {
    private Connection connection = ConnectionDB.getConnection();


    public void addEvent(EventEntity event) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO event (type, title, data) VALUES(?::event_type, ?, ?)");

            String dateString = event.getData();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = dateFormat.parse(dateString);
            Date sqlDate = new Date(utilDate.getTime());

            preparedStatement.setString(1, event.getType().toString());
            preparedStatement.setString(2, event.getTitle());
            preparedStatement.setDate(3, sqlDate);

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
            eventEntity.setData(resultSet.getString("data"));
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

    public void changeEventById(Integer id, EventEntity newEvent){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE event SET type=?::event_type, title=?, data=? WHERE id=?");

            String dateString = newEvent.getData();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = dateFormat.parse(dateString);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());


            preparedStatement.setString(1, newEvent.getType().toString());
            preparedStatement.setString(2, newEvent.getTitle());
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Integer, EventEntity> getAllEvents() {
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
                eventEntity.setData(resultSet.getString("data"));

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
