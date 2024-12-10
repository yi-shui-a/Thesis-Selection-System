package duan.thesis_select_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.thesis_select_system.entity.Schoolyear;
import duan.thesis_select_system.entity.Teacher;
import duan.thesis_select_system.entity.TeacherStudent;
import duan.thesis_select_system.mapper.SchoolyearMapper;
import duan.thesis_select_system.mapper.TeacherMapper;
import duan.thesis_select_system.mapper.TeacherStudentMapper;
import duan.thesis_select_system.service.ISchoolyearService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duanyhui
 * @since 2023-02-04
 */
@Service
public class SchoolyearServiceImpl extends ServiceImpl<SchoolyearMapper, Schoolyear> implements ISchoolyearService {

    @Autowired
    private SchoolyearMapper schoolyearMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private TeacherStudentMapper teacherStudentMapper;
    public List<Schoolyear> getall() {
        return schoolyearMapper.selectList(null);
    }

    public void addSchoolyear(Integer schoolyear) {
        LambdaQueryWrapper<Schoolyear> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Schoolyear::getSchoolyear, schoolyear);
        if(schoolyearMapper.selectOne(queryWrapper) != null) {
            throw new RuntimeException("学年已存在");
        }
        Schoolyear schoolyear1 = new Schoolyear();
        schoolyear1.setSchoolyear(schoolyear);
        schoolyearMapper.insert(schoolyear1);
        //增加学年的同时增加教师接受选题的学年
        List<Teacher> teachers = teacherMapper.selectList(null);
        List<String> tnos = new ArrayList<>();
        for (Teacher teacher : teachers) {
            tnos.add(teacher.getTno());
        }
        for(String tno : tnos) {
            TeacherStudent teacherStudent = new TeacherStudent();
            teacherStudent.setTno(tno);
            teacherStudent.setSchoolyear(schoolyear);
            teacherStudentMapper.insert(teacherStudent);
        }

    }

    public void deleteSchoolyear(Integer schoolyear) {
        try{
            //删除学年的同时删除教师接受选题的学年
            List<Teacher> teachers = teacherMapper.selectList(null);
            List<String> tnos = new ArrayList<>();
            for (Teacher teacher : teachers) {
                tnos.add(teacher.getTno());
            }
            for(String tno : tnos) {
                LambdaQueryWrapper<TeacherStudent> queryWrapper1 = new LambdaQueryWrapper<>();
                queryWrapper1.eq(TeacherStudent::getTno, tno);
                queryWrapper1.eq(TeacherStudent::getSchoolyear, schoolyear);
                teacherStudentMapper.delete(queryWrapper1);
            }
            LambdaQueryWrapper<Schoolyear> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Schoolyear::getSchoolyear, schoolyear);
            schoolyearMapper.delete(queryWrapper);
        }
        catch (Exception e){
            throw new RuntimeException("删除失败，该学年已被使用");
        }


    }
}
