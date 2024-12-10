package duan.thesis_select_system.controller;


import duan.thesis_select_system.common.Result;
import duan.thesis_select_system.entity.Paper;
import duan.thesis_select_system.entity.StudentPaper;
import duan.thesis_select_system.entity.Teacher;
import duan.thesis_select_system.service.impl.PaperServiceImpl;
import duan.thesis_select_system.service.impl.TeacherServiceImpl;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static duan.thesis_select_system.constant.PaperConstant.NOT_SELECT;
import static duan.thesis_select_system.constant.PaperConstant.UNCHECKED;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author duanyhui
 * @since 2023-01-17
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherServiceImpl teacherService;
    @Autowired
    private PaperServiceImpl paperService;

    @PostMapping("/create_paper")
    public Result create_paper(@RequestParam("tno") String tno,
                               @RequestParam("title") String title,
                               @RequestParam("type") String type,
                               @RequestParam("schoolyear") Integer schoolyear,
                               @RequestParam("description") String description) throws Exception {
        Paper paper = new Paper();
        paper.setTitle(title);
        paper.setTno(tno);
        paper.setType(type);
        paper.setSelectStatus(NOT_SELECT);
        paper.setCheckStatus(UNCHECKED);
        paper.setSchoolyear(schoolyear);
        paper.setDescription(description);
        paperService.create(paper);
        return Result.succ("创建毕业设计题目成功");
    }

    @PostMapping("/delete_paper")
    public Result delete_paper(@RequestParam("pno") Integer pno) throws Exception {
        paperService.DeleteByPno(pno);
        return Result.succ("删除毕业设计题目成功");
    }

    @PostMapping("/update_paper")
    public Result update_paper(@RequestBody Paper paper) throws Exception {
        paperService.updateById(paper);
        return Result.succ("修改毕业设计题目成功");
    }

    /**
     * 传入tno，查询该教师的所有毕业设计题目
     */
    @GetMapping("/get_paper")
    public Result get_paper(@RequestParam("tno") String tno,
                            @RequestParam(required = false,value = "title") String title,
                            @RequestParam(required = false,value = "selectStatus") String select_status,
                            @RequestParam(required = false,value = "checkStatus") String check_status,
                            @RequestParam(required = false,value = "type") String type,
                            @RequestParam(required = false,value = "studentName") String studentname,
                            @RequestParam(required = false,value = "schoolyear") Integer schoolyear,
                            @RequestParam(required = false,value = "acceptStatus") String accept_status
                            ){
//        return Result.succ(paperService.GetPaperByTno(tno));
        return Result.succ(paperService.GetPaper(tno,title,select_status,check_status,type,studentname,schoolyear,accept_status));
    }
    @GetMapping("/get_paper_selected")
    public Result get_paper_selected(@RequestParam("tno") String tno,
                                     @RequestParam(required = false,value = "title") String title,
                                     @RequestParam(required = false,value = "type") String type,
                                     @RequestParam(required = false,value = "studentName") String studentname,
                                     @RequestParam(required = false,value = "schoolyear") Integer schoolyear,
                                     @RequestParam(required = false,value = "acceptStatus") String accept_status
    ){
        return Result.succ(paperService.GetPaperSelected(tno,title,type,studentname,schoolyear,accept_status));
    }

    @GetMapping("/get_myinfo")
    public Result get_myinfo(@RequestParam("tno") String tno){
        return Result.succ(teacherService.GetTeacherByTno(tno));
    }

    @PostMapping("/update_myinfo")
//    public Result update_myinfo(@RequestParam("tno") String tno,
//                                @RequestParam("techerName") String name,
//                                @RequestParam("tel") String tel,
//                                @RequestParam("email") String email,
//                                @RequestParam("title") String title,
//                                @RequestParam("education") String education){
        public Result update_myinfo(@RequestBody Teacher teacher) throws Exception {
        try {
            teacherService.updateInfo(teacher);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return Result.succ("修改个人信息成功");
    }
    @PostMapping("update_password")
    public Result update_password(@RequestParam("tno") String tno,
                                  @RequestParam("password") String password) throws Exception {
        try {
            teacherService.updatePassword(tno,password);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return Result.succ("修改密码成功");
    }
    @GetMapping("/get_need_check_paper")
    public Result get_student_paper(@RequestParam("tno") String tno,
                                    @RequestParam(required = false,value = "title") String title,
                                    @RequestParam(required = false,value = "type") String type,
                                    @RequestParam(required = false,value = "studentName") String studentname,
                                    @RequestParam(required = false,value = "schoolyear") Integer schoolyear,
                                    @RequestParam(required = false,value = "selectStatus") String select_status){
        return Result.succ(paperService.GetStudentPaper(tno,title,type,studentname,schoolyear,select_status));
    }
    @PostMapping("/accept_student")
    public Result accept_student(@RequestBody List<StudentPaper> studentPapers) throws Exception {
        try {
            if(studentPapers.size()==0)
                throw new Exception("请选择学生");
            for (StudentPaper studentPaper : studentPapers) {
                paperService.acceptStudent(studentPaper.getPno(),studentPaper.getSno());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return Result.succ("接受学生成功");
    }

    @PostMapping("/refuse_student")
    public Result refuse_student(@RequestBody List<StudentPaper> studentPapers) throws Exception {
        try {
            for (StudentPaper studentPaper : studentPapers) {
                paperService.refuseStudent(studentPaper.getPno(),studentPaper.getSno(),studentPaper.getRefuseReason());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return Result.succ("拒绝学生成功");
    }

    @PostMapping("re_check_paper")
    public Result re_check_paper(@RequestBody Paper paper) throws Exception {
        try {
            paperService.reCheckPaper(paper);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return Result.succ("重新审核成功");
    }

    @GetMapping("/get_history_paper")
    public Result get_history_paper(@RequestParam("tno") String tno,
                                    @RequestParam(required = false,value = "title") String title,
                                    @RequestParam(required = false,value = "type") String type,
                                    @RequestParam(required = false,value = "studentName") String studentname,
                                    @RequestParam(required = false,value = "schoolyear") Integer schoolyear,
                                    @RequestParam(required = false,value = "acceptStatus") String accept_status){
        return Result.succ(paperService.GetHistoryPaperByTno(tno,title,type,studentname,schoolyear,accept_status));
    }




}

