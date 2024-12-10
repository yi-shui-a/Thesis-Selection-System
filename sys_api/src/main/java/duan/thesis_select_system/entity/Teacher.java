package duan.thesis_select_system.entity;

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
 * @since 2023-01-17
 */
@Getter
@Setter
  @ApiModel(value = "Teacher对象", description = "")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer tid;

      private String tno;

    private String teacherName;

    private String password;

    private String sex;

    private String tel;

    private String email;

    private LocalDate birth;

      @ApiModelProperty("职称（助教，讲师，副教授，教授）")
      private String title;

      @ApiModelProperty("学历（学士，研究生，博士）")
      private String education;

    private String role;


}
