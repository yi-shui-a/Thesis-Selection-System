package duan.thesis_select_system.entity.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Paper_Admin_vo {
    private Integer pno;
    private String tno;

    private String title;
    private String type;
    private String description;
    private String teacherName;
    private String checkStatus;
    private String selectStatus;
    private String rejectReason;
    private Integer schoolyear;
}
