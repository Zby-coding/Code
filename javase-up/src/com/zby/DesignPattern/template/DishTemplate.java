package com.zby.DesignPattern.template;

/**
 * 模板方法为抽象方法 需用final关键字进行修饰
 * 为什么不用接口？ 因为在类中得有一个模板方法，需要方法体
 *
 * 优点：
 * 封装不变部分 扩展可变部分
 * 提取广告代码，便于维护
 * 行为父类控制 子类实现
 *
 * 缺点：
 * 1. 每一个不同的实现都需要一个子类来实现，导致类的个数增加，使得系统更加庞大
 * 2. 类之间的关系变的复杂，继承关系的引入会给系统带来很大的麻烦
 * 3. 增加了系统的抽象性和理解难度
 */
public abstract class DishTemplate {
    public final void cook() {
        this.prepare();
        this.doing();
        this.serve();
    }

    abstract void prepare();

    abstract void doing();

    abstract void serve();
}

class Dish1 extends DishTemplate {

    @Override
    void prepare() {
        System.out.println("准备dish1的材料...");
    }

    @Override
    void doing() {
        System.out.println("制备dish1...");
    }

    @Override
    void serve() {
        System.out.println("dish1已经做好...");
    }

    class Dish2 extends DishTemplate {
        @Override
        void prepare() {
            System.out.println("准备dish2的材料...");
        }

        @Override
        void doing() {
            System.out.println("制备dish2...");
        }

        @Override
        void serve() {
            System.out.println("dish2已经做好...");
        }
    }
}
class Test {
    public static void main(String[] args) {
        DishTemplate dish1 = new Dish1();
        dish1.cook();
        DishTemplate dish2 = new Dish1();
        dish2.cook();
    }
}


