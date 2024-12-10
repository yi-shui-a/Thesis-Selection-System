package duan.thesis_select_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.thesis_select_system.entity.TeacherTitle;
import duan.thesis_select_system.mapper.TeacherTitleMapper;
import duan.thesis_select_system.service.ITeacherTitleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class TeacherTitleServiceImpl extends ServiceImpl<TeacherTitleMapper, TeacherTitle> implements ITeacherTitleService {

    @Autowired
    private TeacherTitleMapper teacherTitleMapper;
    public List<TeacherTitle> getall() {
        return teacherTitleMapper.selectList(null);
    }

    public void addTitle(String type) {
        LambdaQueryWrapper<TeacherTitle> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TeacherTitle::getTitle,type);
        if(teacherTitleMapper.selectOne(wrapper)!=null){
            throw new RuntimeException("该类型已存在");
        }
        TeacherTitle teacherTitle = new TeacherTitle();
        teacherTitle.setTitle(type);
        teacherTitleMapper.insert(teacherTitle);
    }

    public void deleteTitle(String type) {
        LambdaQueryWrapper<TeacherTitle> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TeacherTitle::getTitle,type);
        if(teacherTitleMapper.selectOne(wrapper)==null){
            throw new RuntimeException("该类型不存在");
        }
        teacherTitleMapper.delete(wrapper);
    }
}
