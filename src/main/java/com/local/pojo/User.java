package com.local.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;
import java.util.Date;

/**
 * @author: lou ke
 * @createDate: 2020/3/25 11:44
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    // id:  对应数据库中的主键(uuid,自增id,雪花算法,redis,)
    // 这里使用的是雪花算法，几乎不可能唯一

    /**
     * AUTO(0), 数据库id自增
     * NONE(1), 未设置主键
     * INPUT(2), 手动输入
     * ID_WORKER(3), 默认的全局唯一id
     * UUID(4), 全局唯一的id   uuid
     * ID_WORKER_STR(5); ID_WORKER的字符串表示
     */
    @TableId(type = IdType.AUTO)   //主键自增
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @Version    // 代表这是一个乐观锁
    private Integer version;

    // 注意： delete 是mysql关键字   所以字段名设为deleted
    @TableLogic    // 逻辑删除
    private Integer deleted;

    //字段添加填充内容
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
