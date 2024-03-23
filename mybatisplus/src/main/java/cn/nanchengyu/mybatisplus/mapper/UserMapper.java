package cn.nanchengyu.mybatisplus.mapper;
import java.util.List;

import cn.nanchengyu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserMapper extends BaseMapper<User> {



    Map<String,Object> selectMapById(Long id);

    //通过年龄查询用户信息并分页

    Page<User> selectPageVo(@Param("page") Page<User> page, @Param("age") Integer age);


    List<User> selectAllOrderByAgeDesc();
}
