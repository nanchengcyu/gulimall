package cn.nanchengyu.mybatisplus;

import cn.nanchengyu.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName: UserServiceTest
 * Package: cn.nanchengyu.mybatisplus
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/22 23:49
 * @Version 1.0
 */
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testCount(){
        System.out.println(userService.count());

    }




}
