package step_03.factory.bean;


public class UserService {
    private int age;

    public UserService(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void queryUserInfo (){
        System.out.println("查询用户信息");
    }
}
