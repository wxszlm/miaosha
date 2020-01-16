package top.yxf.test.time;

import java.time.LocalDate;

/**
 *@author wuxiangsheng
 *@description 判断当前时间是不是闰年
 *@className LeapYear
 *@date 2020/1/16 9:00
 *
 **/
public class LeapYear {

    public static void main(String[] args) {

        final LocalDate now = LocalDate.now();

        if (now.isLeapYear()) {
            System.out.println("是闰年");
        } else {
            System.out.println("是平年");
        }

    }
}
