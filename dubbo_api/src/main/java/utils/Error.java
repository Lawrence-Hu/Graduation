package utils;

import com.alibaba.dubbo.common.utils.StringUtils;

public class Error {
    String error;

    public Error(String error) {
        this.error = error;
    }

    public boolean hasError() {
        return StringUtils.isNotEmpty(error);
    }

    public String getErrorInfo() {
        if (hasError()) {
            return error;
        }
        return null;
    }
}
