package cn.javaexception.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author huchao
 * @since 2019-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Product extends Model<Product> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 产品名
     */
    private String productName;

    /**
     * 市场价格
     */
    private Double marketPrice;

    /**
     * 现价
     */
    private Double shopPrice;

    /**
     * 存库
     */
    private Integer stock;

    /**
     * 商品规格id
     */
    private String unitId;

    /**
     * 是否开售
     */
    private Boolean isSale;

    /**
     * 是否热销产品
     */
    private Boolean isHot;

    /**
     * 是否新品
     */
    private Boolean isNew;

    /**
     * 品牌Id
     */
    @JsonIgnore
    private String brandId;


    /**
     * 商品描述
     */
    private String description;

    private Boolean isAudit;
    /**
     * 销售数量
     */
    private Integer saleNum;

    /**
     * 上架时间
     */
    private Date saleTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 商品种类表
     */
    private String cateId;

    private String userId;

    private Integer hotIndex;

    private String carouselsImgUrl;

    private Boolean isCarosel;

    @TableField(exist = false)//图片属性
    private ProductBrand productBrand;
    @TableField(exist = false)//图片属性
    private List<Img> img;
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
