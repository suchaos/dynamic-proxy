import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 使用 JDK 动态代理的简单示例
 *
 * @author suchao
 * @date 2019/2/19
 */
public class SimpleDynamicProxyDemo {

    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {

        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        RealObject real = new RealObject();
        consumer(real);

        System.out.println("-----------------------------");

        Interface proxy = (Interface) Proxy.
                newProxyInstance(Interface.class.getClassLoader(),
                        new Class<?>[]{Interface.class},
                        new DynamicProxyHandler(real));
        consumer(proxy);

    }
}

class DynamicProxyHandler implements InvocationHandler {

    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("******* proxy: " + proxy.getClass() + ", method: " + method + ", args: " + args);
        if (args != null) {
            for (Object arg : args) {
                System.out.println(" " + arg);
            }
        }
        return method.invoke(proxied, args);
    }
}
