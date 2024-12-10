package duan.thesis_select_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author duanyhui
 * @since 2023-01-27
 */
@Getter
@Setter
  @ApiModel(value = "Paper对象", description = "")
public class Paper implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("此表为论文选题表")
        @TableId(value = "pno", type = IdType.AUTO)
      private Integer pno;

      private String tno;

    private String title;

      private String description;

      @ApiModelProperty("枚举类型（论文型，工程型）")
      private String type;

      @ApiModelProperty("审核状态（NOT_CHECK，CHECK,REFUSE）默认NOT_CHECK,只有审核状态为CHECK而且选择状态为NOT_SELECT才能被学生选择")
      private String checkStatus;

      @ApiModelProperty("选择状态（NOT_SELECT，HAS_SELECT）默认NOT_SELECT")
      private String selectStatus;

    private String rejectReason;

    private Integer schoolyear;


}
