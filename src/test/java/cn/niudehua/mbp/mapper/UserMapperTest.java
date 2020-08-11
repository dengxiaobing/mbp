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
import java.util.List;


/**
 * @author: deng
 * @datetime: 2020/8/11 11:27 下午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void select() {
        List<User> users = userMapper.selectList(null);
//        Assert.assertEquals(5, users.size());
        users.forEach(System.out::println);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setName("向西");
        user.setAge(27);
        user.setEmail("xx@baomidou.com");
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        user.setRemark1("remark1");
        User.setRemark2("remark2");
        user.setRemark3("remark3");
        int insert = userMapper.insert(user);
        Assert.assertEquals(1, insert);
        log.info("影响记录数{}", insert);
    }


}