package cn.nanchengyu.mybatisplus;

import cn.nanchengyu.mybatisplus.mapper.ProductMapper;
import cn.nanchengyu.mybatisplus.mapper.UserMapper;
import cn.nanchengyu.mybatisplus.pojo.Product;
import cn.nanchengyu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName: MyBatisPlusPluginsTest
 * Package: cn.nanchengyu.mybatisplus
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/23 17:53
 * @Version 1.0
 */
@SpringBootTest
public class MyBatisPlusPluginsTest {
    @Autowired
    UserMapper userMapper;
    @Autowired
    ProductMapper productMapper;

    @Test
    public void test01(){
        IPage<User> userIPage = userMapper.selectPage(new Page<User>().setPages(1L).setSize(2L), null);
        System.out.println(userIPage);

    }
    @Test
    public void test02(){
        //自定义分页
        Page<User> userPage = new Page<>(1L,4L);
        System.out.println(userMapper.selectPageVo(userPage, 20));

    }


    @Test
    public void testOptimisticLockerInnerInterceptor(){
        //小李查询价格
        Product productXL = productMapper.selectById(1L);
        System.out.println("小李查询到的价格"+productXL);
       //小王查询到的价格
        Product productXW = productMapper.selectById(1L);
        System.out.println("小王查询到的价格"+productXW);

        //小l将价格+50
        productXL.setPrice(productXL.getPrice()+50);
        productMapper.updateById(productXL);


        //小王将价格-30
        productXW.setPrice(productXW.getPrice()-30);
        int result = productMapper.updateById(productXW);
        if (result==0){
            Product productNew = productMapper.selectById(1L);
            productNew.setPrice(productNew.getPrice()-30);
            productMapper.updateById(productNew);
        }
        //老板查询的价格 加乐观锁后的价格应该为150

        Product productBoss = productMapper.selectById(1L);
        System.out.println("老板查询到的价格"+productBoss);



    }


}
