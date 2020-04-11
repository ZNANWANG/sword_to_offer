package practice;

public class Test {
    public static void main(String[] args){
        Person a, b;
        a = b = new Person();
        b = new Person();
        System.out.println("a: " + a);
        System.out.println("b: " + b);
    }

    static class Person{
        int age;
        int name;
        public Person(){

        }
    }
}
