package cn.nanchengyu.mybatisplus;

import cn.nanchengyu.mybatisplus.mapper.UserMapper;
import cn.nanchengyu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * ClassName: MyBatisPlusWrapperTest
 * Package: cn.nanchengyu.mybatisplus
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/23 16:50
 * @Version 1.0
 */
@SpringBootTest
public class MyBatisPlusWrapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testSelect(){
        //查询用户名中包含a,年龄在20到30之间，且邮箱不为空

         List<User> users = userMapper.selectList(new QueryWrapper<User>().like("name", "a").between("age", 20, 30).isNotNull("email"));
        users.forEach(System.out::println);
    }
}
