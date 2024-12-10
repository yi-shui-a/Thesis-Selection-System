package duan.thesis_select_system.controller;


import duan.thesis_select_system.common.Result;
import duan.thesis_select_system.entity.Admin;
import duan.thesis_select_system.entity.Root;
import duan.thesis_select_system.entity.Student;
import duan.thesis_select_system.entity.Teacher;
import duan.thesis_select_system.service.impl.LoginServiceImpl;
import duan.thesis_select_system.utils.HashUtils;
import duan.thesis_select_system.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author duanyhui
 * @since 2023-01-14
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping("/admin")
    public Result admin_login(@RequestParam("username") String uid, @RequestParam("password") String password) throws Exception {
        Admin user = loginService.FindRootByUidAndPassword(uid, password);
        user.setPassword(null);
        String jwt= JwtUtil.createJWT("admin:"+user.getUid());
        HashMap<String, Object>map = new HashMap<>();
        map.put("token",jwt);
        map.put("user",user);
        return Result.succ(map);
    }

    @PostMapping("/teacher")
    public Result teacher_login(@RequestParam("username") String tno, @RequestParam("password") String password) throws Exception {
        Teacher user = loginService.FindTeacherByTnoAndPassword(tno, password);
        user.setPassword(null);
        String jwt= JwtUtil.createJWT("teacher:"+user.getTno());
        HashMap<String, Object>map = new HashMap<>();
        map.put("token",jwt);
        map.put("user",user);
        return Result.succ(map);
    }

    @PostMapping("/student")
    public Result student_login(@RequestParam("username") String sno, @RequestParam("password") String password) throws Exception {
        Student student = loginService.FindStudentBySnoAndPassword(sno, password);
        student.setPassword(null);
        String jwt= JwtUtil.createJWT("student:"+sno);
        HashMap<String, Object>map = new HashMap<>();
        map.put("token",jwt);
        map.put("user",student);
        return Result.succ(map);
    }




}

