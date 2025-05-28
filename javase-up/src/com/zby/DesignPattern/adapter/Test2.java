package com.zby.DesignPattern.adapter;

/**
 * 对象适配器
 */
public class Test2 {
    public static void main(String[] args) {
        PowerAdapterObject powerAdapterObject = new PowerAdapterObject(new Power());
        powerAdapterObject.output5v();

    }
}
class PowerAdapterObject implements PowerTarget {
    private Power power = new Power();
    public PowerAdapterObject(Power power) {
        this.power = power;
    }

    @Override
    public int output5v() {
        int target  = power.output220()/44;
        System.out.println("类对象适配器输出目标类电压:" + target);
        return target;
    }
}