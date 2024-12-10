package duan.thesis_select_system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
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
 * @since 2023-02-04
 */
@Getter
@Setter
  @TableName("select_deadline")
@ApiModel(value = "SelectDeadline对象", description = "")
public class SelectDeadline implements Serializable {

    private static final long serialVersionUID = 1L;

      private LocalDate selectDeadline;


}
