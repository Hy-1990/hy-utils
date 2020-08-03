package com.bigdata.hy_tools.learn;

/**
 * @Author huyi
 * @Date 2020/7/16 16:26
 * @Description
 */
public class Plate<T> {
    private T item;

    public Plate(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public static void main(String[] args) {
        Plate<? extends Fruit> aa = new Plate<Apple>(new Apple());

        Fruit fruit = aa.getItem();
        System.out.println(fruit.getName());
//        Apple apple = aa.getItem();报错 因为只是知道aa的Fruit的子类，可能不是Apple，也可能是别的。


        Plate<? super Fruit> bb = new Plate<Fruit>(new Fruit());
        bb.setItem(new Apple());
//        Fruit ff = bb.getItem();
//        Apple apple = bb.getItem();
//        都报错因为下界定义了最小粒度

    }
}

class Apple extends Fruit {
    public String getName() {
        return this.getClass().getSimpleName();
    }
}

class Fruit {
    private String Name;

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public void setName(String name) {
        Name = name;
    }
}
