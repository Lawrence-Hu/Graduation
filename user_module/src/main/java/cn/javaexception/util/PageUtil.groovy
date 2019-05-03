package cn.javaexception.util

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

class PageUtil {
    @NotNull(message="分页数量不能为空")
    @Min(message = "页面大小不合法",value = 0L)
    Long pageSize
    @NotNull(message="分页数量不能为空")
    @Min(message = "当前页不合法",value = 0L)
    Long currentPage

    @Override
    String toString() {
        return "PageUtil{" +
                "pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                '}'
    }
}
