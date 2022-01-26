package step_11.aop.aspectj;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import step_11.aop.ClassFilter;
import step_11.aop.MethodMatcher;
import step_11.aop.Pointcut;
import step_11.event.UserService;


import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: small_spring_study
 * @description:这个对象在构造函数的时候给定包的范围，就可以判断给定的Class或者对象是否属于这个包
 * @author: Z-Man
 * @create: 2022-01-21 09:25
 */
public class AspectJExpressionPointcut implements ClassFilter, MethodMatcher, Pointcut {
    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<>();

    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    }
    private final PointcutExpression pointcutExpression ;

    public AspectJExpressionPointcut(String expression) {
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES, this.getClass().getClassLoader());
        pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }



    @Override
    public boolean matches(Class<?> clazz) {
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }

    public static void main(String[] args) throws NoSuchMethodException{
        //获取aspectJExpressionPointcut的对象，并且验证匹配功能是否可用
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut("execution(* step_11.event.UserService.*(..))");
        Class clazz = UserService.class;
        Method method = clazz.getMethod("queryUserInfo");
        System.out.println(aspectJExpressionPointcut.matches(clazz));
    }
}
