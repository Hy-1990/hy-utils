package com.bigdata.hy_tools.learn;

/**
 * @Author huyi
 * @Date 2020/7/16 16:12
 * @Description
 */
public class Human<T> {
    private T x;

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public static void main(String[] args) {
//        Human<Integer> h1 = new Human<>();
//        h1.setX(new Integer(100));
//        System.out.println(h1.getX());
//        Human<Float> h2 = new Human<>();
//        h2.setX(new Float(100.0));
//        System.out.println(h2.getX());

//        HumanChild<Integer,String> h3 = new HumanChild<>();
//        h3.setX(new Integer(100));
//        h3.setY(new String("aa"));
//        System.out.println(h3.getX() + "," + h3.getY());

        Info<Integer> info = new InfoImpl<>();
        info.setVar(new Integer(100));
        System.out.println(info.getVar());
    }
}

class HumanChild<T, U> extends Human<T> {
    private U y;

    public U getY() {
        return y;
    }

    public void setY(U y) {
        this.y = y;
    }
}

interface Info<T> {
    public T getVar();

    public void setVar(T var);
}

class InfoImpl<T> implements Info<T> {

    private T var;

    @Override
    public T getVar() {
        return this.var;
    }

    @Override
    public void setVar(T var) {
        this.var = var;
    }
}
