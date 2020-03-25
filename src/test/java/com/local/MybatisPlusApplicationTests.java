package com.local;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.local.mapper.UserMapper;
import com.local.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    //继承了BaseMapper,所有的方法都来自父类，我们也可以编写自己的扩展方法
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        //查询全部用户
        //里面的参数 wrapper ：条件构造器，这里不用先设为null
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

    //测试插入
    @Test
    public void testInsert() {
        User user = new User();
        user.setName("新用户");
        user.setAge(3);
        user.setEmail("helloworld@qq.com");

        int result = userMapper.insert(user); //帮我们自动生成id
        System.out.println(result); //受影响的行数
        System.out.println(user); //发现，id 会自动生成
    }

    //测试更新
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(6L);
        user.setName("更新会更改时间");
        user.setAge(18);

        // 注意，updateById  参数是一个 user
        int i = userMapper.updateById(user);
        System.out.println(i);  //受影响的行数
    }

    // 测试乐观锁
    @Test
    public void testOptimisticLocker() {
        //1.查询用户信息
        User user = userMapper.selectById(1L);

        //2.修改用户信息
        user.setName("修改");
        user.setEmail("huiYuan@163.com");

        //3.执行更新操作
        userMapper.updateById(user);
    }

    // 测试乐观锁失败
    @Test
    public void testOptimisticLocker2() {
        // 线程1
        User user = userMapper.selectById(1L);
        user.setName("local111");
        user.setEmail("huiYuan@163.com");

        //模拟另外一个线程执行了插队操作
        User user2 = userMapper.selectById(1L);
        user2.setName("local222");
        user2.setEmail("huiYuan@163.com");
        userMapper.updateById(user2);

        // 自旋锁来实现多次插入
        userMapper.updateById(user); //如果没有乐观锁，就会覆盖插队线程的值
    }

    // 测试查询
    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

    // 测试批量查询
    @Test
    public void testSelectByBatchId() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        for (User user : users) {
            System.out.println(user);
        }
    }

    //按条件查询之一： 使用 map 操作
    @Test
    public void testSelectByMap() {
        HashMap<String, Object> map = new HashMap<>();
        //自定义要查询的条件
        map.put("name", "张三");

        List<User> users = userMapper.selectByMap(map);
        for (User user : users) {
            System.out.println(user);
        }
    }

    // 测试分页查询
    @Test
    public void testPage() {
        // 参数一： 当前页
        // 参数二： 页面大小
        Page<User> page = new Page<>(1, 5);
        userMapper.selectPage(page, null);

        page.getRecords().forEach(System.out::println);

        //打印总数
        System.out.println(page.getTotal());
    }

    //测试删除
    @Test
    public void testDeleteById() {
        userMapper.deleteById(1L);
    }

    //通过id批量删除
    @Test
    public void testDeleteBatchId() {
        userMapper.deleteBatchIds(Arrays.asList(4L, 5L));
    }

    //通过map 按条件删除
    @Test
    public void testDeleteByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Jack");
        userMapper.deleteByMap(map);
    }

}
