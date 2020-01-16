package top.yxf.test.leetcode;

import java.util.Scanner;

public class YangHuiSanJiao {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入要打印的行数：");
        int n = scanner.nextInt();
        // 打印杨辉三角
        getTriangle(n);
    }

    /**
     * 打印杨辉三角
     *
     * @param n 要打印的行数
     * @return
     */
    private static int[][] getTriangle(int n) {
        // 创建一个二维数组，此二维数组用来存放杨辉三角中每一行的值
        int[][] array = new int[n][n];
        // 给数组元素赋值
        for (int i = 0; i < array.length; i++) {
            // 每一行的值
            array[i] = new int[i + 1];
            // 给首末元素赋值
            array[i][0] = array[i][i] = 1;
            // 给每行的非首末元素赋值
            if (i > 1) {
                for (int j = 1; j < array[i].length - 1; j++) {
                    array[i][j] = array[i - 1][j - 1] + array[i - 1][j];
                }
            }
        }
        // 遍历二维数组
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
        return array;
    }


}
