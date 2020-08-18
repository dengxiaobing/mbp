package cn.niudehua.mbp.service;

import cn.niudehua.mbp.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author: deng
 * @datetime: 2020/8/18 11:40 下午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void getAll() {
        Page<User> page = userService.page(new Page<>());
        page.getRecords().forEach(u -> log.info("==>user:{}", u));
        Assert.assertNotNull(page.getRecords());
    }

    @Test
    public void getOne() {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
//        User user = userService.getOne(queryWrapper.gt(User::getAge, 25));
        User user = userService.getOne(queryWrapper.gt(User::getAge, 25), false);
        log.info("==>user:{}", user);
        Assert.assertNotNull(user);
    }


    @Test
    public void batchSave() {
        User user1 = User.builder().name("老大哥").age(29).build();
//        User user1 = User.builder().id(1295751113806512129L).name("老大哥").age(29).build();
        User user2 = User.builder().name("老二").age(29).build();
        User user3 = User.builder().name("老三").age(29).build();
        List<User> users = Arrays.asList(user1, user2, user3);
        boolean b = userService.saveBatch(users);
        log.info("==>batchSave:{}", b);
        Assert.assertTrue(b);
    }


    @Test
    public void batchSaveOrUpdate() {
//        User user1 = User.builder().name("老大哥").age(29).build();
        User user1 = User.builder().id(1295751113806512129L).name("老大哥").age(30).build();
        User user2 = User.builder().name("老二").age(30).build();
        User user3 = User.builder().name("老三").age(30).build();
        List<User> users = Arrays.asList(user1, user2, user3);
        boolean b = userService.saveOrUpdateBatch(users);
        log.info("==>batchSave:{}", b);
        Assert.assertTrue(b);
    }

    @Test
    public void chain() {
        List<User> users = userService.lambdaQuery().like(User::getName, "老").list();
        users.forEach(u -> log.info("==>user:{}", u));
        Assert.assertNotNull(users);
    }

    @Test
    public void chain2() {
        boolean update = userService.lambdaUpdate().like(User::getName, "老").set(User::getAge, 99).update();
        log.info("==>update:{}", update);
        Assert.assertTrue(update);
    }

    @Test
    public void chain3() {
        boolean remove = userService.lambdaUpdate().like(User::getName, "老").remove();
        log.info("==>remove:{}", remove);
        Assert.assertTrue(remove);
    }
}
