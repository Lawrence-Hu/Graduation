package cn.javaexception.util;

import java.util.regex.Pattern;

/**
 * @author hcuhao
 * @date 2019-03-02-0:08
 */
public class FormatValidator {
    public static boolean isEmail(String str){
        if(Pattern.matches("^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$",str))
            return true;
        else
        return false;
    }
    public static boolean isPhone(String str){
        if(Pattern.matches(" /^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$/",str))
            return true;
        else
            return false;
    }
}
