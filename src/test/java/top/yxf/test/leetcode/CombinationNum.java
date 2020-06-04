package top.yxf.test.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class CombinationNum {
    public static void main(String[] args) {
//        System.out.println(combinationSum(new int[]{2,4,5,3},8));

        testDiGui(new int[]{2, 4, 5, 3, 0}, 8, 0);
    }


    public static void testDiGui(int[] candidates, int target, int start) {

//        System.out.println(start + "-------------> start");

        if (target == 0) {
            return;
        } else if (target < 0) {
            return;
        } else {
            for (int i = start; i < candidates.length && candidates[i] > 0; i++) {

                System.out.println(candidates[i]);
                target = candidates[i];
                i++;
                testDiGui(candidates, target, i);
                target = -1;
            }
        }

        System.out.println(target);
    }


    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrace(res, new ArrayList<>(), candidates, 0, target);
        return res;
    }


    public static void backtrace(List<List<Integer>> res, List<Integer> list, int[] candidate, int start, int remain) {
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            res.add(new ArrayList<>(list));
            return;
        } else {
            for (int i = start; i < candidate.length && candidate[i] <= remain; i++) {
                list.add(candidate[i]);
                backtrace(res, list, candidate, i, remain - candidate[i]);
                list.remove(list.size() - 1);
            }
        }

    }

}
