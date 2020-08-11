package cn.niudehua.mbp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: deng
 * @datetime: 2020/8/11 11:08 下午
 */
@Data
@TableName(value = "`user`")
public class User {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 年龄
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 直属上级id
     */
    @TableField(value = "manager_id")
    private Long managerId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;
    /**
     * 排除非表字段方法1
     */
    private transient String remark1;
    /**
     * 排除非表字段方法2
     */
    private static String remark2;

    /**
     * 排除非表段方法3 推荐
     */
    @TableField(exist = false)
    private  String remark3;


    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_AGE = "age";

    public static final String COL_EMAIL = "email";

    public static final String COL_MANAGER_ID = "manager_id";

    public static final String COL_CREATE_TIME = "create_time";
}