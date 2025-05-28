package com.zby.DesignPattern.factory;
/**
 * 工厂模式
 * 适用场景：
 * 1.当需要创建的对象较少时，可以考虑使用简单工厂模式
 * 2.当需要创建的对象较多时，不建议使用简单工厂模式，因为会导致工厂类的代码变得非常复杂
 * 3.当需要创建的对象的创建逻辑比较复杂时，不建议使用简单工厂模式，因为会导致工厂类的代码变得非常复杂
 */

public class Factory{
    public static void main(String[] args) {
        PhoneFactory factoryHuawei = new HuaweiFactory();
        PhoneFactory factoryXiaomi = new XiaomiFactory();
        Phone huaweiPhone = factoryHuawei.createPhone();
        Phone XiaomiPhone = factoryXiaomi.createPhone();
        huaweiPhone.print();
        XiaomiPhone.print();

    }
}
abstract class Phone{
    abstract void print();
}
class Huawei extends Phone{
    @Override
    void print() {
        System.out.println("输出A手机-->Huawei");
    }
}
class Xiaomi extends Phone{
    @Override
    void print() {
        System.out.println("输出B手机-->Xiaomi");
    }
}

interface PhoneFactory{
    Phone createPhone();

}
class HuaweiFactory implements PhoneFactory{
    @Override
    public Huawei createPhone() {
       return new Huawei();
    }
}
class XiaomiFactory implements PhoneFactory{
    @Override
    public Xiaomi createPhone() {
        return new Xiaomi();
    }
}