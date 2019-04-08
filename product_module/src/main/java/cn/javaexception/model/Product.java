package cn.javaexception.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author huchao
 * @since 2019-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Product extends Model<Product> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 产品名
     */

    private String productName;

    /**
     * 市场价格
     */
    private Float marketPrice;

    /**
     * 现价
     */
    private Float shopPrice;

    /**
     * 存库
     */
    private Integer productsStock;

    /**
     * 商品规格id
     */
    private Integer productUnitId;

    /**
     * 是否开售
     */
    private Boolean isSale;

    /**
     * 是否精品
     */
    private Boolean isBest;

    /**
     * 是否热销产品
     */
    private Boolean isHot;

    /**
     * 是否新品
     */
    private Boolean isNew;

    /**
     * 是否推荐
     */
    private Boolean isRecom;

    /**
     * 品牌Id
     */
    private Integer brandId;

    /**
     * 商品描述
     */
    private String productDesc;

    /**
     * -1:违规 0:未审核 1:已审核
     */
    private String productStatus;

    /**
     * 销售数量
     */
    private Integer saleNum;

    /**
     * 上架时间
     */
    private LocalDateTime saleTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 商品种类表
     */
    private Integer productCataId;

    private Integer userId;

    private Integer hotIndex;

    private String carouselsImgUrl;

    private Boolean isCarosel;

    @TableField(exist = false)
    List<Img> imgs = new ArrayList<>();

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
