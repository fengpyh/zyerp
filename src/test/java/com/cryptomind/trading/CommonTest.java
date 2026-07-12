package com.cryptomind.trading;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by chengli.wang on 2018/12/4.
 */
public class CommonTest {
    private Random random ;

    private List<Student> stuList ;
    @Before
    public void init() {
        random = new Random();
        stuList = new ArrayList<Student>() {
            {
                for (int i = 0; i < 100; i++) {
                    add(new Student("student" + i, random.nextInt(50) + 50));
                }
            }
        };
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Student {
        private String name;
        private Integer score;
        //-----getters and setters-----
    }

    // 列出班上超过85分的学生姓名，并按照分数降序输出用户名字
    @Test
    public void test1() {
        List<String> studentList = stuList.stream()
                .filter(x->x.getScore()>85)
                .sorted(Comparator.comparing(Student::getScore).reversed())
                .map(Student::getName)
                .limit(1)
                .collect(Collectors.toList());
        System.out.println(studentList);
    }



}
