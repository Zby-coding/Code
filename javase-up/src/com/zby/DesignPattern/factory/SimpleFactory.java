package com.zby.DesignPattern.factory;

/**
 * 简单工厂模式
 * 适用场景：
 * 1.当需要创建的对象较少时，可以考虑使用简单工厂模式
 * 2.当需要创建的对象较多时，不建议使用简单工厂模式，因为会导致工厂类的代码变得非常复杂
 * 3.当需要创建的对象的创建逻辑比较复杂时，不建议使用简单工厂模式，因为会导致工厂类的代码变得非常复杂
 * 简单工厂模式的优点：
 * 简单工厂模式的优点是，它可以将对象的创建和使用分离，使得客户端不需要知道对象的创建细节，只需要知道对象的类型即可
 * 简单工厂模式的缺点：
 * 1.简单工厂模式的缺点是，当需要创建的对象的创建逻辑比较复杂时，会导致工厂类的代码变得非常复杂
 * 2.简单工厂模式的缺点是，当需要创建的对象较多时，会导致工厂类的代码变得非常复杂
 */

public class SimpleFactory {
    public static Product createProduct(String type) {
        if (type.equals("A")) return new ProductA();
        else return new ProductB();
    }

    public static void main(String[] args) {
        Product product = SimpleFactory.createProduct("A");
        product.print();
    }

    /**
     * 抽象产品类
     */

    static abstract class Product {
        void print() {
        }
    }

    static class ProductA extends Product {
        @Override
        void print() {
            System.out.println("输出产品-->ProductA");
        }
    }

    static class ProductB extends Product {
        @Override
        void print() {
            System.out.println("输出产品-->ProductB");
        }
    }

}