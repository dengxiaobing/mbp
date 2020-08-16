package cn.niudehua.mbp.mapper;


import cn.niudehua.mbp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @author: deng
 * @datetime: 2020/8/13 11:25 下午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class CreateTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setName("向北");
        user.setAge(26);
        user.setEmail("xb@baomidou.com");
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        int insert = userMapper.insert(user);
        Assert.assertEquals(1, insert);
        log.info("==>影响记录数{}", insert);
    }
}
