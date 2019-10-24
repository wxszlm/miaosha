package top.yxf.miaosha.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 移动和包签名Test
 *
 * @author: zz
 * @date: 2019/10/21 17:25
 */
public class SignTest {

    private static final String signKey = "7940cefe5b161b66e8544d32129f25da";

    private static XmlMapper xmlMapper = new XmlMapper();

    static {
        xmlMapper.registerModule(new JaxbAnnotationModule());
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        xmlMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    public static void main(String[] args) throws IOException {
        String xmlStr = "<MESSAGE><CHANNELID>Chedd01</CHANNELID><DATE>20190814</DATE><GOODSID>G001500190807001</GOODSID><MCODE>TZ0001</MCODE><MERID>888001130000310</MERID><MID>1565778259464ST</MID><MOBILEID>13777871192</MOBILEID><PAYDAY>20190814</PAYDAY><PID>ZF201908141824190001</PID><QUANTITY>1</QUANTITY><SIGN>95D9F49B17A8CB042AD4754A7427C4C5</SIGN><STATUS>1</STATUS><TIME>182419</TIME></MESSAGE>";

        Map xmlMap = xmlMapper.readValue(xmlStr, Map.class);
        System.out.println("xml转Map:" + xmlMap);
        System.out.println("原签名:" + xmlMap.get("SIGN"));

        String sign = sign(xmlMap);
        System.out.println("签名结果:" + sign);
    }

    private static String sign(Map<String, Object> map) {
        String signStr = convertMapToUrlParams(map);
        signStr += "&KEY=" + signKey;
        // SecureUtil.md5就是普通的md5加密，使用你自己的md5加密方法就行
        return MD5.MD5Encode(new String(signStr.getBytes(Charset.forName("GBK")), Charset.forName("GBK"))).toUpperCase();
    }

    private static String convertMapToUrlParams(Map<String, Object> dataMap) {
        if (dataMap == null || dataMap.size() == 0) {
            return "";
        }
        dataMap.remove("SIGN");

        List<String> keyList = new ArrayList<String>();
        for (String key : dataMap.keySet()) {
            keyList.add(key);
        }

        Collections.sort(keyList, String.CASE_INSENSITIVE_ORDER);
        StringBuffer stringBuffer = new StringBuffer();
        for (Object key : keyList) {
            if (stringBuffer.length() == 0) {
                stringBuffer.append(key.toString().toUpperCase() + "=" + dataMap.get(key));
            } else {
                stringBuffer.append("&" + key.toString().toUpperCase() + "=" + dataMap.get(key));
            }
        }
        return stringBuffer.toString();
    }
}
