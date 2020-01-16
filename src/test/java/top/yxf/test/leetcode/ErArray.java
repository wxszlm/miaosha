package top.yxf.test.leetcode;

import com.alibaba.fastjson.JSONObject;

public class ErArray {

    public static void main(String[] args) {
        int[][] array = {{1,2,3},{4,5,6},{7,8,9}};

        for (int i = 0; i <array.length;i ++) {
            System.out.println(JSONObject.toJSONString(array[i]));
        }
    }
}
