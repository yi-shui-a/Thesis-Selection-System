package duan.thesis_select_system.entity.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Paper_Student_vo {
    private Integer pno;
    private String title;
    private String type;
    private String description;
    private String teacherName;
    private String acceptStatus;
    private String refuseReason;
    private String createTime;
}
