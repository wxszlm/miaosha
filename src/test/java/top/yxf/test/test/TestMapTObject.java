package top.yxf.test.test;


import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.*;

public class TestMapTObject {

    public static void main(String[] args) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("channelId", "清华大学");
        map.put("date", "001");
        map.put("goodsId", "gxs");
        map.put("mCode", "gxs");
        map.put("merId", "gxs");
        map.put("mId", "gxs");
        map.put("mobileId", "gxs");
        map.put("payDay", "gxs");
        map.put("pId", "gxs");
        map.put("quantity", "gxs");
        map.put("sign", "gxs");
        map.put("status", "gxs");
        map.put("time", "gxs");



        CDDRequestMessage cddRequestMessage = null;
//        int len = 0;
//        try {
//            Class<?> clzz = Class.forName(student.getClass().getName());
//            len = clzz.getDeclaredFields().length;
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        if(map != null && map.size() == len) {
            try {
                cddRequestMessage = (CDDRequestMessage)mapToObject(map, CDDRequestMessage.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
//        }

        System.out.println(cddRequestMessage);
    }

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        BeanUtils.populate(obj, map);

        System.out.println(obj);

        return obj;
    }

}
