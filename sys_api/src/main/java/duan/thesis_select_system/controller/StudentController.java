package duan.thesis_select_system.controller;


import duan.thesis_select_system.common.Result;
import duan.thesis_select_system.entity.Student;
import duan.thesis_select_system.mapper.StudentMapper;
import duan.thesis_select_system.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author duanyhui
 * @since 2023-02-01
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/get_myinfo")
    public Result get_myinfo(@RequestParam("sno") String sno){
        return Result.succ(studentService.GetInfoBySno(sno));
    }

    @PostMapping("/update_myinfo")
    public Result update_myinfo(@RequestBody Student student){
        studentService.updateInfo(student);
        return Result.succ("修改成功");
    }
    @PostMapping("/update_password")
    public Result update_password(@RequestParam("sno") String sno,@RequestParam("password") String password){
        studentService.updatePassword(sno,password);
        return Result.succ("修改成功");
    }

    /**
     * 获取该学生能选择的所有选题
     */
    @GetMapping("/get_able_paper")
    public Result get_all_paper(@RequestParam("sno") String sno){
        return Result.succ(studentService.GetAblePaper(sno));
    }

    @GetMapping("/get_mypaper")
    public Result get_mypaper(@RequestParam("sno") String sno,
                              @RequestParam(required = false,value = "acceptStatus") String acceptStatus){
        return Result.succ(studentService.GetMyPaper(sno,acceptStatus));
    }

    @PostMapping("/select_paper")
    public Result select_paper(@RequestParam("sno") String sno,
                               @RequestParam("pno") Integer pno){

        studentService.SelectPaper(sno,pno);
        return Result.succ("选题成功");
    }
    @PostMapping("/reselect_paper")
    public Result reselect_paper(@RequestParam("sno") String sno,
                               @RequestParam("pno") Integer pno){

        studentService.ReSelectPaper(sno,pno);
        return Result.succ("重新选题成功");
    }

    /**
     * 取消选题(只有未审核的选题才能取消)
     */
    @PostMapping("/cancel_paper")
    public Result cancel_paper(@RequestParam("sno") String sno,
                               @RequestParam("pno") Integer pno){
        studentService.CancelPaper(sno,pno);
        return Result.succ("取消选题成功");
    }


}

