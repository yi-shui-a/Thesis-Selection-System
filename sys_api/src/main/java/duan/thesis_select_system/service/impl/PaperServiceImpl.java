package duan.thesis_select_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.thesis_select_system.entity.Paper;
import duan.thesis_select_system.entity.vo.Paper_Admin_vo;
import duan.thesis_select_system.entity.vo.Paper_teacher_vo;
import duan.thesis_select_system.mapper.PaperMapper;
import duan.thesis_select_system.service.IPaperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import duan.thesis_select_system.utils.PaperCheckUtils;
import duan.thesis_select_system.utils.voHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static duan.thesis_select_system.constant.PaperConstant.*;
import static duan.thesis_select_system.utils.PaperCheckUtils.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duanyhui
 * @since 2023-01-27
 */
@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements IPaperService {
    @Autowired
    private PaperMapper paperMapper;

    public void create(Paper paper) throws Exception {
        try {
            paperMapper.insert(paper);
        }
        catch (Exception e){
            throw new Exception("创建毕业设计题目失败");
        }
    }

    public boolean ABLE_DELETE(Integer pno){
        Paper paper = paperMapper.selectById(pno);
        if(paper==null){
            throw new RuntimeException("该毕业设计题目不存在");
        }
        if(paper.getSelectStatus()==HAS_SELECT){
            return false;
        }
        return true;
    }

    public void DeleteByPno(Integer pno) {
        if(ABLE_DELETE(pno)){
            try {
                paperMapper.deleteById(pno);
            }
            catch (Exception e){
                throw new RuntimeException("该题目已被选，无法删除");
            }
        }
        else {
            throw new RuntimeException("该题目已被选，无法删除");
        }

    }

    public List<Paper> GetPaperByTno(String tno) {
        LambdaQueryWrapper<Paper> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Paper::getTno,tno);
        List<Paper> papers = paperMapper.selectList(wrapper);
        //方便前端处理
        for(Paper paper:papers){
            if(paper.getCheckStatus().equals(UNCHECKED))
                paper.setCheckStatus("未审核");
            else
                paper.setCheckStatus("已审核");
            if(paper.getSelectStatus().equals(NOT_SELECT))
                paper.setSelectStatus("未被选");
            else
                paper.setSelectStatus("已被选");
        }
        return papers;
    }

    public List<Paper_teacher_vo> GetPaper(String tno,
                                           String title,
                                           String select_status,
                                           String check_status,
                                           String type,
                                           String studentname,
                                           Integer schoolyear,
                                           String accept_status
                                           ) {
        List<Paper_teacher_vo> paper_teacher_vos = new ArrayList<>();
        if(tno==null)
            tno= "";
        if(title==null)
            title= "";
        if(select_status==null)
            select_status= "";
        if(check_status==null)
            check_status= "";
        if(type==null)
            type= "";
        if(accept_status==null)
            accept_status= "";
        if(schoolyear==null)
            schoolyear= 0;

        if(studentname==null||studentname.equals("")){
            paper_teacher_vos = paperMapper.GetPaper_no_name(tno,title,select_status,check_status,type,schoolyear,accept_status);
        }
        else paper_teacher_vos = paperMapper.GetPaper(tno, title, select_status, check_status, type, studentname,schoolyear,accept_status);


        return voHandle.Paper_teacher_voHandle(paper_teacher_vos);
    }

    public List<Paper_teacher_vo> GetPaperSelected(String tno, String title, String type, String studentname, Integer schoolyear, String accept_status) {
        if(title==null)
            title= "";
        if(type==null)
            type= "";
        if(studentname==null)
            studentname= "";
        if(schoolyear==null)
            schoolyear= 0;
        List<Paper_teacher_vo> paper_teacher_vos = paperMapper.GetPaperSelected(tno,title,type,studentname,schoolyear,accept_status);
        return voHandle.Paper_teacher_voHandle(paper_teacher_vos);
    }

    public List<Paper_teacher_vo> GetStudentPaper(String tno, String title, String type, String studentname, Integer schoolyear,String select_status) {
        if(title==null)
            title= "";
        if(type==null)
            type= "";
        if(studentname==null)
            studentname= "";
        if(schoolyear==null)
            schoolyear= 0;
        if(select_status==null)
            select_status= "";
        List<Paper_teacher_vo> paper_teacher_vos = paperMapper.GetStudentPaper(tno,title,type,studentname,schoolyear,select_status);
        return voHandle.Paper_teacher_voHandle(paper_teacher_vos);
    }

    public void acceptStudent(Integer pno, String sno) {

        CheckAbleAccept(pno,sno);
        try {
            paperMapper.acceptStudent(pno,sno);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("接受学生失败");
        }

    }

    private void CheckAbleAccept(Integer pno, String sno) {
        PaperCheckUtils paperCheckUtils = new PaperCheckUtils();
        if(!PaperCheckUtils.HasPaper(pno))
            throw new RuntimeException("该题目不存在");
        if(!CanTeach(pno,sno)){
            throw new RuntimeException("超过指导学生上限");
        }
        if(PaperIsSelect(pno)){
            throw new RuntimeException("该题目已被其他学生选择");
        }

//        if(!CanStudentSelect(sno)){
//            throw new RuntimeException("该学生可选题目数已达上限");
//        }
    }


    public void refuseStudent(Integer pno, String sno, String refuseReason) {
        try {
            paperMapper.refuseStudent(pno,sno,refuseReason);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("拒绝学生失败");
        }

    }

    public List<Paper_Admin_vo> GetAllPaper(String teacherName, String title, String checkStatus, String type, Integer schoolyear) {
        if(teacherName==null)
            teacherName= "";
        if(title==null)
            title= "";
        if(checkStatus==null)
            checkStatus= "";
        if(type==null)
            type= "";
        if(schoolyear==null)
            schoolyear= 0;
        List<Paper_Admin_vo> papers = paperMapper.GetAllPaper(teacherName,title,checkStatus,type,schoolyear);
        return voHandle.Paper_Admin_voHandle(papers);
    }

    public void AdminCheckPaper(Integer pno) {
            LambdaQueryWrapper<Paper> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Paper::getPno,pno);
            Paper paper = paperMapper.selectOne(wrapper);
            if(paper==null)
                throw new RuntimeException("该题目不存在");
            if(paper.getCheckStatus().equals(CHECKED))
                throw new RuntimeException("该题目已审核");
            Paper CheckPaper= new Paper();
            CheckPaper.setCheckStatus(CHECKED);
            CheckPaper.setRejectReason("");
            paperMapper.update(CheckPaper,wrapper);
    }

    public void AdminRefusePaper(Paper paper) {
        if(paper.getRejectReason()==null)
            throw new RuntimeException("拒绝理由不能为空");
        LambdaQueryWrapper<Paper> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Paper::getPno,paper.getPno());
        Paper paper1 = paperMapper.selectOne(wrapper);
        if(paper1==null)
            throw new RuntimeException("该题目不存在");
        if(paper1.getCheckStatus().equals(CHECKED))
            throw new RuntimeException("该题目已审核");
        Paper CheckPaper= new Paper();
        CheckPaper.setPno(paper.getPno());
        CheckPaper.setCheckStatus(REFUSE);
        CheckPaper.setRejectReason(paper.getRejectReason());

        paperMapper.update(CheckPaper,wrapper);
    }

    public Paper getPaperByPno(String pno) {
        LambdaQueryWrapper<Paper> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Paper::getPno,pno);
        Paper paper = paperMapper.selectOne(wrapper);
        if(paper==null)
            throw new RuntimeException("该题目不存在");
        return paper;
    }

    public void reCheckPaper(Paper paper) {
        LambdaQueryWrapper<Paper> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Paper::getPno,paper.getPno());
        Paper paper1 = paperMapper.selectOne(wrapper);
        if(paper1==null)
            throw new RuntimeException("该题目不存在");
        if(paper1.getCheckStatus().equals(NOT_CHECK))
            throw new RuntimeException("该题目未审核");
        if(paper1.getCheckStatus().equals(CHECKED))
            throw new RuntimeException("该题目已审核");
        paper.setCheckStatus(NOT_CHECK);

    }

    public List<Paper_teacher_vo> GetHistoryPaperByTno(String tno, String title, String type, String studentname, Integer schoolyear, String accept_status) {
        if(title==null)
            title= "";
        if(type==null)
            type= "";
        if(studentname==null)
            studentname= "";
        if(schoolyear==null)
            schoolyear= 0;
        if(accept_status==null)
            accept_status= "";
        List<Paper_teacher_vo> paper_teacher_vos = paperMapper.GetHistoryPaperByTno(tno,title,type,studentname,schoolyear,accept_status);
        return voHandle.Paper_teacher_voHandle(paper_teacher_vos);
    }
}
