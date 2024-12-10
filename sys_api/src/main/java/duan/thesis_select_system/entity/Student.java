package duan.thesis_select_system.entity;

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
 * @since 2023-02-01
 */
@Getter
@Setter
  @ApiModel(value = "Student对象", description = "")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer sid;

      private String sno;

    private String studentName;

    private String password;

      @ApiModelProperty("枚举类型男和女")
      private String sex;

    private String tel;

    private String email;

    private String birth;

    private Integer schoolyear;

      @ApiModelProperty("学生可选题目数（默认为1）")
      private Integer ableTopicNum;

      @ApiModelProperty("已选题目数（默认为0）")
      private Integer selectedTopicNum;

    private String role;


}
