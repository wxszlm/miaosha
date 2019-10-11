package top.yxf.miaosha.comon;

/**
 *@author wuxiangsheng
 *@description 异常通用回复
 *@className CodeExceptionMsg
 *@date 2019/10/10 13:45
 *
 **/
public class CodeExceptionMsg {

    private int code;

    private String msg;

    public static CodeExceptionMsg SUCCESS = new CodeExceptionMsg(0,"success");
    public static CodeExceptionMsg ERROR = new CodeExceptionMsg(1,"服务端异常");

    private CodeExceptionMsg(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
