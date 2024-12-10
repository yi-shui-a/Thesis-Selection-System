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
 * @since 2023-02-04
 */
@Getter
@Setter
  @TableName("teacher_title")
@ApiModel(value = "TeacherTitle对象", description = "")
public class TeacherTitle implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("职称")
        private String title;


}
