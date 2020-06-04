package top.yxf.test.linux;

import org.junit.Test;
import top.yxf.miaosha.comon.Result;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaOperationLinux {

    private static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(JavaOperationLinux.class);

    public static String exec(String command) throws InterruptedException {
        String returnString = "";
        Process pro = null;
        Runtime runTime = Runtime.getRuntime();
        System.out.println(runTime.freeMemory());
        if (runTime == null) {
            System.err.println("Create runtime false!");
        }
        try {
            pro = runTime.exec(command);
            BufferedReader input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            PrintWriter output = new PrintWriter(new OutputStreamWriter(pro.getOutputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                returnString = returnString + line + "\n";
            }
            input.close();
            output.close();
            pro.destroy();
        } catch (IOException ex) {
            // Logger.getLogger(JavaOperationLinux.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnString;
    }

    public static void callShell(String shellString) {
        try {
            Process process = Runtime.getRuntime().exec(shellString);
            int exitValue = process.waitFor();
            if (0 != exitValue) {
//                logger.error("call shell failed. error code is :" + exitValue);
            }
        } catch (Throwable e) {
//            logger.error("call shell failed. " + e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(exec("ls"));
    }


    @Test
    public void test() throws Exception {

    }

}
