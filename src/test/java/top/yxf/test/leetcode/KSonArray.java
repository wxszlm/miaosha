package top.yxf.test.leetcode;

import java.util.*;

public class KSonArray {


    public static void main(String[] args) {
        System.out.println(subarray(new int[]{1, 1, 2,2}, 2));

//        Map<String, String> map = new HashMap<>();
//        map.put("name", "lxj");
//        map.put("age", "24");
//        map.put("sex", "女");
//        String name = map.getOrDefault("name", "test");
//        System.out.println(name);// lxj，map中存在name,获得name对应的value
//        String address = map.getOrDefault("address", "北京");
//        System.out.println(address);// 北京，map中不存在address,使用默认值“北京
    }

    public static int subarray (int [] nums, int k) {
        Integer sum = 0;
        int num = nums[0];
        int repeatNumber = 1;
        if (nums == null) {
            return 0;
        }
        if(nums.length == k){
            return 1;
        }
        for (int i = 1; i < nums.length; i++) {
            if (num == nums[i]) {
                repeatNumber++;
                if (repeatNumber == k) {
                    sum++;
                    repeatNumber = 1;
                }
            } else {
                repeatNumber = 1;
                num = nums[i];
            }
        }
        return sum;
    }

    public static int subarraySum(int[] nums, int k) {
         /*
            使用前缀和
            方法一：使用一个 sum 数组，遍历 [1, len] 求得 sum[i] - sum[j] 如果为 k ，那么 res++ ，双重循环 O(n^2)
            方法二：
            (preSum[i] - preSum[j]) mod 1 == k ⟺ preSum[i] - k == preSum[j]
            我们使用 map 记录前缀结果和出现的次数
        */
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>(len);
        //初始化 结果为 0 的个数为 1
        map.put(0, 1);

        int res = 0;

        int sum = 0;
        for (int num : nums) {
            sum += num;
            res += map.getOrDefault(sum - k, 0);
            System.out.println(res  + ":res");
//            System.out.println(map.getOrDefault(sum - k, 0) + "--->map.getOrDefault(sum - k, 0)");
            System.out.println( map.getOrDefault(sum, 0));
            map.put(sum, map.getOrDefault(sum, 0) + 1);

            System.out.println(map);

//            System.out.println(map.put(sum, map.getOrDefault(sum, 0) + 1) + "map.put(sum, map.getOrDefault(sum, 0) + 1);");
        }
        return res;

    }

    public static void getAll(List<List<Integer>> lists, List<Integer> list, int[] nums, int k, int start) {
        if (k < 0) {
            return;
        } else if (k == 0) {
            lists.add(list);
            return;
//            return lists.size();
        } else {
            for (int i = start; i < nums.length && nums[i] <= k; i++) {
                list.add(nums[i]);
                getAll(lists, list, nums, k - nums[i], i);
                list.remove(list.size() - 1);
            }
        }
        System.out.println(lists);

//        return lists.size();
    }

}
