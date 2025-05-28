package com.zby.arithmetic;

import java.util.HashMap;

public class TwoSum {
    public static void main(String[] args) {
        // 测试twoSum方法
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);
        System.out.println("[" + result[0] + ", " + result[1] + "]");

    }

    private static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; ; i++) {
            // 记录第i个元素
            int x = nums[i];
            // 记录目标值与第i个元素的差值
            int y = target - x;
            // 判断差值是否在hashMap中，若在则返回差值的索引和第i个元素的索引
            if (hashMap.containsKey(y)) {
                return new int[]{hashMap.get(y), i};
            }
            hashMap.put(x, i);
        }
    }
}
