package top.yxf.test.sf;

/**
 *@author wuxiangsheng
 *@description 选择排序算法
 *@className SelectionSortSF
 *@date 2019/10/23 13:31
 *
 **/
public class SelectionSortSF {

    public static void main(String[] args) {

        int[] arr = {3,7,5,8,9,2,4,1,6};
        // 默认数组第一个是最大的数值
        int minPos = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[minPos])
                minPos = i;

        }
        System.out.println("minPos = " + minPos);
        int temp = arr[minPos];
        arr[minPos] = arr[0];
        arr[0] = temp;

        for (int j =0; j < arr.length; j++) {
            System.out.print(arr[j] + " ");
        }

    }

}
