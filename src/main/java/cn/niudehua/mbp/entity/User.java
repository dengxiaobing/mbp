package cn.niudehua.mbp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author: deng
 * @datetime: 2020/8/11 11:08 下午
 */
@TableName(value = "`user`")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User extends Model<User> {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 姓名
     */
    @TableField(value = "name", condition = SqlCondition.LIKE)
    private String name;

    /**
     * 年龄
     */
    @TableField(value = "age", condition = "%s&lt;#{%s}")
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


    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_AGE = "age";

    public static final String COL_EMAIL = "email";

    public static final String COL_MANAGER_ID = "manager_id";

    public static final String COL_CREATE_TIME = "create_time";
}