package top.yxf.miaosha.jsapi;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import top.yxf.miaosha.jsapi.entity.ResultEntity;
import top.yxf.miaosha.jsapi.entity.TransfersDTO;
import top.yxf.miaosha.jsapi.util.*;

public class TestJsapi {

    private static final Log LOG = LogFactory.getLog(TestJsapi.class);


    public static void main(String[] args) {
        System.out.println(RandomStringGenerator.getRandomStringByLength(32));

        // 微信商户秘钥, 根据实际情况填写
        String appkey = "xxxxxxxxxxxxx";
        // 微信商户证书路径, 根据实际情况填写
        String certPath = "D:/demo/jsapi.p12";

        // 微信接口请求参数, 根据实际情况填写
        TransfersDTO model = new TransfersDTO();
        // 申请商户号的appid或商户号绑定的appid
//        model.setMch_appid("xxxxxxxxxx");
        model.setMch_appid("xxxxxxxxx");
        // 商户号
        model.setMchid("xxxxxxxxx");
        // 商户名称
        model.setMch_name("xxxxxxxxxxx");
        // 商户appid下，某用户的openid
//        model.setOpenid("oIRSR1hR2m7E3i6YOllRKB_IBPJw");
        model.setOpenid("xxxxxxxxx");
        // 企业付款金额，这里单位为元 金额不能低于1元，否则无法提现
        model.setAmount(1);
        model.setDesc("测试企业微信提现到账");
        ResultEntity iResult = JSAPIUtil.doTransfers(appkey, certPath, model);
        LOG.info(iResult);
    }

}
