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

        for (int j = 0; j < arr.length; j++) {

            for (int i = j; i < arr.length -1; i++) {
//                if (arr[i] < arr[minPos])
//                    minPos = i;
                minPos = arr[i] < arr[minPos] ? i : minPos;
            }
            System.out.println("minPos = " + minPos);
            swap(arr,minPos,j);
        }
        for (int j =0; j < arr.length; j++) {
            System.out.print(arr[j] + " ");
        }

    }

    static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
