package step_03;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//验证一下只要我们能拿到一个类的Class,就可以完整的拿到整个对象
public class Reflect {
    public static void main(String[] args) {
        //拿到person的Class
        Class<Person> personClass =Person.class;

        try {
            //使用默认的函数创建对象
            Person person = personClass.newInstance();
            System.out.println("无参"+person);
            //想要使用有参的构造函数
            Constructor<?>[] constructor =personClass.getDeclaredConstructors();
            for (Constructor constructor1 : constructor) {
                //这里只对比了参数的数量的不同，实际上还需要更多的对比
                if (constructor1.getParameterTypes().length == 2) {
                    System.out.println(constructor1.toString());
                }
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

class Person{
    private String name="zhang";
    private int age=18;
    public Person() {
    }
    public Person(String name) {
        this.name = name;
    }

    public Person(int age) {
        this.age = age;
    }
    public Person(int age,String name) {
        this.age = age;
        this.name=name;
    }
    public Person(String name,int age) {
        this.age = age;
        this.name=name;
    }

    @Override
    public String toString() {
        return name+"/"+age;
    }
    //验证有构造函数创建实例
    public static void main(String[] args) {
        Class clazz = Person.class;
        try {
            //参数是参数类型的Class来删选出需要的构造函数
            Constructor<Person> ctor = clazz.getDeclaredConstructor(String.class);
            try {
                Person person= ctor.newInstance("zhangLei");
                System.out.println(person);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}