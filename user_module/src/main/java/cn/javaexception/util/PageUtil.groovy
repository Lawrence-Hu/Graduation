package cn.javaexception.util

import lombok.Data
import lombok.Getter
import lombok.Setter

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
@Data
@Getter
@Setter
class PageUtil {
    @NotNull(message="分页数量不能为空")
    @Min(message = "页面大小不合法",value = 0L)
    Long pageSize
    @NotNull(message="分页数量不能为空")
    @Min(message = "当前页不合法",value = 0L)
    Long currentPage

    Long getPageSize() {
        return pageSize
    }

    void setPageSize(Long pageSize) {
        this.pageSize = pageSize
    }

    Long getCurrentPage() {
        return currentPage
    }

    void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage
    }

    @Override
    String toString() {
        return "PageUtil{" +
                "pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                '}'
    }
}
