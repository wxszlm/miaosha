package top.yxf.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *@author wuxiangsheng
 *@description 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *@className SF
 *@date 2019/10/14 8:42
 *
 **/
public class SF {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("abcabcbb"));
    }


    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        Set<Character> set = new HashSet<>(chars.length);
        Integer max = 0;
        for (int i = 0; i < chars.length; i++){
            for (int j = i; j < chars.length; j++){
                if (!set.add(chars[j])){
                    break;
                }
            }
            if (set.size() > max){
                max = set.size();
            }
            set.clear();
        }
        return max;

    }
    public static int lengthOfLongestSubstring2(String s) {
        char[] chars = s.toCharArray();
        Integer[] counts = new Integer[chars.length];
        Integer max = 0;
        for (int i = 0; i < chars.length; i++){
            for (int j = i; j < chars.length; j++){

                if (j != 0) {
                    if (chars[j] == chars[j - 1] || chars[j] == chars[i]) {
                        break;
                    }
                }
                max ++;

            }
            counts[i] = max;

        }
        int n = counts.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (counts[j] > counts[j + 1]) {
                    swap(counts, j, j + 1);
                }
            }
        }
        return counts[counts.length -1];

    }

    public static void swap(Integer[] arr, int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
