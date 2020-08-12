package cn.niudehua.mbp.mapper;


import cn.niudehua.mbp.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        users.forEach(user -> log.info("==>user:{}", user));
    }

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
        log.info("影响记录数{}", insert);
    }

    @Test
    public void selectById() {
        User user = userMapper.selectById(1094590409767661570L);
        Assert.assertNotNull(user);
        System.out.println(user);
    }

    @Test
    public void selectByIds() {
        List<Long> ids = Arrays.asList(1293219260105076738L, 1293218106734301186L, 1293217248806297602L, 1293216422729613314L);
        List<User> users = userMapper.selectBatchIds(ids);
        Assert.assertNotNull(users);
        users.forEach(user -> log.info("==>user:{}", user));
    }

    @Test
    public void selectByMap() {
        Map<String, Object> columnMap = new HashMap<>();
//        columnMap.put("name", "向西");
//        columnMap.put("age", 27);
        // 查询map key值为数据表中的 列字段
//        columnMap.put("managerId", 1088248166370832385L);
        columnMap.put("manager_id", 1088248166370832385L);
        List<User> users = userMapper.selectByMap(columnMap);
        Assert.assertNotNull(users);
        users.forEach(user -> log.info("==>user:{}", user));
    }

    /**
     * 名字中包含雨并且年龄小于40
     * name like '%雨%' and age<40
     */
    @Test
    public void selectByWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.like("name", "%雨%").lt("age", 40);
        List<User> users = userMapper.selectList(queryWrapper);
        Assert.assertNotNull(users);
        users.forEach(user -> log.info("==>user:{}", user));
    }

    /**
     * 名字中包含雨年并且龄大于等于20且小于等于40并且email不为空
     * name like '%雨%' and age between 20 and 40 and email is not null
     */
    @Test
    public void selectByWrapper2() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.like("name", "%雨%").between("age", 20, 40).isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        Assert.assertNotNull(users);
        users.forEach(user -> log.info("==>user:{}", user));
    }

    /**
     * 名字为王姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列
     * name like '王%' or age>=25 order by age desc,id asc
     */
    @Test
    public void selectByWrapper3() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.like("name", "王%").or().gt("age", 20).orderByDesc("age").orderByAsc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        Assert.assertNotNull(users);
        users.forEach(user -> log.info("==>user:{}", user));
    }

    /**
     * 创建日期为2019年2月14日并且直属上级为名字为王姓
     * date_format(create_time,'%Y-%m-%d')='2019-02-14' and manager_id in (select id from user where name like '王%')
     */
    @Test
    public void selectByWrapper4() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0} ", "2019-02-14").inSql("manager_id", "select id from user where name like '王%'");
        List<User> users = userMapper.selectList(queryWrapper);
        Assert.assertNotNull(users);
        users.forEach(user -> log.info("==>user:{}", user));
    }

    /**
     * 名字为王姓并且（年龄小于40或邮箱不为空）
     * name like '王%' and (age<40 or email is not null)
     */
    @Test
    public void selectByWrapper5() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.likeRight("name", "王%").and(wq -> wq.lt("age", 40).or().isNotNull("email"));
        List<User> users = userMapper.selectList(queryWrapper);
        Assert.assertNotNull(users);
        users.forEach(user -> log.info("==>user:{}", user));
    }

    /**
     * 名字为王姓或者（年龄小于40并且年龄大于20并且邮箱不为空）
     * name like '王%' or (age<40 and age>20 and email is not null)
     */
    @Test
    public void selectByWrapper6() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.likeRight("name", "王%").or(wq -> wq.lt("age", 40).gt("age", 20).isNotNull("email"));
        List<User> users = userMapper.selectList(queryWrapper);
        Assert.assertNotNull(users);
        users.forEach(user -> log.info("==>user:{}", user));
    }

    /**
     * 年龄小于40或邮箱不为空）并且名字为王姓
     * (age<40 or email is not null) and name like '王%'
     */
    @Test
    public void selectByWrapper7() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.nested(wq -> wq.lt("age", 40).or().isNotNull("email")).likeRight("name", "王");
        List<User> users = userMapper.selectList(queryWrapper);
        Assert.assertNotNull(users);
        users.forEach(user -> log.info("==>user:{}", user));
    }

    /**
     * 年龄为30、31、34、35
     * age in (30、31、34、35)
     */
    @Test
    public void selectByWrapper8() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.in("age", Arrays.asList(25, 31, 34, 35));
        List<User> users = userMapper.selectList(queryWrapper);
        Assert.assertNotNull(users);
        users.forEach(user -> log.info("==>user:{}", user));
    }

    /**
     * 只返回满足条件的其中一条语句即可
     * limit 1
     */
    @Test
    public void selectByWrapper9() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.in("age", Arrays.asList(25, 31, 34, 35)).last("limit 1");
        List<User> users = userMapper.selectList(queryWrapper);
        Assert.assertNotNull(users);
        users.forEach(user -> log.info("==>user:{}", user));
    }

    /**
     * 名字中包含雨并且年龄小于40(需求1加强版)
     * 第一种情况：select id,name
     * from user
     * where name like '%雨%' and age<40
     */
    @Test
    public void selectByWrapperSuper() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.select("id", "name").like("name", "%雨%").lt("age", 40);
        List<User> users = userMapper.selectList(queryWrapper);
        Assert.assertNotNull(users);
        users.forEach(user -> log.info("==>user:{}", user));
    }

    /**
     * 名字中包含雨并且年龄小于40(需求1加强版)
     * 第二种情况：select id,name,age,email
     * from user
     * where name like '%雨%' and age<40
     */
    @Test
    public void selectByWrapperSuper2() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.like("name", "%雨%").lt("age", 40).select(User.class, info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("manager_id"));
        List<User> users = userMapper.selectList(queryWrapper);
        Assert.assertNotNull(users);
        users.forEach(user -> log.info("==>user:{}", user));
    }

    @Test
    public void testCondition() {
        condition("王", "");
//        condition("", "");
//        condition("", "x");
    }

    private void condition(String name, String email) {
        QueryWrapper<User> queryWrapper = Wrappers.query();
//        if (StringUtils.isNotBlank(name)) {
//            queryWrapper.like("name", name);
//        }
//        if (StringUtils.isNotBlank(email)) {
//            queryWrapper.like("email", email);
//        }
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name).like(StringUtils.isNotBlank(email), "email", email);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(user -> log.info("==>user:{}", user));

    }

    @Test
    public void selectByWrapperEntity() {
        User user = new User();
        user.setName("王");
        user.setAge(30);
        QueryWrapper<User> queryWrapper = Wrappers.query(user);
        List<User> users = userMapper.selectList(queryWrapper);
        Assert.assertNotNull(users);
        users.forEach(u -> log.info("==>u:{}", u));
    }

    @Test
    public void selectByWrapperAllEq() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        Map<String, Object> params = new HashMap<>();
        params.put("name", "王天风");
        params.put("age", null);
