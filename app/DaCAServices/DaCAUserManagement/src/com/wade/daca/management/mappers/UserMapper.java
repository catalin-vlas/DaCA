package com.wade.daca.management.mappers;

import com.wade.daca.management.dataobjects.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return User.builder()
                .id(resultSet.getString("id"))
                .name(resultSet.getString("name"))
                .password(resultSet.getString("password"))
                .build();
    }
}
