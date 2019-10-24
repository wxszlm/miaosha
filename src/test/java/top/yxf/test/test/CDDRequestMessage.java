package top.yxf.test.test;

import java.io.Serializable;

public class CDDRequestMessage implements Serializable {

    /**
     * 平台渠道编号
     */
    private String channelId;
    /**
     * 交易日期
     */
    private String date;
    /**
     * 商品编号
     */
    private String goodsId;

    private String mCode;
    /**
     * 中心商户编号
     */
    private String merId;
    /**
     * 系统跟踪号
     */
    private String mId;
    /**
     * 用户手机号
     */
    private String mobileId;

    /**
     * 账务日期
     */
    private String payDay;
    /**
     * 平台流水号
     */
    private String pId;
    /**
     * 商品份数
     */
    private String quantity;
    /**
     * 签名
     */
    private String sign;
    /**
     * 状态 1:成功 其他:失败
     */
    private Integer status;
    /**
     * 交易时间
     */
    private String time;


    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getMobileId() {
        return mobileId;
    }

    public void setMobileId(String mobileId) {
        this.mobileId = mobileId;
    }

    public String getPayDay() {
        return payDay;
    }

    public void setPayDay(String payDay) {
        this.payDay = payDay;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CDDRequestMessage{" +
                "channelId='" + channelId + '\'' +
                ", date='" + date + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", mCode='" + mCode + '\'' +
                ", merId='" + merId + '\'' +
                ", mId='" + mId + '\'' +
                ", mobileId='" + mobileId + '\'' +
                ", payDay='" + payDay + '\'' +
                ", pId='" + pId + '\'' +
                ", quantity='" + quantity + '\'' +
                ", sign='" + sign + '\'' +
                ", status=" + status +
                ", time='" + time + '\'' +
                '}';
    }


}
