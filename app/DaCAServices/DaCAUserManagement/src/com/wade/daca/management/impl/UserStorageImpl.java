package com.wade.daca.management.impl;

import com.wade.daca.management.api.UserStorage;
import com.wade.daca.management.dataobjects.User;
import com.wade.daca.management.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("userStorage")
public class UserStorageImpl implements UserStorage {

    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = :userId";
    private static final String INSERT_USER = "INSERT INTO users VALUES(:userId, :userName, :userPassword)";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = :userId";
    private static final String UPDATE_USER = "UPDATE users" + "\n" +
                                              "SET name = :userName, password = :userPassword" + "\n" +
                                              "WHERE id = :userId";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public User getUserById(String id) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("userId", id);
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(GET_USER_BY_ID, params, new UserMapper());
        } catch (DataAccessException ex) {
            System.out.println(ex.toString());
        }

        return user;
    }

    @Override
    public boolean addUser(User user)
    {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", user.getId())
                .addValue("userName", user.getName())
                .addValue("userPassword",user.getPassword());
        boolean added = false;

        try {
            added = jdbcTemplate.update(INSERT_USER, params) == 1;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return added;
    }

    @Override
    public boolean removeUser(User user) {
        return removeUser(user.getId());
    }

    @Override
    public boolean removeUser(String id) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("userId", id);
        boolean removed = false;

        try {
            removed = jdbcTemplate.update(DELETE_USER, params) == 1;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return removed;
    }

    @Override
    public boolean updateUser(String id, User user) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", id)
                .addValue("userName", user.getName())
                .addValue("userPassword",user.getPassword());
        boolean updated = false;

        try {
            updated = jdbcTemplate.update(UPDATE_USER, params) == 1;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return updated;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config/Spring.xml");
        UserStorage userStorage = (UserStorage) applicationContext.getBean("userStorage");

        User userToAdd = User.builder()
                .id("test")
                .name("Test")
                .password("test")
                .build();

        User userUpdated = User.builder()
                .id("test")
                .name("TestUpdated")
                .password("testUpdated")
                .build();

        boolean added = userStorage.addUser(userToAdd);
        System.out.println("added: " + added);

        // test error case
        added = userStorage.addUser(userToAdd);
        System.out.println("added: " + added);

        User user = userStorage.getUserById("test");
        System.out.println(user.getName());

        // test error case
        user = userStorage.getUserById("test1");
        System.out.println(user != null ? user.getName() : "no user found");

        boolean updated = userStorage.updateUser("test", userUpdated);
        System.out.println("updated: " + updated);

        // test error case
        updated = userStorage.updateUser("test", userUpdated);
        System.out.println("updated: " + updated);

        user = userStorage.getUserById("test");
        System.out.println(user.getName());

        boolean removed = userStorage.removeUser("test");
        System.out.println("removed: " + removed);

        // test error case
        removed = userStorage.removeUser("test");
        System.out.println("removed: " + removed);

        user = userStorage.getUserById("test");
        System.out.println(user != null ? user.getName() : "no user found");
    }
}
