package top.yxf.test.leetcode;
/**
 *@author wuxiangsheng
 *@description 获取最长公共前缀
 *@className MaxCommonPrefix
 *@date 2019/12/9 9:13
 *
 **/
public class MaxCommonPrefix {

    public static String getMaxCommonPrefix(String[] strs) {

        if (null == strs && strs.length <= 0) {
            return "";
        }
        // 默认拿着第一个作为比较
        String str = strs[0];

        for (int i = 1; i < strs.length; i++) {
            System.out.println(strs[i].indexOf(str));
            while (strs[i].indexOf(str) != 0) {
                System.out.println(str);
                str = str.substring(0, str.length() - 1);

            }
        }

        return str;
    }

    public static void main(String[] args) {
        String[] strs = {"flower",  "flight","flow"};

        System.out.println(getMaxCommonPrefix(strs));
    }

}
