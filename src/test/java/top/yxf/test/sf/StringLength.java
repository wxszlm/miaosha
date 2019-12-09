package top.yxf.test.sf;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wuxiangsheng
 * @description 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * @className StringLength
 * @date 2019/10/28 8:44
 **/
public class StringLength {

    // 存储每个set的值


    public static int isMaxLength(String str) {
        List<Set> sets = new ArrayList<>();
        // 存储str的值
        Set<Character> stringSet = null;

        if (str.length() == 0 || null == str) {
            return 0;
        }
        char[] chars = str.toCharArray();
        for (int k = 0; k < chars.length; k++) {
            stringSet = new HashSet<>(str.length());
            for (int i = k; i < chars.length - k; i++) {
                if (i == k) {
                    stringSet.add(chars[i]);
                    continue;
                }
                if (chars[k] == chars[i]) {
                    break;
                }

                stringSet.add(chars[i]);
            }
            sets.add(stringSet);
        }

        int max = 0;
        for (int i = 0; i < sets.size(); i++) {
            System.out.println("这是第" + i + "个" + JSONObject.toJSONString(sets.get(i)));
            if (sets.get(i).size() > sets.get(max).size()) {
                max = i;
            }
        }

        return sets.get(max).size();
    }

    public static void main(String[] args) {

        System.out.println(isMaxLength("abcabcdbbd"));
    }

}
