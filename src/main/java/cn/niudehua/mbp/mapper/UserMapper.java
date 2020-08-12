package cn.niudehua.mbp.mapper;

import cn.niudehua.mbp.entity.User;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: deng
 * @datetime: 2020/8/11 11:08 下午
 */
public interface UserMapper extends BaseMapper<User> {
//    @Select("select * from user ${ew.customSqlSegment}")
    List<User> selectAll(@Param(Constants.WRAPPER)Wrapper<User> wrapper);
    IPage<User> selectMyPage(IPage<User> user, @Param(Constants.WRAPPER)Wrapper<User> wrapper);

 }