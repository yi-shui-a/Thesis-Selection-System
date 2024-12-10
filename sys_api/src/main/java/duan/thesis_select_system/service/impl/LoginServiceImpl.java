package duan.thesis_select_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.thesis_select_system.common.Result;
import duan.thesis_select_system.entity.Admin;
import duan.thesis_select_system.entity.Root;
import duan.thesis_select_system.entity.Student;
import duan.thesis_select_system.entity.Teacher;
import duan.thesis_select_system.mapper.AdminMapper;
import duan.thesis_select_system.mapper.RootMapper;
import duan.thesis_select_system.mapper.StudentMapper;
import duan.thesis_select_system.mapper.TeacherMapper;
import duan.thesis_select_system.utils.HashUtils;
import duan.thesis_select_system.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
@Service
public class LoginServiceImpl {

    @Autowired
    private RootMapper rootMapper;
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentMapper studentMapper;

    public Admin FindRootByUidAndPassword(String uid, String password) throws Exception {
        if (uid == null || password == null) {
            throw new Exception("用户名或密码错误");
        }
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getUid, uid);
        Admin admin = adminMapper.selectOne(wrapper);
        if (admin == null) {
            throw new Exception("用户名或密码错误");
        }
        if (!HashUtils.matchBC(password, admin.getPassword())) {
            throw new Exception("用户名或密码错误");
        }
        return admin;

    }
    public Teacher FindTeacherByTnoAndPassword(String tno, String password) throws Exception {
        if (tno == null || password == null) {
            throw new Exception("用户名或密码错误");
        }
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Teacher::getTno, tno);
        Teacher teacher = teacherMapper.selectOne(wrapper);
        if (teacher == null) {
            throw new Exception("用户名或密码错误");
        }
        if (!HashUtils.matchBC(password, teacher.getPassword())) {
            throw new Exception("用户名或密码错误");
        }
        return teacher;

    }

    public Student FindStudentBySnoAndPassword(String sno, String password) throws Exception {
        if(sno == null || password == null){
            throw new Exception("用户名或密码错误");
        }
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getSno, sno);
        Student student = studentMapper.selectOne(wrapper);
        if(student == null){
            throw new Exception("用户名或密码错误");
        }
        if(!HashUtils.matchBC(password, student.getPassword())){
            throw new Exception("用户名或密码错误");
        }
        return student;
    }
}
