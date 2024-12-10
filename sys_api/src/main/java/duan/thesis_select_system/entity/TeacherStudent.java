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
 * @since 2023-02-03
 */
@Getter
@Setter
  @TableName("teacher_student")
@ApiModel(value = "TeacherStudent对象", description = "")
public class TeacherStudent implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("该表用于查询每个学年每个老师能指导的学生个数和已经指导的学生个数")
        private String tno;

    private Integer schoolyear;

    private Integer canTeachNum;

    private Integer hasTeachNum;


}
