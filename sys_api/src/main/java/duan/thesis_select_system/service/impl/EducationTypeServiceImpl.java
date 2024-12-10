package duan.thesis_select_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.thesis_select_system.entity.EducationType;
import duan.thesis_select_system.mapper.EducationTypeMapper;
import duan.thesis_select_system.service.IEducationTypeService;
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
public class EducationTypeServiceImpl extends ServiceImpl<EducationTypeMapper, EducationType> implements IEducationTypeService {

    @Autowired
    private EducationTypeMapper educationTypeMapper;
    public List<EducationType> getall() {
        return educationTypeMapper.selectList(null);
    }

    public void addEducationType(String type) {
        LambdaQueryWrapper<EducationType> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EducationType::getEducation,type);
        if(educationTypeMapper.selectOne(wrapper)!=null){
            throw new RuntimeException("该类型已存在");
        }
        EducationType educationType = new EducationType();
        educationType.setEducation(type);
        educationTypeMapper.insert(educationType);
    }

    public void deleteEducationType(String type) {
        LambdaQueryWrapper<EducationType> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EducationType::getEducation,type);
        if(educationTypeMapper.selectOne(wrapper)==null){
            throw new RuntimeException("该类型不存在");
        }
        educationTypeMapper.delete(wrapper);
    }
}
