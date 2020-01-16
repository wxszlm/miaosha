package top.yxf.test.leetcode;

import java.util.ArrayList;
import java.util.List;

public class ForEachTest {

    public static void main(String[] args) {

        List<Object> strings = new ArrayList<>();

        strings.add("1");
        strings.add("2");
        strings.add("4");

        for (Object item : strings) {

            if ("4".equals(item)) {
                strings.remove(item);
                System.out.println("进来了!!!");
            }
        }

//        for (int i = 0; i < strings.size(); i++) {
//            if ("1".equals(strings.get(i))) {
//                strings.remove(i);
//                System.out.println("进来了!!!");
//            }
//        }

        for (Object item : strings) {
            System.out.println(item);
        }

    }

}
