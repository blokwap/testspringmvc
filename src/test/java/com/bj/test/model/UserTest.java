package com.bj.test.model;

import com.bj.test.service.UserServiceI;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by baojun on 2016/1/11.
 */
public class UserTest extends BaseTest {
    @Autowired
    private UserServiceI userService;
    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("baojun");
        user.setPassword("password");
        userService.save(user);
    }
}
