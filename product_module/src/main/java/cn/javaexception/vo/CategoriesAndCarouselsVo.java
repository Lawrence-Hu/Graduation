package cn.javaexception.vo;


import cn.javaexception.model.Category;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CategoriesAndCarouselsVo {
    private List<Category> categories;
    private List carousels;

}
