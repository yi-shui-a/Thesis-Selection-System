package duan.thesis_select_system.mapper;

import duan.thesis_select_system.entity.Paper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import duan.thesis_select_system.entity.vo.Paper_Admin_vo;
import duan.thesis_select_system.entity.vo.Paper_teacher_vo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author duanyhui
 * @since 2023-01-27
 */
@Mapper
public interface PaperMapper extends BaseMapper<Paper> {

    List<Paper_teacher_vo> GetPaper(@Param("tno") String tno,
                                    @Param("title")String title,
                                    @Param("select_status") String select_status,
                                    @Param("check_status") String check_status,
                                    @Param("type")String type,
                                    @Param("studentname")String studentname,
                                    @Param("schoolyear")Integer schoolyear,
                                    @Param("accept_status")String accept_status);

    List<Paper_teacher_vo> GetPaper_no_name(String tno, String title, String select_status, String check_status, String type,Integer schoolyear,String accept_status);

    List<Paper_teacher_vo> GetStudentPaper(String tno, String title, String type, String studentname, Integer schoolyear,String select_status);

    void acceptStudent(Integer pno, String sno);

    List<Paper_teacher_vo> GetPaperSelected(String tno, String title, String type, String studentname, Integer schoolyear, String accept_status);

    void refuseStudent(Integer pno, String sno, String refuseReason);

    List<Paper_Admin_vo> GetAllPaper(String teacher_name, String title, String check_status, String type, Integer schoolyear);

    List<Paper_teacher_vo> GetHistoryPaperByTno(String tno, String title, String type, String studentname, Integer schoolyear, String accept_status);
}
