package step_05.factory.config;

/**
 * @program: small_spring_study
 * @description:
 * @author: Z-Man
 * @create: 2022-01-08 15:04
 */
public class BeanReference {
    private final String beanName;

    public String getBeanName() {
        return beanName;
    }

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }
}
