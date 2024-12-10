package duan.thesis_select_system.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Paper_teacher_vo {
    private Integer pno;

    private String tno;
    private String sno;

    private String title;

    @ApiModelProperty("枚举类型（论文型，工程型）")
    private String type;

    @ApiModelProperty("审核状态（NOT_CHECK，CHECK,REFUSE）默认NOT_CHECK,只有审核状态为CHECK而且选择状态为NOT_SELECT才能被学生选择")
    private String checkStatus;

    @ApiModelProperty("选择状态（NOT_SELECT，HAS_SELECT）默认NOT_SELECT")
    private String selectStatus;

    private String rejectReason;
    private String refuseReason;

    private String studentName;
    private String acceptStatus;
    private Integer schoolyear;
    private String createTime;
    private String description;
}
