package com.atguigu.gulimall.product.web;

import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import com.atguigu.gulimall.product.vo.Catelog2Vo;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * ClassName: IndexController
 * Package: com.atguigu.gulimall.product.web.controller
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/14 18:06
 * @Version 1.0
 */
@Controller
public class IndexController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    RedissonClient redissonClient;
    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping({"/", "/index.html"})
    public String indexPage(Model model) {

        //查出所有的1级分类
        List<CategoryEntity> categoryEntities = categoryService.getLevel1Categorys();

        return "index";
    }

    @ResponseBody
    @GetMapping("/index/catalog.json")
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
        Map<String, List<Catelog2Vo>> catalogJson = categoryService.getCatalogJson();
        return catalogJson;

    }

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        RLock myLock = redissonClient.getLock("my_lock");
        //加锁
        myLock.lock();
        try {
            System.out.println("枷锁成功，执行业务..." + Thread.currentThread().getId());
            Thread.sleep(30000);
        } catch (Exception e) {
        } finally {
            System.out.println("释放锁。。。" + Thread.currentThread().getId());
            myLock.unlock();

        }
        return "hello";
    }

    /**
     * 写锁是一个排它锁（互斥锁、独享锁） 读锁是一个共享锁
     *  只要有写锁存在，就必须要等待
     */
    @ResponseBody
    @GetMapping("/write")
    public String writeValue() {
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("rw-lock");
        String s = "";
        RLock rLock = readWriteLock.writeLock();
        try {
            //改数据加写锁，读数据加读锁
            rLock.lock();
            s = UUID.randomUUID().toString();
            Thread.sleep(30000);
            redisTemplate.opsForValue().set("writeValue", s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rLock.unlock();
        }
        return s;
    }
    @ResponseBody
    @GetMapping("/read")
    public String readValue() {
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("rw-lock");
        String s = "";
        //加读锁
        RLock rLock = readWriteLock.readLock();
        rLock.lock();
        try {
            Thread.sleep(30000);
            redisTemplate.opsForValue().get("writeValue");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            rLock.unlock();
        }
        return s;
    }

    //闭锁
    @ResponseBody
    @GetMapping("/lockDoor")
    public String lockDoor() throws InterruptedException {

        RCountDownLatch door = redissonClient.getCountDownLatch("door");
        door.trySetCount(5);
        door.await();
        return "放假了";
    }

    @ResponseBody
    @GetMapping("/go/{id}")
    public String gogogo(@PathVariable("id") Long id){
        RCountDownLatch door = redissonClient.getCountDownLatch("door");
        door.countDown(); //计数减一
        return id+"班人都走完了";

    }
    //信号量 =》 限流
}
