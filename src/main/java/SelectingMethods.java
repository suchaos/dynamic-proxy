import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 使用动态代理过程中，可以通过传递参数来过滤某些方法
 *
 * @author suchao
 * @date 2019/2/19
 */
public class SelectingMethods {

    public static void consume(SomeMethods someMethods) {
        someMethods.boring1();
        someMethods.boring2();
        someMethods.boring3();
        someMethods.interesting("bonobo");
    }

    public static void main(String[] args) {
        SomeMethods noproxy = new Implementation();
        consume(noproxy);

        System.out.println("--------------------------------");

        SomeMethods proxy = (SomeMethods) Proxy.
                newProxyInstance(SomeMethods.class.getClassLoader(),
                        new Class<?>[]{SomeMethods.class},
                        new MethodSelector(new Implementation()));
        consume(proxy);


    }
}

class MethodSelector implements InvocationHandler {

    private Object proxied;

    public MethodSelector(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("interesting".equals(method.getName())) {
            System.out.println("Proxy detected the interesting method");
        }
        return method.invoke(proxied, args);
    }
}

interface SomeMethods {
    void boring1();

    void boring2();

    void boring3();

    void interesting(String arg);
}

class Implementation implements SomeMethods {

    @Override
    public void boring1() {
        System.out.println("boring1");
    }

    @Override
    public void boring2() {
        System.out.println("boring2");
    }

    @Override
    public void boring3() {
        System.out.println("boring3");
    }

    @Override
    public void interesting(String arg) {
        System.out.println("interesting " + arg);
    }
}