//        queryWrapper.allEq(params);
//        queryWrapper.allEq(params,false);
//        queryWrapper.allEq((k, v) -> !k.equals("name"), params);
        queryWrapper.allEq((k, v) -> !k.equals("name"), params);
        List<User> users = userMapper.selectList(queryWrapper);
        Assert.assertNotNull(users);
        users.forEach(user -> log.info("==>user:{}", user));
    }

    @Test
    public void selectByWrapperMaps() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
//        queryWrapper.like("name", "向").select(User.class,info->!info.getColumn().equals("manager_id")&&!info.getColumn().equals("create_time")&&!info.getColumn().equals("email"));
        queryWrapper.like("name", "向").select("name", "age", "email");
        List<Map<String, Object>> users = userMapper.selectMaps(queryWrapper);
        Assert.assertNotNull(users);
        users.forEach(map -> log.info("==>map:{}", map));
    }

    /**
     * 按照直属上级分组，查询每组的平均年龄、最大年龄、最小年龄。并且只取年龄总和小于500的组。
     * select avg(age) avg_age,min(age) min_age,max(age) max_age
     * from user
     * group by manager_id
     * having sum(age) <500
     */
    @Test
    public void selectByWrapperMaps2() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.select("name", "count(name)", "avg(age) avg_age", "min(age) min_age", "max(age) max_age").groupBy("manager_id").having("sum(age)<{0}", 500);
        List<Map<String, Object>> users = userMapper.selectMaps(queryWrapper);
        Assert.assertNotNull(users);
        users.forEach(map -> log.info("==>map:{}", map));
    }

    @Test
    public void selectByWrapperObjs() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.select("name", "count(name)", "avg(age) avg_age", "min(age) min_age", "max(age) max_age").groupBy("manager_id").having("sum(age)<{0}", 500);
        List<Object> objects = userMapper.selectObjs(queryWrapper);
        Assert.assertNotNull(objects);
        objects.forEach(name -> log.info("==>name:{}", name));
    }

    @Test
    public void selectByWrapperCount() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.like("name", "雨").gt("age", 20);
        Integer integer = userMapper.selectCount(queryWrapper);
        Assert.assertNotNull(integer);
        log.info("==>总记录数:{}", integer);
    }

    @Test
    public void selectByWrapperOne() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
