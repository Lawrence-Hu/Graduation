package cn.javaexception.util;

import java.io.Serializable;

/**
 *
 * @author Laerence
 * @date 2018年12月8日
 * @Desciprtion
 */
public class JsonData implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 状态码：
     *  0 表示成功；
     *  1 表示处理中；
     *  -1 表示失败；
     */
    private Integer code;
    // 数据
    private Object data;
    // 描述
    private String msg;



    // 成功，传入数据：空数据
    public static JsonData buildSuccess() {
        return new JsonData(0, null, null);
    }

    // 成功：传入数据
    public static JsonData buildSuccess(Object data) {
        return new JsonData(0, data, null);
    }

    // 成功：传入数据和描述信息
    public static JsonData buildSuccess(Object data, String msg) {
        return new JsonData(0, data, msg);
    }

    // 成功，传入数据,及状态码
    public static JsonData buildSuccess(int code, Object data) {
        return new JsonData(code, data, null);
    }

    // 成功，传入数据、状态码和描述信息
    public static JsonData buildSuccess(int code, Object data, String msg) {
        return new JsonData(code, data, msg);
    }

    // 失败：传入描述信息
    public static JsonData buildError(String msg) {
        return new JsonData(-1, null, msg);
    }

    // 失败：传入描述信息，状态码
    public static JsonData buildError(Integer code, String msg) {
        return new JsonData(code, null, msg);
    }


    public JsonData() {

    }

    public JsonData(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "JsonData{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
