package duan.thesis_select_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.thesis_select_system.entity.Student;
import duan.thesis_select_system.entity.StudentPaper;
import duan.thesis_select_system.entity.vo.Paper_Student_vo;
import duan.thesis_select_system.mapper.StudentMapper;
import duan.thesis_select_system.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import duan.thesis_select_system.utils.HashUtils;
import duan.thesis_select_system.utils.PaperCheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static duan.thesis_select_system.utils.voHandle.Paper_Student_voHandle;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duanyhui
 * @since 2023-02-01
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;
    public Student GetInfoBySno(String sno) {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getSno,sno);
        Student student = studentMapper.selectOne(wrapper);
        student.setPassword(null);
        return student;
    }

    public void updateInfo(Student student) {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getSno,student.getSno());
        studentMapper.update(student,wrapper);
    }

    public void updatePassword(String sno, String password) {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getSno,sno);
        Student student = new Student();
        student.setPassword(HashUtils.getBC(password));
        studentMapper.update(student,wrapper);
    }

    public List<Paper_Student_vo> GetAblePaper(String sno) {
        return studentMapper.GetAblePaper(sno);
    }

    public List<Paper_Student_vo> GetMyPaper(String sno, String acceptStatus) {
        if (acceptStatus==null) {
            acceptStatus="";
        }
        List<Paper_Student_vo> student_vos = studentMapper.GetMyPaper(sno,acceptStatus);
        return Paper_Student_voHandle(student_vos);
    }

    public void SelectPaper(String sno, Integer pno) {
        AbleSelectPaper(sno,pno);
        studentMapper.SelectPaper(sno,pno);
    }

    private void AbleSelectPaper(String sno, Integer pno) {
        if(!PaperCheckUtils.InSelectDeadline())
            throw new RuntimeException("不在选题时间");
        if(!PaperCheckUtils.HasPaper(pno))
            throw new RuntimeException("该题目不存在");
        if(!PaperCheckUtils.CanStudentSelect(sno))
            throw new RuntimeException("超过选题数量限制");
        if(!PaperCheckUtils.CanTeach(pno, sno))
            throw new RuntimeException("该教师指导人数已满");
        if(PaperCheckUtils.HasSameNotCheckPaper(pno,sno))
            throw new RuntimeException("已有相同题目未审核");
        if(PaperCheckUtils.HasSameAcceptPaper(pno,sno))
            throw new RuntimeException("已有相同题目已通过");
    }

    public void CancelPaper(String sno, Integer pno) {
        AbleCancelPaper(sno,pno);
        studentMapper.CancelPaper(sno,pno);
    }

    private void AbleCancelPaper(String sno, Integer pno) {
        if(!PaperCheckUtils.HasPaper(pno))
            throw new RuntimeException("该题目不存在");
        if(!PaperCheckUtils.HasSelectPaper(sno,pno))
            throw new RuntimeException("未选该题目");
        if(PaperCheckUtils.HasSameAcceptPaper(pno,sno))
            throw new RuntimeException("已通过审核，无法取消");
    }

    public void updateCanSelectNum(Student student) {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getSno,student.getSno());
        Student temp = studentMapper.selectOne(wrapper);
        if(temp.getSelectedTopicNum()>student.getAbleTopicNum())
            throw new RuntimeException("已选题目数量大于可选题目数量");
        studentMapper.update(student,wrapper);
    }

    public List<Student> getlist() {
        List<Student> students = studentMapper.selectList(null);
        for (Student student : students) {
            student.setPassword(null);
        }
        return students;

    }

    public void resetStudent(String sno) {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getSno,sno);
        Student student = new Student();
        student.setPassword(HashUtils.getBC("123456"));
        studentMapper.update(student,wrapper);
    }

    public void ReSelectPaper(String sno, Integer pno) {
        AbleReSelectPaper(sno,pno);
        studentMapper.ReSelectPaper(sno,pno);

    }

    private void AbleReSelectPaper(String sno, Integer pno) {
        if(!PaperCheckUtils.InSelectDeadline())
            throw new RuntimeException("不在选题时间");
        if(!PaperCheckUtils.HasPaper(pno))
            throw new RuntimeException("该题目不存在");
        if(!PaperCheckUtils.CanStudentSelect(sno))
            throw new RuntimeException("超过选题数量限制");
        if(!PaperCheckUtils.CanTeach(pno, sno))
            throw new RuntimeException("该教师指导人数已满");
        if(PaperCheckUtils.HasSameNotCheckPaper(pno,sno))
            throw new RuntimeException("已有相同题目未审核");
        if(!PaperCheckUtils.IsRefusePaper(sno,pno))
            throw new RuntimeException("该题目未被拒绝");
    }

    public void addStudent(Student student) {
        try {
            student.setAbleTopicNum(1);
            student.setSelectedTopicNum(0);
            student.setPassword(HashUtils.getBC("123456"));
            studentMapper.insert(student);
        }
        catch (Exception e) {
            throw new RuntimeException("学号重复");
        }


    }
    }
