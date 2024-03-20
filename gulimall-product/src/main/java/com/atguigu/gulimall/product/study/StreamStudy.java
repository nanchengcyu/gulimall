package com.atguigu.gulimall.product.study;

import com.mysql.cj.xdevapi.Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ClassName: StreamStudy
 * Package: com.atguigu.gulimall.product.study
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/19 16:44
 * @Version 1.0
 */
public class StreamStudy {
    public static void main(String[] args) {
        //案例导入 吧集合中所有以“张”开头的，且是3个字的元素存储到一个新的集合中
        ArrayList<String> names = new ArrayList<>();
        Collections.addAll(names, "张无忌", "周芷若", "赵敏", "张强", "张三丰");
        System.out.println(names);

        //1.原生list方法
        ArrayList<String> list = new ArrayList<>();
        for (String name : names) {
            if (name.startsWith("张") && name.length() == 3) {
                list.add(name);
            }

        }

        System.out.println(list);
        System.out.println("======================");

        //2.stream流
        //filter过滤条件 collect将过滤完的内容收集成为一个list集合
        //方式1 一个filter  下面中s a 均代表上方中的ArrayList names
        List<String> list2 = names.stream().filter(s -> s.startsWith("张") && s.length() == 3).collect(Collectors.toList());
        //方式2 也可以在filter后面继续跟filter
        List<String> list3 = names.stream().filter(s -> s.startsWith("张")).filter(a -> a.length() == 3).collect(Collectors.toList());
        System.out.println(list2);
        System.out.println(list3);


        //3.获取数组的stream流
        //3.1 Arrays.stream
        String[] arrays = {"张翠山","东方不败","堂大山"};
        Stream<String> stringStream = Arrays.stream(arrays);
        System.out.println(stringStream.filter(s -> s.contains("山")).collect(Collectors.toList()));
        System.out.println("===============================");
        //3.2 Stream.of
        System.out.println(Stream.of(arrays).filter(s -> s.contains("山")).collect(Collectors.toList()));
    }


}
