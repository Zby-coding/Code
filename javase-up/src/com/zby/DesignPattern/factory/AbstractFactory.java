package com.zby.DesignPattern.factory;

/**
 * 抽象工厂模式
 * 适用场景：
 * 1.当需要创建的对象较多时，可以考虑使用抽象工厂模式
 * 2.当需要创建的对象的创建逻辑比较复杂时，可以考虑使用抽象工厂模式
 * 抽象工厂模式的优点：
 * 1.抽象工厂模式的优点是，它可以将对象的创建和使用分离，使得客户端不需要知道对象的创建细节，只需要知道对象的类型即可
 * 2.抽象工厂模式的优点是，它可以将对象的创建和使用分离，使得客户端不需要知道对象的创建细节，只需要知道对象的类型即可
 * 抽象工厂模式的缺点：
 * 1.抽象工厂模式的缺点是，当需要创建的对象的创建逻辑比较复杂时，会导致工厂类的代码变得非常复杂
 */
public class AbstractFactory {
    public static void main(String[] args) {
        SuperFactory superFactory = new SuperFactoryImpl();
        SuperFactory superFactory2 = new SuperFactoryImpl();
        Mask mask = superFactory2.createMask();
        mask.print();
        HuaWei huaWei = superFactory.createPhone();
        huaWei.print();
    }
}

interface SuperFactory {
    HuaWei createPhone();

    Mask createMask();
}

class SuperFactoryImpl implements SuperFactory {
    @Override
    public HuaWei createPhone() {
        return new HuaWei();
    }

    @Override
    public Mask createMask() {
        return new N95Mask();
    }
}

interface HuaWeiPhone {
    void print();
}

class HuaWei implements HuaWeiPhone {
    public void print() {
        System.out.println("输出华为手机-->HuaWei");
    }
}


interface Mask {
    void print();
}

class N95Mask implements Mask {
    @Override
    public void print() {
        System.out.println("输出口罩-->Mask[N95]");
    }
}
