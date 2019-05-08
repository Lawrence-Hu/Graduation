package cn.javaexception.util;

import java.util.regex.Pattern;

/**
 * @author hcuhao
 * @date 2019-03-02-0:08
 */
public class FormatValidator {
    public static boolean isEmail(String str){
        return  (Pattern.matches("^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$",str));
    }
    public static boolean isPhone(String str){
        return (Pattern.matches("^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$",str));
    }
}
