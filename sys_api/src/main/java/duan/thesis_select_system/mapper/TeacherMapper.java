package duan.thesis_select_system.mapper;

import duan.thesis_select_system.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import duan.thesis_select_system.entity.vo.Teacher_vo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author duanyhui
 * @since 2023-01-17
 */
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {

    List<Teacher> selectTeacher();

    List<Teacher_vo> selectTeacherStudent(String tno);
}
