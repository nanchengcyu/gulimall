package cn.nanchengyu.mybatisplus.service.Impl;

import cn.nanchengyu.mybatisplus.mapper.UserMapper;
import cn.nanchengyu.mybatisplus.pojo.User;
import cn.nanchengyu.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserServiceImpl
 * Package: cn.nanchengyu.mybatisplus.service.Impl
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/22 23:46
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {



}
