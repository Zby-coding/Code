package com.zby.homework;

import java.util.ArrayList;

/**
 * 给定一个`整数数组` nums 和一个`整数目标值` target，请你在该数组中找出 和为目标值 target  的那 两个 整数，
 * 并返回它们的`数组下标`。 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。 你可
 * 以按任意顺序返回答案。
 */

public class Demo3 {
    public static void main(String[] args) throws IndexOutOfBoundsException {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int target = 6;
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = i + 1; j < arrayList.size(); j++) {
                if (arrayList.get(i) + arrayList.get(j) == target) {
                    System.out.println("[" + i + "," + j + "]");
                    return;
                }
            }
        }
    }
}