//        queryWrapper.like("name", "雨").gt("age", 20);
        queryWrapper.eq("name", "刘红雨").gt("age", 20);
        User user = userMapper.selectOne(queryWrapper);
        Assert.assertNotNull(user);
        log.info("==>user:{}", user);
    }

    @Test
    public void selectLambda() {
//        LambdaQueryWrapper<User> lambdaQuery = new QueryWrapper<User>().lambda();
//        LambdaQueryWrapper<User> lambdaQuery = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.like(User::getName, "雨").lt(User::getAge, 40);
        List<User> users = userMapper.selectList(lambdaQuery);
        Assert.assertNotNull(users);
        users.forEach(user -> log.info("==>user:{}", user));
    }

    /**
     * 名字为王姓或者（年龄小于40并且年龄大于20并且邮箱不为空）
     * name like '王%' or (age<40 and age>20 and email is not null)
     */
    @Test
    public void selectLambda2() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.likeRight(User::getName, "王").or(lambdaQueryWrapper -> lambdaQueryWrapper.between(User::getAge, 20, 40).isNotNull(User::getEmail));
        List<User> users = userMapper.selectList(lambdaQuery);
        Assert.assertNotNull(users);
        users.forEach(user -> log.info("==>user:{}", user));
    }

    /**
     * 名字为王姓或者（年龄小于40并且年龄大于20并且邮箱不为空）
     * name like '王%' and (age<40  or email is not null)
     */
    @Test
    public void selectLambda3() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.likeRight(User::getName, "王").and(lambdaQueryWrapper -> lambdaQueryWrapper.lt(User::getAge, 40).or().isNotNull(User::getEmail));
        List<User> users = userMapper.selectList(lambdaQuery);
        Assert.assertNotNull(users);
        users.forEach(user -> log.info("==>user:{}", user));
    }

    @Test
    public void selectLambda4() {
        LambdaQueryChainWrapper<User> lambdaQueryChainWrapper = new LambdaQueryChainWrapper<User>(userMapper);
        List<User> users = lambdaQueryChainWrapper.like(User::getName, "雨").gt(User::getAge, 20).list();
        Assert.assertNotNull(users);
        users.forEach(user -> log.info("==>user:{}", user));
    }

    @Test
    public void selectMy() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.like(User::getName, "王").or(lq -> lq.like(User::getName, "雨"));
        List<User> users = userMapper.selectAll(lambdaQuery);
        Assert.assertNotNull(users);
        users.forEach(user -> log.info("==>user:{}", user));
    }

    @Test
    public void selectPage() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.like(User::getName, "王").or(lq -> lq.lt(User::getAge, 40));
        IPage<User> page = new Page<>(1, 2);
        IPage<User> userIPage = userMapper.selectPage(page, lambdaQuery);
        Assert.assertNotNull(userIPage);
        log.info("总页数：{}", userIPage.getPages());
        log.info("总记录数：{}", userIPage.getTotal());
        userIPage.getRecords().forEach(user -> log.info("==>user:{}", user));
    }

    @Test
    public void selectMapPage() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.like(User::getName, "王").or(lq -> lq.lt(User::getAge, 40));
        // 不查询总记录数
        IPage<Map<String, Object>> page = new Page<>(1, 2,false);
//        IPage<Map<String, Object>> page = new Page<>(1, 2);
        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(page, lambdaQuery);
        Assert.assertNotNull(mapIPage);
        log.info("总页数：{}", mapIPage.getPages());
        log.info("总记录数：{}", mapIPage.getTotal());
        mapIPage.getRecords().forEach(map -> log.info("==>map:{}", map));
    }

    @Test
    public void selectMyPage() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.like(User::getName, "王").or(lq -> lq.lt(User::getAge, 40));
        IPage<User> page = new Page<>(1, 2);
        IPage<User> userIPage = userMapper.selectMyPage(page, lambdaQuery);
        Assert.assertNotNull(userIPage);
        log.info("总页数：{}", userIPage.getPages());
        log.info("总记录数：{}", userIPage.getTotal());
        userIPage.getRecords().forEach(user -> log.info("==>user:{}", user));
    }
}