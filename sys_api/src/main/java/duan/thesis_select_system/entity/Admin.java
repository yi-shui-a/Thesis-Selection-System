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
 * @since 2023-02-04
 */
@Getter
@Setter
  @ApiModel(value = "Admin对象", description = "")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

      private String uid;

    private String name;

    private String password;

    private String sex;

    private String tel;

    private String email;

    private String role;


}
