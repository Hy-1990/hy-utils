package com.bigdata.hy_tools.stu;

/**
 * @Author huyi
 * @Date 2020/7/31 12:49
 * @Description:
 */
public enum StuEnums {
    A("a"),
    B("b"),
    C("c")
    ;

    private String name;

    StuEnums(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        System.out.println(StuEnums.A.ordinal() + " " + StuEnums.C.ordinal());
    }
}
