package cn.niudehua.mbp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.niudehua.mbp.entity.User;
import cn.niudehua.mbp.mapper.UserMapper;
import cn.niudehua.mbp.service.UserService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: deng
 * @datetime: 2020/8/11 11:08 下午
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final UserMapper userMapper;

//    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void test1() {
        User test1 = User.builder().age(18).name("test1").email("test1@baomidou.com").build();
        userMapper.insert(test1);
        test2();
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void test2() {
        User test2 = User.builder().age(28).name("test2").email("test2@baomidou.com").build();
        userMapper.insert(test2);
        int i = 1 / 0;
    }
}
