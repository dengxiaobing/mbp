package cn.niudehua.mbp.mapper;


import cn.niudehua.mbp.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @author: deng
 * @datetime: 2020/8/11 11:27 下午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void select() {
        List<User> users = userMapper.selectList(null);
        Assert.assertEquals(5,users.size());
        users.forEach(System.out::println);
    }



}