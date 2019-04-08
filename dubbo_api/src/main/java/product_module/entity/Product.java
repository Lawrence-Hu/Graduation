package product_module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 2 * @Author: HuChao
 * 3 * @Date: 19-3-27 下午10:34
 * 4
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Product implements Serializable {



    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private static final long serialVersionUID = 1L;

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
    private String isSale;

    /**
     * 是否精品
     */
    private String isBest;

    /**
     * 是否热销产品
     */
    private String isHot;

    /**
     * 是否新品
     */
    private String isNew;

    /**
     * 是否推荐
     */
    private String isRecom;

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

    /**
     * 商品种类表
     */
    private Integer userId;
    /**
     *商品热度值
     */
    private Integer hotIndex;

    @TableField(exist = false)//图片属性
    private List<Img> img;

    @Data
    @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public static class Img{

        private Integer id;

        private String imgUrl;

        private Integer productId;
    }

}
