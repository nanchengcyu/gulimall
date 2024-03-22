package cn.nanchengyu.mybatisplus.mapper;

import cn.nanchengyu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserMapper extends BaseMapper<User> {



    Map<String,Object> selectMapById(Long id);


}
