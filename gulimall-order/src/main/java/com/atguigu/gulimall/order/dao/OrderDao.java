package com.atguigu.gulimall.order.dao;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author nanchengyu
 * @email nanchengyu02@gmail.com
 * @date 2024-03-01 22:45:48
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
