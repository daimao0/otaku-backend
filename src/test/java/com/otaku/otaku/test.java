package com.otaku.otaku;


import com.otaku.otaku.mapper.UserDao;
import com.otaku.otaku.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
public class test {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDao userDao;
    @Test
    public void testDataSource() throws SQLException {
        System.out.println(dataSource.getConnection());
        User userCondition = new User();
        userCondition.setUsername("呆毛");
        User user = userDao.selectOne(userCondition);
        System.out.println(user);
    }

}