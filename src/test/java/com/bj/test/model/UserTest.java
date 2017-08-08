package com.bj.test.model;

import com.bj.test.service.UserServiceI;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * Created by baojun on 2016/1/11.
 */
@Transactional
@Rollback(false)
public class UserTest extends BaseTest {
    @Autowired
    private UserServiceI userService;

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("baojun");
        user.setPassword("123456");
        userService.save(user);
    }

    @Test
    public void testGetById(){
        System.out.println("------");
        String id = "40289e49524350640152435068f10000";
        User user = userService.getById(id);
        System.out.println(user.getUsername());
        assertEquals("baojun",user.getUsername());
    }

    @Test
    public void testUpdate(){
        String id = "40289e49524350640152435068f10000";
        User user = userService.getById(id);
        user.setPassword("123456");
        userService.update(user);
    }

    @Test
    public void testDelete(){
        String id = "40289e49524350640152435068f10000";
        User user = userService.getById(id);
        userService.delete(user);
    }
}
