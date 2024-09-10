

public class OOPS19 {
    public static void main(String args[]) {
      
    }
}


class Vehicle {
    void print() {
        System.out.println("vehice");
    }
}

class Car extends Vehicle {
    void prin(){
        System.out.println("car");
    }
}

interface Herbivore {
    String eat = "grass";
    void grass();
}

interface Carnivore {
     void meat();
}

class Bear implements Herbivore, Carnivore {
    public void grass() {
        System.out.println("eats grass");
    }

    public void meat() {
        System.out.println("eats meat");
    }

    public static void p() {
        System.out.println("print");
    }
}

class Tv {
    int screenSize;
    int volume;

    void isDisplay() {
        System.out.println("tv has a screen");
    }

}

class smartTv extends Tv {
    int resolution;
    int storage;
}

class onePlus extends smartTv {
    int andoidVersion;
    int ram;

    void runApps() {
        System.out.println("can run apps");
    }
}

class Student {
    String name;
    int age;
    int roll;
    int marks[];

    // Student(Student s1){ // shallow copy constructor
    // marks = new int[3];
    // this.name = s1.name;
    // this.age = s1.age;
    // this.marks = s1.marks;
    // }
    Student(Student s1) { // deep copy constructor
        marks = new int[3];
        this.name = s1.name;
        this.age = s1.age;
        for (int i = 0; i < marks.length; i++) {
            this.marks[i] = s1.marks[i];
        }
    }

    Student(int age, int roll) {
        marks = new int[3];
        this.age = age;
        this.roll = roll;
    }

    Student(String name) {
        marks = new int[3];
        this.name = name;
    }
}

class Pen {
    private String colour;
    private int tip;

    String getColour() {
        return this.colour;
    }

    int getTip() {
        return this.tip;
    }

    void setColour(String newColour) {
        colour = newColour;
    }

    void setTip(int newTip) {
        tip = newTip;
    }
}

class ComplexNumberPair {
    int rea;
    int imga;
    int reb;
    int imgb;

    ComplexNumberPair(int a, int b, int c, int d) {
        rea = a;
        imga = b;
        reb = c;
        imgb = d;
    }

    void printSumOfComplex() {
        System.out.println((rea + reb) + "+" + "i(" + (imga + imgb) + ")");
    }

    void printDifferenceOfComplex() {
        System.out.println((rea - reb) + "+" + "i(" + (imga - imgb) + ")");
    }

    void printProductOfComplex() {
        System.out.println(
            (rea * reb - imga * imgb) + "+" + "i(" + (rea * imgb + imga * reb) + ")"
        );
    }
}

class Robin {
    {
        System.out.println(5);
    }

    Robin() {
        System.out.println(4);
    }

    {
        System.out.println(3);
    }
}

class Kumar extends Robin {
    {
        System.out.println(2);
    }

    Kumar() {
        System.out.println(1);
    }

    {
        System.out.println(0);
    }
}

class SmartPhone {
    SmartPhone() {
        System.out.println("realme");
    }
}

class Realme extends SmartPhone {
    void print() {
        System.out.println("heklli");
    }
}

class BallPen extends Pen {
    int ghada;

    BallPen() {
        System.out.println("BallPen constructor is called");
        System.out.println(ghada);
    }
}

class HeavyPen extends BallPen {
    int heavypan;

    HeavyPen() {
        System.out.println("Heavypen constructor is called");
        System.out.println(getColour());
    }
}

abstract class Animal {
    String colour;

    Animal() {
        colour = "yellow";
        System.out.println("Animal constructor is called");
    }

    void eats() {
        System.out.println("Animal eats");
    }

    abstract void walks();
}

class Dog extends Animal {
    void setColour(String colour) {
        this.colour = colour;
    }

    Dog() {
        System.out.println("Dog constructor is called");
    }

    void walks() {
        System.out.println("The dog walks");
    }
}

class Horse extends Animal {
    void setColour(String colour) {
        this.colour = colour;
    }

    Horse() {
        System.out.println("Horse constructor is called");
    }

    void walks() {
        System.out.println("Horse walks");
    }
}

class Mustang extends Horse {
    Mustang() {
        System.out.println("mustang constructor are called");
    }
}

interface Study {

    String name = "Robin";

    void obreinfo();
}

interface Practical {
    int number = 5;

    void practicalaim();
}

class Phy implements Study, Practical {
    Phy() {
        System.out.println("phy constructor is called");
    }

    public void obreinfo() {
        System.out.println("phy function is executed ");
    }

    public void practicalaim() {
        System.out.println("khoj");
    }
}
