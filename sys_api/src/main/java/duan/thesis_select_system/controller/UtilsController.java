package duan.thesis_select_system.controller;

import duan.thesis_select_system.common.Result;
import duan.thesis_select_system.entity.Student;
import duan.thesis_select_system.entity.Teacher;
import duan.thesis_select_system.entity.vo.Teacher_vo;
import duan.thesis_select_system.mapper.SelectDeadlineMapper;
import duan.thesis_select_system.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典设置api
 * @author duanyhui
 * @since 2023-02-04
 */
@RestController
@RequestMapping("/utils")
public class UtilsController {
    @Autowired
    private TitleTypeServiceImpl titleTypeService;
    @Autowired
    private EducationTypeServiceImpl educationTypeService;
    @Autowired
    private TeacherTitleServiceImpl teacherTitleService;
    @Autowired
    private TeacherServiceImpl teacherService;
    @Autowired
    private StudentPaperServiceImpl studentPaperService;
    @Autowired
    private SchoolyearServiceImpl schoolyearService;
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private SelectDeadlineMapper selectDeadlineMapper;
    @Autowired
    private SelectDeadlineServiceImpl selectDeadlineService;
    @Autowired
    private PaperServiceImpl paperService;
    @GetMapping("/get_title_type")
    public Result titleType(){
        return Result.succ(titleTypeService.getall());
    }
    @PostMapping("/add_title_type")
    public Result addTitleType(String type){
        titleTypeService.addTitleType(type);
        return Result.succ("添加成功");
    }
    @PostMapping("/delete_title_type")
    public Result deleteTitleType(String type){
        titleTypeService.deleteTitleType(type);
        return Result.succ("删除成功");
    }

    @GetMapping("/get_education_list")
    public Result educationList(){
        return Result.succ(educationTypeService.getall());
    }
    @PostMapping("/add_education")
    public Result addEducationType(String type){
        educationTypeService.addEducationType(type);
        return Result.succ("添加成功");
    }
    @PostMapping("/delete_education")
    public Result deleteEducationType(String type){
        educationTypeService.deleteEducationType(type);
        return Result.succ("删除成功");
    }
    @GetMapping("/get_title_list")
    public Result titleList(){
        return Result.succ(teacherTitleService.getall());
    }
    @PostMapping("/add_title")
    public Result addTitle(String type){
        teacherTitleService.addTitle(type);
        return Result.succ("添加成功");
    }
    @PostMapping("/delete_title")
    public Result deleteTitle(String type){
        teacherTitleService.deleteTitle(type);
        return Result.succ("删除成功");
    }

    @GetMapping("/get_schoolyear_list")
    public Result schoolyearList(){
        return Result.succ(schoolyearService.getall());
    }
    @PostMapping("/add_schoolyear")
    public Result addSchoolyear(Integer schoolyear){
        schoolyearService.addSchoolyear(schoolyear);
        return Result.succ("添加成功");
    }
    @PostMapping("/delete_schoolyear")
    public Result deleteSchoolyear(Integer schoolyear){
        schoolyearService.deleteSchoolyear(schoolyear);
        return Result.succ("删除成功");
    }

    @GetMapping("/get_teacher_list")
    public Result teacherList(){
        return Result.succ(teacherService.getlist());
    }

    @GetMapping("/get_teacher_student_list")
    public Result teacherStudentList(String tno){
        return Result.succ(teacherService.getTeacherStudentList(tno));
    }

    @PostMapping("/update_can_teach_num")
    public Result updateCanTeachNum(@RequestBody List<Teacher_vo> teacher_vos){
        for(Teacher_vo teacher_vo:teacher_vos){
            teacherService.updateCanTeachNum(teacher_vo);
        }
        return Result.succ("更新成功");
    }

    @GetMapping("/get_student_list")
    public Result studentList(){
        return Result.succ(studentService.getlist());
    }

    @PostMapping("/update_can_select_num")
    public Result updateCanSelectNum(@RequestBody List<Student> students){
        for(Student student:students){
            studentService.updateCanSelectNum(student);
        }
        return Result.succ("更新成功");
    }

    @PostMapping("/reset_student")
    public Result resetStudent(@RequestParam("sno") String sno){
        studentService.resetStudent(sno);
        return Result.succ("重置密码为123456");
    }
    @PostMapping("/reset_teacher")
    public Result resetTeacher(@RequestParam("tno") String tno){
        teacherService.resetTeacher(tno);
        return Result.succ("重置密码为123456");
    }

    @GetMapping("/get_select_deadline")
    public Result getSelectDeadline(){
        return Result.succ(selectDeadlineMapper.selectOne(null));
    }
    @PostMapping("/update_select_deadline")
    public Result updateSelectDeadline(@RequestParam("selectDeadline") String deadline){
        selectDeadlineService.updateSelectDeadline(deadline);
        return Result.succ("更新成功");
    }


    @GetMapping("/get_paper_by_pno")
    public Result getPaperByPno(@RequestParam("pno") String pno){
        return Result.succ(paperService.getPaperByPno(pno));
    }

    @PostMapping("/add_student")
    public Result addStudent(@RequestBody Student student){
        studentService.addStudent(student);
        return Result.succ("添加成功");
    }
    @PostMapping("/add_teacher")
    public Result addTeacher(@RequestBody Teacher teacher){
        teacherService.addTeacher(teacher);
        return Result.succ("添加成功");
    }
    @PostMapping("/update_can_teach_num_piliang")
    public Result updateCanTeachNumPiliang(@RequestBody List<Teacher_vo> teachers){
        for(Teacher_vo teacher:teachers){
            teacherService.updateCanTeachNumPiliang(teacher);
        }
        return Result.succ("更新成功");
    }





}
