package duan.thesis_select_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.thesis_select_system.entity.Schoolyear;
import duan.thesis_select_system.entity.Teacher;
import duan.thesis_select_system.entity.TeacherStudent;
import duan.thesis_select_system.entity.vo.Teacher_vo;
import duan.thesis_select_system.mapper.SchoolyearMapper;
import duan.thesis_select_system.mapper.TeacherMapper;
import duan.thesis_select_system.mapper.TeacherStudentMapper;
import duan.thesis_select_system.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import duan.thesis_select_system.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duanyhui
 * @since 2023-01-17
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private TeacherStudentMapper teacherStudentMapper;
    @Autowired
    private SchoolyearMapper schoolyearMapper;

    public Teacher GetTeacherByTno(String tno) {
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teacher::getTno, tno);
        Teacher teacher = teacherMapper.selectOne(queryWrapper);
        teacher.setPassword(null);
        return teacher;
    }

    public void updateInfo(Teacher teacher) {
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teacher::getTno, teacher.getTno());
        teacherMapper.update(teacher, queryWrapper);
    }

    public void updatePassword(String tno, String password) {
        String bc_pwd = HashUtils.getBC(password);
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teacher::getTno, tno);
        Teacher teacher = new Teacher();
        teacher.setPassword(bc_pwd);
        teacherMapper.update(teacher, queryWrapper);
    }

    public List<Teacher> getlist() {
        List<Teacher> teachers = teacherMapper.selectTeacher();
        for (Teacher teacher : teachers) {
            teacher.setPassword(null);
        }
        return teachers;
    }

    public List<Teacher_vo> getTeacherStudentList(String tno) {
        if(tno == null)
            tno = "";
        return teacherMapper.selectTeacherStudent(tno);
    }

    public void updateCanTeachNum(Teacher_vo teacher_vo) {
        LambdaQueryWrapper<TeacherStudent> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TeacherStudent::getTno, teacher_vo.getTno()).
                eq(TeacherStudent::getSchoolyear, teacher_vo.getSchoolyear());
        TeacherStudent teacherStudent = teacherStudentMapper.selectOne(queryWrapper);
        if(teacherStudent.getHasTeachNum()>teacher_vo.getCanTeachNum()){
            throw new RuntimeException("已通过学生人数超过修改数量，不能修改");
        }
        teacherStudent.setCanTeachNum(teacher_vo.getCanTeachNum());
        teacherStudentMapper.update(teacherStudent, queryWrapper);

    }

    public void updateCanTeachNumPiliang(Teacher_vo teacher) {
        LambdaQueryWrapper<TeacherStudent> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TeacherStudent::getTno, teacher.getTno());
        List<TeacherStudent> teacherStudents = teacherStudentMapper.selectList(queryWrapper);
        for(TeacherStudent teacherStudent : teacherStudents){
            LambdaQueryWrapper<TeacherStudent> queryWrapper2 = new LambdaQueryWrapper<>();
            queryWrapper2.eq(TeacherStudent::getTno, teacherStudent.getTno()).
                    eq(TeacherStudent::getSchoolyear, teacherStudent.getSchoolyear());
            TeacherStudent temp = teacherStudentMapper.selectOne(queryWrapper2);
            if(temp.getHasTeachNum()>temp.getCanTeachNum()){
                throw new RuntimeException("已通过学生人数超过修改数量，不能修改");
            }
            temp.setCanTeachNum(teacher.getCanTeachNum());
            teacherStudentMapper.update(temp, queryWrapper2);
        }

    }


    public void resetTeacher(String tno) {
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teacher::getTno, tno);
        Teacher teacher = new Teacher();
        teacher.setPassword(HashUtils.getBC("123456"));
        teacherMapper.update(teacher, queryWrapper);
    }

    public void addTeacher(Teacher teacher) {
        try {
            teacher.setPassword(HashUtils.getBC("123456"));
            teacherMapper.insert(teacher);
            LambdaQueryWrapper<Schoolyear> wrapper = new LambdaQueryWrapper<>();
            List<Schoolyear> schoolyears = schoolyearMapper.selectList(null);
            for(Schoolyear schoolyear : schoolyears){
                TeacherStudent teacherStudent = new TeacherStudent();
                teacherStudent.setTno(teacher.getTno());
                teacherStudent.setSchoolyear(schoolyear.getSchoolyear());
                teacherStudent.setCanTeachNum(5);
                teacherStudent.setHasTeachNum(0);
                teacherStudentMapper.insert(teacherStudent);
            }
        }
        catch (Exception e){
            throw new RuntimeException("添加失败,教师工号重复");
        }

    }


}
