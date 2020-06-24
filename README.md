# 学习动态代理

## 文件说明
com.suchaos.dynamic.proxy.SimpleProxyDemo -- 展示代理结构的简单示例

com.suchaos.dynamic.proxy.SimpleDynamicProxyDemo -- 使用 JDK 动态代理的简单示例

com.suchaos.dynamic.proxy.SelectingMethods -- 使用动态代理过程中，可以通过传递参数来过滤某些方法

> 参考：Java编程思想 14.7

## 将动态代理生成的 class 文件输出到文件中

> 注意：将 sun.misc.ProxyGenerator.saveGeneratedFiles 赋值为 true，可以将动态生成的 class 文件显示生成出来
1. IDEA 中 VM options 设置就可以 -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true
2. `System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");`
