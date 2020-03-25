package com.local.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.local.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author: lou ke
 * @createDate: 2020/3/25 11:47
 * @version: 1.0
 */
//在对应的mapper上面集成基本的类 BaseMapper
@Repository     //代表持久层
public interface UserMapper extends BaseMapper<User> {
    //所有的crud操作都已经编写完成
    //不需要以前那样配置一大堆文件
}
