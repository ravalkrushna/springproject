package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.bean.UserBean;

@Repository
public class UserDao {

    @Autowired
    JdbcTemplate stmt;

    public void saveUser(UserBean userBean) {
        stmt.update("insert into users (firstName, email, password, credentials) values (?, ?, ?, ?);",
                userBean.getFirstName(), userBean.getEmail(), userBean.getPassword(), 500);
    }

    // Add the findByEmail method to retrieve a user by email
    public UserBean findByEmail(String email) {
        try {
            return stmt.queryForObject("select * from users where email = ?",
                    new BeanPropertyRowMapper<>(UserBean.class), email);
        } catch (Exception e) {
            return null;  // Return null if no user is found
        }
    }
}
