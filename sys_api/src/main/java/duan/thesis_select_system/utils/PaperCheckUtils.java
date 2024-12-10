package duan.thesis_select_system.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.thesis_select_system.entity.*;
import duan.thesis_select_system.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.time.LocalDate;
import java.util.List;

import static duan.thesis_select_system.constant.PaperConstant.HAS_SELECT;
@Component
public class PaperCheckUtils{

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherStudentMapper teacherStudentMapper;
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private StudentPaperMapper studentPaperMapper;
    @Autowired
    private SelectDeadlineMapper selectDeadlineMapper;

    public static PaperCheckUtils paperCheckUtils;




    @PostConstruct
    public void init(){
        paperCheckUtils = this;
    }

    /**
     * 该方法用于判断该老师是否可以指导该学生
     * @param pno
     * @param sno
     * @return
     */
    public static boolean CanTeach(Integer pno, String sno) {
        LambdaQueryWrapper<Student> wrapper1 = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Paper> wrapper2 =new LambdaQueryWrapper<>();
        LambdaQueryWrapper<TeacherStudent> wrapper3 = new LambdaQueryWrapper<>();
        wrapper1.eq(Student::getSno,sno);
        Integer schoolyear = paperCheckUtils.studentMapper.selectOne(wrapper1).getSchoolyear();
        wrapper2.eq(Paper::getPno,pno);
        String tno = paperCheckUtils.paperMapper.selectOne(wrapper2).getTno();
        wrapper3.eq(TeacherStudent::getTno,tno).
                 eq(TeacherStudent::getSchoolyear,schoolyear);
        TeacherStudent teacherStudent = paperCheckUtils.teacherStudentMapper.selectOne(wrapper3);
        if(teacherStudent.getCanTeachNum()>teacherStudent.getHasTeachNum())
            return true;
        return false;
    }

    /**
     * 该方法用于判断该毕业设计题目是否已经被选
     * @param pno
     * @return
     */
    public static boolean PaperIsSelect(Integer pno) {
        LambdaQueryWrapper<Paper> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Paper::getPno,pno);
        Paper paper = paperCheckUtils.paperMapper.selectOne(wrapper);
        return paper.getSelectStatus().equals(HAS_SELECT);
    }

    /**
     * 该方法用于判断该学生可选题目数是否大于已选题目数
     * @param sno
     * @return
     */
    public static boolean CanStudentSelect(String sno){
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getSno,sno);
        Student student = paperCheckUtils.studentMapper.selectOne(wrapper);
        return student.getAbleTopicNum()>student.getSelectedTopicNum();
    }

    /**
     * 该方法用于判断该学生是否已经选择了该题目并未通过审核
     * @param pno
     * @param sno
     * @return
     */
    public static boolean HasSameNotCheckPaper(Integer pno,String sno){
        LambdaQueryWrapper<StudentPaper> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentPaper::getSno,sno).
                eq(StudentPaper::getAcceptStatus,"NOT_CHECK").
                eq(StudentPaper::getPno,pno);
        return paperCheckUtils.studentPaperMapper.selectCount(wrapper)>0;
    }

    /**
     * 该方法用于判断是否存在该题目
     * @param pno
     * @return
     */
    public static boolean HasPaper(Integer pno) {
        LambdaQueryWrapper<Paper> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Paper::getPno,pno);
        return paperCheckUtils.paperMapper.selectCount(wrapper)>0;
    }

    /**
     * 该方法用于判断该学生是否已经选择了该题目并通过了审核
     * @param pno
     * @param sno
     * @return
     */
    public static boolean HasSameAcceptPaper(Integer pno, String sno) {
        LambdaQueryWrapper<StudentPaper> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentPaper::getSno,sno).
                eq(StudentPaper::getAcceptStatus,"ACCEPT").
                eq(StudentPaper::getPno,pno);
        return paperCheckUtils.studentPaperMapper.selectCount(wrapper)>0;
    }

    /**
     * 该方法用于判断该学生是否已经选择了该题目
     * @param sno
     * @param pno
     * @return
     */
    public static boolean HasSelectPaper(String sno, Integer pno) {
        LambdaQueryWrapper<StudentPaper> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentPaper::getSno,sno).
                eq(StudentPaper::getPno,pno);
        List<StudentPaper> studentPaper = paperCheckUtils.studentPaperMapper.selectList(wrapper);
        if(studentPaper==null){
            return false;
        }
        return true;
    }

    public static boolean InSelectDeadline(){
        LambdaQueryWrapper<SelectDeadline> wrapper = new LambdaQueryWrapper<>();
        SelectDeadline selectDeadline = paperCheckUtils.selectDeadlineMapper.selectOne(null);
        LocalDate date = selectDeadline.getSelectDeadline();
        if(LocalDate.now().isAfter(date)){
            return false;
        }
        return true;
    }
    public static boolean IsRefusePaper(String sno, Integer pno) {
        LambdaQueryWrapper<StudentPaper> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentPaper::getSno,sno).
                eq(StudentPaper::getPno,pno).
                eq(StudentPaper::getAcceptStatus,"REFUSE");
        return paperCheckUtils.studentPaperMapper.selectCount(wrapper)>0;
    }

}
