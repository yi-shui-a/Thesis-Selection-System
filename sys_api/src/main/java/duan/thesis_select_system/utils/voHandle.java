package duan.thesis_select_system.utils;

import duan.thesis_select_system.entity.vo.Paper_Admin_vo;
import duan.thesis_select_system.entity.vo.Paper_Student_vo;
import duan.thesis_select_system.entity.vo.Paper_teacher_vo;

import java.util.List;
import java.util.Objects;

import static duan.thesis_select_system.constant.PaperConstant.*;

public class voHandle {
    public static List<Paper_teacher_vo> Paper_teacher_voHandle(List<Paper_teacher_vo> vos){
        if (vos==null){
            return null;
        }
        for(Paper_teacher_vo vo:vos){
            if(vo.getCheckStatus().equals(CHECKED)){
                vo.setCheckStatus("通过");
            }else if(vo.getCheckStatus().equals(UNCHECKED)){
                vo.setCheckStatus("未审核");
            }
            else{
                vo.setCheckStatus("未通过");
            }
            if(vo.getSelectStatus().equals(NOT_SELECT)){
                vo.setSelectStatus("未选择");
            }else{
                vo.setSelectStatus("已选择");
            }
            if(vo.getAcceptStatus()==null){
                vo.setAcceptStatus("");
            }
            else if(vo.getAcceptStatus().equals(NOT_CHECK)) {
                vo.setAcceptStatus("未处理");
            }
            else if(vo.getAcceptStatus().equals(ACCEPT)){
                vo.setAcceptStatus("已接受");
            }
            else{
                vo.setAcceptStatus("已拒绝");
            }

        }
        return vos;
    }

    public static List<Paper_Student_vo> Paper_Student_voHandle(List<Paper_Student_vo> vos){
        if (vos==null){
            return null;
        }
        for(Paper_Student_vo vo:vos){
            if(vo.getAcceptStatus()==null){
                vo.setAcceptStatus("");
            }
            else if(vo.getAcceptStatus().equals(NOT_CHECK)) {
                vo.setAcceptStatus("未审核");
            }
            else if(vo.getAcceptStatus().equals(ACCEPT)){
                vo.setAcceptStatus("已接受");
            }
            else{
                vo.setAcceptStatus("已拒绝");
            }

        }
        return vos;
    }

    public static List<Paper_Admin_vo> Paper_Admin_voHandle(List<Paper_Admin_vo> vos){
        if (vos==null){
            return null;
        }
        for(Paper_Admin_vo vo:vos){
            if(Objects.equals(vo.getCheckStatus(), CHECKED))
                vo.setCheckStatus("已审核");
            else if(Objects.equals(vo.getCheckStatus(), UNCHECKED))
                vo.setCheckStatus("未审核");
            else
                vo.setCheckStatus("已拒绝");

            if(Objects.equals(vo.getSelectStatus(), NOT_SELECT))
                vo.setSelectStatus("未选择");
            else
                vo.setSelectStatus("已选择");

        }
        return vos;
    }
}
