package duan.thesis_select_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.thesis_select_system.entity.TitleType;
import duan.thesis_select_system.mapper.TitleTypeMapper;
import duan.thesis_select_system.service.ITitleTypeService;
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
public class TitleTypeServiceImpl extends ServiceImpl<TitleTypeMapper, TitleType> implements ITitleTypeService {

    @Autowired
    private TitleTypeMapper titleTypeMapper;
    public List<TitleType> getall() {
        return titleTypeMapper.selectList(null);
    }

    public void addTitleType(String type) {
        LambdaQueryWrapper<TitleType> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TitleType::getType,type);
        TitleType titleType = titleTypeMapper.selectOne(wrapper);
        if(titleType!=null){
            throw new RuntimeException("该类型已存在");
        }
        TitleType titleType1 = new TitleType();
        titleType1.setType(type);
        titleTypeMapper.insert(titleType1);
    }

    public void deleteTitleType(String type) {
        LambdaQueryWrapper<TitleType> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TitleType::getType,type);
        TitleType titleType = titleTypeMapper.selectOne(wrapper);
        if(titleType==null){
            throw new RuntimeException("该类型不存在");
        }
        titleTypeMapper.delete(wrapper);
    }
}
