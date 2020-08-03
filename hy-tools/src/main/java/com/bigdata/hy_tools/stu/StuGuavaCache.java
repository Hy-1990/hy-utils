package com.bigdata.hy_tools.stu;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class StuGuavaCache {
    //操作内存模型
    public static LoadingCache<String, Student> studentLoadingCache;

    public static void init() {
        studentLoadingCache = CacheBuilder.newBuilder()
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .expireAfterAccess(8, TimeUnit.SECONDS)
                .initialCapacity(100000)
                .removalListener((RemovalListener<String, Student>) a -> {
                    if (a == null) {
                        return;
                    }
                    System.out.println("11111");
                }).build(new CacheLoader<String, Student>() {
                    @Override
                    public Student load(String key) throws Exception {
                        return null;
                    }
                });
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        init();
        studentLoadingCache.put("a", new Student("h1", "20"));
        studentLoadingCache.put("b", new Student("h2", "21"));

        System.out.println(studentLoadingCache.get("a").toString());

        Thread.sleep(10000);
        //如果内存中key 监听结束  返回自己想要的内容
        System.out.println(studentLoadingCache.get("a", () -> {
            return new Student("h1", "100");
        }));
    }
}

class Student {
    private String name;
    private String age;

    public Student() {
    }

    public Student(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
