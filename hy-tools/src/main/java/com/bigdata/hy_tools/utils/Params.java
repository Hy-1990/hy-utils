package com.bigdata.hy_tools.utils;

/**
 * @Author huyi
 * @Date 2020/7/27 19:21
 * @Description:
 */
public class Params {
    private String name;
    private String sex;

    public Params() {
    }

    public Params(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Params{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
