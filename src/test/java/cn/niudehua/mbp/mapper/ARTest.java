package cn.niudehua.mbp.mapper;

import cn.niudehua.mbp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: deng
 * @datetime: 2020/8/16 11:58 下午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ARTest {

    @Test
    public void insert() {
        User user = User.builder().age(29).name("霍元甲").email("hyj@baomidou.com").build();
        boolean insert = user.insert();
        log.info("==>:{}", insert);
        Assert.assertTrue(insert);
    }

    @Test
    public void selectAll() {
        User user = new User();
        List<User> users = user.selectAll();
        users.forEach(u -> log.info("==>user:{}", u));
        Assert.assertNotNull(users);
    }

    @Test
    public void selectById() {
        User user = new User();
        User userSelect = user.selectById(1087982257332887553L);
        log.info("==>userSelect:{}", userSelect);
        Assert.assertNotNull(userSelect);
    }

    @Test
    public void selectById2() {
        User user = new User();
        user.setId(1087982257332887553L);
        User userSelect = user.selectById();
        log.info("==>userSelect:{}", userSelect);
        Assert.assertNotNull(userSelect);
    }

    @Test
    public void updateById() {
        User user = User.builder().id(1087982257332887553L).name("王大朗").build();
        boolean updateById = user.updateById();
        log.info("==>updateById:{}", updateById);
        Assert.assertTrue(updateById);
    }

    @Test
    public void deleteById() {
        User user = User.builder().id(1295382205899567105L).build();
        boolean deleteById = user.deleteById();
        log.info("==>updateById:{}", deleteById);
        Assert.assertTrue(deleteById);
    }

}
