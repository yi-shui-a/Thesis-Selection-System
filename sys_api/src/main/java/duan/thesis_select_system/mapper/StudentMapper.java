package duan.thesis_select_system.mapper;

import duan.thesis_select_system.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import duan.thesis_select_system.entity.vo.Paper_Student_vo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author duanyhui
 * @since 2023-02-01
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    List<Paper_Student_vo> GetAblePaper(String sno);

    List<Paper_Student_vo> GetMyPaper(String sno, String acceptstatus);

    void SelectPaper(String sno, Integer pno);

    void CancelPaper(String sno, Integer pno);

    void ReSelectPaper(String sno, Integer pno);
}
