package top.yxf.test.leetcode;

public class PrintChar {
    public PrintChar() {
    }

    public static void main(String[] args) {
        char[] numberChar = {'1','2','3','4','5'};

        char[] letterChar = {'A','B','C','D','E'};

        new Thread(() -> {

            for (int i = 0; i < numberChar.length; i++) {

                System.out.print(numberChar[i]);
            }

        }).start();

        new Thread(() -> {

            for (int i = 0; i < letterChar.length; i++) {

                System.out.print(letterChar[i]);

            }

        }).start();
    }
}
