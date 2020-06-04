package top.yxf.test.test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DuanDIanXuChuan {
    public static void main(String args[]) {
        File srcFile = new File("E:/test1/testMp4.mp4");
        File desDir = new File("E:/test2");
        copyFileToDir(srcFile, desDir);
    }

    public static void copyFileToDir(File srcFile, File desDir) {
        desDir.mkdirs();
        // 创建配置文件
        File configFile = new File(desDir, srcFile.getName().split("\\.")[0]
                + ".config");
        // 创建目标文件
        File desFile = new File(desDir, srcFile.getName());
        if (!configFile.exists() && desFile.exists()) {
            System.out.println("已下载过该文件！");
            return;
        }
        RandomAccessFile rafSrc = null;
        RandomAccessFile rafDes = null;
        RandomAccessFile rafConfig = null;
        try {
            rafSrc = new RandomAccessFile(srcFile, "r");
            rafDes = new RandomAccessFile(desFile, "rw");
            rafConfig = new RandomAccessFile(configFile, "rw");

            // 设置目标文件和源文件一样长
            rafDes.setLength(srcFile.length());
            // 设置配置的文件长度为8,防止第一次下载是出现EOF 异常
            rafConfig.setLength(8);

            // 从上次下载的位置开始继续下载！
            long pointer = rafConfig.readLong();
            System.out.println("已下载：" + ((float) pointer / srcFile.length())
                    * 100 + "%");
            rafSrc.seek(pointer);
            rafDes.seek(pointer);

            // 单次传输长度设置小点，好观察是否断点续传
            byte[] buffer = new byte[32];
            int len = -1;
            // 每次复制的开始，必须把源文件的指针和目标文件的指针从上次断开的位置去读
            while ((len = rafSrc.read(buffer)) != -1) {
                rafDes.write(buffer, 0, len);
                // 在配置文件写的时候，每次使文件指针移动到最初的位置 --> 这样永远对只会存储前8个字节
                rafConfig.seek(0);
                // 每复制一次之和，赶紧记录下文件指针的位置，以备断点续传使用。
                rafConfig.writeLong(rafSrc.getFilePointer());
            }

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                rafSrc.close();
                rafDes.close();
                rafConfig.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            // 在流关闭之后删除配置文件
            System.out.println("下载成功！");
            configFile.delete();
        }
    }
}
