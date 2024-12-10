package duan.thesis_select_system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2023-02-02
 */
@Getter
@Setter
  @TableName("student_paper")
@ApiModel(value = "StudentPaper对象", description = "")
public class StudentPaper implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer spid;

      private String sno;

      private Integer pno;

      @ApiModelProperty("枚举类型（NOT_CHECK表示未审核默认为它，REFUSE表示拒绝，ACCEPT表示接受")
      private String acceptStatus;


      private String refuseReason;

      private String createTime;


}
