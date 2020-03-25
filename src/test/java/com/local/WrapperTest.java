package com.local;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.local.mapper.UserMapper;
import com.local.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @author lou ke
 * @version 1.0
 * @date 2020/3/25 21:50
 */
@SpringBootTest
public class WrapperTest {

    //继承了BaseMapper,所有的方法都来自父类，我们也可以编写自己的扩展方法
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        // 查询 name 不为空，并且邮箱不为空，年龄大于等于12岁的用户

        QueryWrapper<User> wrapper = new QueryWrapper<>();   // wrapper 是一个对象，里面有很多方法
        wrapper
                .isNotNull("name")
                .isNotNull("email")
                .ge("age", 12);     //年龄大于等于12
        List<User> users = userMapper.selectList(wrapper);//和 map 对比一下
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    void test2() {
        // 查询名字等于 张三
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "张三");
        User user = userMapper.selectOne(wrapper);   //查询一个时使用One,多个时使用List 或者map
        System.out.println(user);
    }

    @Test
    void test3() {
        // between and  查询年龄在20-30岁之间的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 20, 30);
        Integer count = userMapper.selectCount(wrapper);//  查询结果数
        System.out.println("20-30岁之间的用户人数：" + count);
    }

    //模糊查询   查询名字里面不包含 J   和邮箱以 he 开头
    @Test
    void test4() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //左和右   likeRight: J%
        wrapper
                .notLike("name", "J")
                .likeRight("email", "he");

        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);

        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
    }

    // 内查询
    @Test
    void test5() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // id 在子查询里面查出来的
        wrapper
                .inSql("id", "select id from user where id<7");
        List<Object> objects = userMapper.selectObjs(wrapper);
        for (Object object : objects) {
            System.out.println(object);
        }
    }

    // 排序
    @Test
    void test6() {
        // 通过id 降序排序
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");

        List<User> users = userMapper.selectList(wrapper);
    }

}
