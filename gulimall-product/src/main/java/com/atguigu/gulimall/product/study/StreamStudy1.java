package com.atguigu.gulimall.product.study;

import java.util.*;
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
public class StreamStudy1 {
    public static void main(String[] args) {
        //案例导入
        List<Student> students = new ArrayList<>();
        Student s1 = new Student("蜘蛛精", 26, 172.5);
        Student s2 = new Student("蜘蛛精", 26, 172.5);
        Student s3 = new Student("紫霞", 23, 167.6);
        Student s4 = new Student("白晶晶", 25, 169.0);
        Student s5 = new Student("牛夫人", 35, 183.3);
        Collections.addAll(students, s1, s2, s3, s4, s5);

        //1. 身高超过168的学生有几人
        System.out.println(students.stream().filter(student -> student.getHeight() > 168).count());
        //2.身高最高的学生
        System.out.println(students.stream().max((o1, o2) -> Double.compare(o1.getHeight(), o2.getHeight())));
        //方法引用简化写法
        System.out.println(students.stream().max(Comparator.comparingDouble(Student::getHeight)).get());
        //最矮
        System.out.println(students.stream().min(Comparator.comparingDouble(Student::getHeight)).get());

        //4.身高超过170 放入一个新集合返回

        System.out.println(students.stream().filter(student -> student.getHeight() > 170).collect(Collectors.toList()));

        //5.身高超过170 学生名字和身高放入一个map集合放回
        Map<String, Double> collect = students.stream().filter(student -> student.getHeight() > 170).distinct().collect(Collectors.toMap(a -> a.getName(), a -> a.getHeight()));
        System.out.println(collect);


    }


}
