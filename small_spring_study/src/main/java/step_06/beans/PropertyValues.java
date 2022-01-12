package step_06.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: small_spring_study
 * @description:
 * @author: Z-Man
 * @create: 2022-01-08 13:58
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValuesList = new ArrayList<>();

    public void addPropertyValue(PropertyValue value) {
        propertyValuesList.add(value);
    }

    //获取单个的属性值
    public PropertyValue getPropertyValue(String propertyName) {
        //==比较的是变量(栈)内存中的对象的(堆)内存的地址，所以只要是大小相同，==比较的值就想通
        //第二点比较特殊的是String a ="abc";String b ="abc",这是唯一不需要new产生的对象,不放在压缩堆中，而是放在常量池中，所以a变量创建时会先在常量池中找有这个对象没，没有就创建
        //b由于a已经创建了String对象，所以b直接指向的是a的对象，所以，a==b
        for (PropertyValue pv : this.propertyValuesList) {
            if (pv.getName().equals(propertyName) ) {
                return pv;
            }
        }
        return null;
    }
    //获取单个的属性值
    public  PropertyValue[] getPropertyValue() {
        //返回的是一个全新的数组，对数组的修改不会影响到内部数组
        return this.propertyValuesList.toArray(new PropertyValue[0]);
    }
}
