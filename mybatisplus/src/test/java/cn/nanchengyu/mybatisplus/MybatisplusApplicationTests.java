package cn.nanchengyu.mybatisplus;

import cn.nanchengyu.mybatisplus.mapper.UserMapper;
import cn.nanchengyu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.*;

@SpringBootTest
class MybatisplusApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
//        SELECT id,name,age,email,is_deleted FROM user WHERE is_deleted=0
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }


    @Test
    void testInsert() {
        User user = new User();
        user.setName("ncy");
        user.setAge(20);
//        user.setId(6L);
        user.setEmail("ncy@qq.com");
        int result = userMapper.insert(user);
        System.out.println(result);
    }

    @Test
    public void testDeleteMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","ncy");
        map.put("age",20);
        int result = userMapper.deleteByMap(map);
        System.out.println(result);

    }
    @Test
    public void testDeleteList(){
//        List<Long> list1 = Arrays.asList(1L, 2L);
//        ArrayList<Long> longs = new ArrayList<>();
//        UPDATE user SET is_deleted=1 WHERE id IN ( ? , ? ) AND is_deleted=0
        LinkedList<Long> list = new LinkedList<>();
        list.add(1L);
        list.add(6L);

        int result = userMapper.deleteBatchIds(list);
        System.out.println(result);

    }
    @Test
    public void testSelect(){
//        List<User> users = userMapper.selectList(new QueryWrapper<>(new User()));
//        System.out.println(users);
        System.out.println(userMapper.selectMapById(2L));
    }

}
