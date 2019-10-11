package top.yxf.miaosha.comon;

/**
 *@author wuxiangsheng
 *@description 通用回复
 *@className Result
 *@date 2019/10/10 13:34
 *
 **/
public class Result<T> {

    private int code;

    private T data;

    private String msg;

    private Result(T data){
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    private Result(CodeExceptionMsg cem) {
        if (cem == null) {
            return;
        }
        this.code = cem.getCode();
        this.msg = cem.getMsg();
    }

    public static <T> Result<T> success(T data){
        return new Result<>(data);
    }
    public static <T> Result<T> error(CodeExceptionMsg cem){
        return new Result<>(cem);
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }
}
