package top.yxf.miaosha.jsapi.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OrderNOGenerator {
    private static final Log log = LogFactory.getLog(OrderNOGenerator.class);

    /**
     * 获取订单编号
     *
     * @return 订单编号
     */
    public static String getNextNo() {
        String orderNo = null;
        try {
            Long curTime = System.currentTimeMillis();
            //获取当前时间戳
            orderNo = curTime.toString();
            //获取3位随机数
            int i = (int) (Math.random() * 900) + 100;
            orderNo = orderNo + i;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return orderNo;
    }

    /**
     * 获取退款编号
     *
     * @return 退款编号
     */
    public static String getReFundNo() {
        String orderNo = null;
        try {
            Long curTime = System.currentTimeMillis();
            //获取当前时间戳
            orderNo = curTime.toString();
            //获取3位随机数
            int i = (int) (Math.random() * 900) + 100;
            orderNo = "20"+orderNo + i;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return orderNo;
    }
}
