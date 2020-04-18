package test;

/**
 * 测试引用变量的特性，引用变量可以看作指针，指针的指向变化不影响引用对象，而且也不影响其他指向同一对象的指针。
 */
public class Test1 {
    public static void main(String[] args) {
        Person a, b;
        a = b = new Person();
        b = new Person();
        System.out.println("a: " + a);
        System.out.println("b: " + b);
    }

    static class Person {
        int age;
        int name;

        public Person() {

        }
    }
}
