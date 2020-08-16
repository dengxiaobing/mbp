package cn.niudehua.mbp.mapper;


import cn.niudehua.mbp.entity.User;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: deng
 * @datetime: 2020/8/13 11:25 下午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class UpdateTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void updateById() {
        User user = User.builder().id(1088248166370832385L).name("王大风").age(78).build();
        int i = userMapper.updateById(user);
        Assert.assertEquals(1, i);
        log.info("==>影响记录数:{}", i);
    }

    @Test
    public void updateByWrapper() {
        User user = User.builder().name("王小风").age(18).build();
        UpdateWrapper<User> updateWrapper = Wrappers.update();
        updateWrapper.eq("name", "王大风").eq("age", 78);
        int update = userMapper.update(user, updateWrapper);
        Assert.assertNotNull(update);
        log.info("==>影响记录数:{}", update);
    }

    @Test
    public void updateByWrapper2() {
        User user = User.builder().name("王小风").age(18).build();
        User whereUser = User.builder().name("王二风").build();
        LambdaUpdateWrapper<User> lambdaQuery = Wrappers.lambdaUpdate(whereUser);
        lambdaQuery.eq(User::getName, "王大风").eq(User::getAge, 78);
        int update = userMapper.update(user, lambdaQuery);
        Assert.assertNotNull(update);
        log.info("==>影响记录数:{}", update);
    }

    @Test
    public void updateByWrapper3() {
        UpdateWrapper<User> updateWrapper = Wrappers.update();
        updateWrapper.eq("name", "王小风").eq("age",78).set("name", "王二风").set("age",18);
        int update = userMapper.update(null, updateWrapper);
        Assert.assertNotNull(update);
        log.info("==>影响记录数:{}", update);
    }
    @Test
    public void updateByWrapperLambda() {
        LambdaUpdateWrapper<User> lambdaUpdate = Wrappers.lambdaUpdate();
        lambdaUpdate.eq(User::getName, "王二风").eq(User::getAge,18).set(User::getName, "王小风").set(User::getAge,78);
        int update = userMapper.update(null, lambdaUpdate);
        Assert.assertNotNull(update);
        log.info("==>影响记录数:{}", update);
    }

    @Test
    public void updateByWrapperLambdaChain() {
        LambdaUpdateChainWrapper<User> chainWrapper = new LambdaUpdateChainWrapper<>(userMapper);
        boolean update = chainWrapper.eq(User::getName, "王二风").eq(User::getAge, 18).set(User::getName, "王小风").set(User::getAge, 78).update();
        Assert.assertNotNull(update);
        log.info("==>影响记录数:{}", update);
    }
}
