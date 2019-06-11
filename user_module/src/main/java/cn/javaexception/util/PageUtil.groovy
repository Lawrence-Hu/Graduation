package cn.javaexception.util

import lombok.Data
import lombok.Getter
import lombok.Setter

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
@Data
class PageUtil {
    @NotNull(message="分页数量不能为空")
    @Min(message = "页面大小不合法",value = 0L)
    Long pageSize
    @NotNull(message="分页数量不能为空")
    @Min(message = "当前页不合法",value = 0L)
    Long currentPage
}
