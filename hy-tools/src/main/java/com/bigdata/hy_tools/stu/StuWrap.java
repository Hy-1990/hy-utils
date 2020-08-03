package com.bigdata.hy_tools.stu;

/**
 * @author huyi
 * @date 2020/6/12 15:32
 */
public class StuWrap {
    public static void main(String[] args) {
        Animal animal = new Cat();
        Animal colorAnimal = new ColorAnimalDecorator(animal);
        colorAnimal.getName();
    }
}
interface Animal{
    void getName();
}
class Cat implements Animal{

    @Override
    public void getName() {
        System.out.println("nananana");
    }
}
class Dog implements Animal{

    @Override
    public void getName() {
        System.out.println("aasdasdasdas");
    }
}

abstract class AnimalDecorator implements Animal{
    protected Animal animal;

    public AnimalDecorator(Animal animal) {
        this.animal = animal;
    }
    public void getName(){
        animal.getName();
    }
}

class ColorAnimalDecorator extends AnimalDecorator{

    public ColorAnimalDecorator(Animal animal) {
        super(animal);
    }
    public void getName(){
        animal.getName();
        ppp();
    }
    public void ppp(){
        System.out.println("asdasddqwqwe----");
    }
}