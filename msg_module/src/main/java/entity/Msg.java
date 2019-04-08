package entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author huchao
 * @since 2019-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Msg extends Model<Msg> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 创建时间
     */
    private String creatTime;

    /**
     * 是否阅读
     */
    private String isRead;

    /**
     * 消息接受用户id
     */
    private String toUserId;

    /**
     * 消息来源id
     */
    private String fromUserId;

    /**
     * 读取消息时间
     */
    private LocalDateTime readTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
