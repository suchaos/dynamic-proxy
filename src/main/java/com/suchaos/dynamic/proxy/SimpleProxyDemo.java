package com.suchaos.dynamic.proxy;

/**
 * Thinking in java page 337
 * 展示代理结构的简单示例
 *
 * @author suchao
 * @date 2019/2/19
 */
public class SimpleProxyDemo {

    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        consumer(new RealObject());
        System.out.println("--------------------------");
        consumer(new SimpleProxy(new RealObject()));
    }
}

interface Interface {

    void doSomething();

    void somethingElse(String args);
}

class RealObject implements Interface {

    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }

    @Override
    public void somethingElse(String args) {
        System.out.println("something else " + args);
    }
}

class SimpleProxy implements Interface {

    private Interface proxied;

    public SimpleProxy(Interface proxied) {
        this.proxied = proxied;
    }

    @Override
    public void doSomething() {
        System.out.println("com.suchaos.dynamic.proxy.SimpleProxy doSomething");
        this.proxied.doSomething();
    }

    @Override
    public void somethingElse(String args) {
        System.out.println("com.suchaos.dynamic.proxy.SimpleProxy somethingElse " + args);
        this.proxied.somethingElse(args);
    }
}
