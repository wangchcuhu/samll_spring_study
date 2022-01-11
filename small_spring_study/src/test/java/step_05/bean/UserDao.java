package step_05.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: small_spring_study
 * @description:
 * @author: Z-Man
 * @create: 2022-01-08 15:22
 */
public class UserDao {
    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("10001", "小傅哥");
        map.put("10002", "八杯水");
        map.put("10003", "阿毛");
    }

    public String queryUserName(String uID) {
        return map.get(uID);
    }
}
