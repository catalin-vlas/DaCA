package com.wade.daca.management.impl;

import com.wade.daca.management.api.UserStorage;
import com.wade.daca.management.dataobjects.User;
import com.wade.daca.management.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("userStorage")
public class UserStorageImpl implements UserStorage {

    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = :userId";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public User getUserById(String id) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("userId", id);
        return jdbcTemplate.queryForObject(GET_USER_BY_ID, params, new UserMapper());
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config/Spring.xml");
        UserStorage userStorage = (UserStorage) applicationContext.getBean("userStorage");
        User user = userStorage.getUserById("aurelian.hreapca");
        System.out.println(user.getName());
    }
}
