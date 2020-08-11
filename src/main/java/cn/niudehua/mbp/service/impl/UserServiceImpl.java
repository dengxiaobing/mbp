package cn.niudehua.mbp.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.niudehua.mbp.entity.User;
import cn.niudehua.mbp.mapper.UserMapper;
import cn.niudehua.mbp.service.UserService;
/**
 * @author: deng
 * @datetime: 2020/8/11 11:08 下午
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

}
