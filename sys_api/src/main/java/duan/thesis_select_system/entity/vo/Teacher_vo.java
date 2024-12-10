package duan.thesis_select_system.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Setter
@Getter
public class Teacher_vo {
    private String tno;

    private String teacherName;

    private String sex;

    private String tel;

    private String email;

    private LocalDate birth;

    @ApiModelProperty("职称（助教，讲师，副教授，教授）")
    private String title;

    @ApiModelProperty("学历（学士，研究生，博士）")
    private String education;

    private Integer schoolyear;

    private Integer canTeachNum;

    private Integer hasTeachNum;
}
