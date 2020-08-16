package cn.niudehua.mbp.mapper;


import cn.niudehua.mbp.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: deng
 * @datetime: 2020/8/13 11:25 下午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class DeleteTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void deleteById() {
        int i = userMapper.deleteById(1293874745900593154L);
        Assert.assertEquals(1, i);
        log.info("==>删除条数:{}", i);
    }

    @Test
    public void deleteByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "向北");
        int i = userMapper.deleteByMap(columnMap);
        log.info("==>删除条数:{}", i);
    }

    @Test
    public void deleteBatchIds() {
        int i = userMapper.deleteBatchIds(Arrays.asList(1293216422729613314L, 1293217248806297602L, 1293218106734301186L));
        log.info("==>删除条数:{}", i);
    }

    @Test
    public void deleteByWrapper() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.lt(User::getAge, 30).or(lq -> lq.eq(User::getAge, 31));
        int delete = userMapper.delete(lambdaQuery);
        log.info("==>删除条数:{}", delete);
    }
}
