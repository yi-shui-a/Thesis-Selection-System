package duan.thesis_select_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.thesis_select_system.entity.SelectDeadline;
import duan.thesis_select_system.mapper.SelectDeadlineMapper;
import duan.thesis_select_system.service.ISelectDeadlineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duanyhui
 * @since 2023-02-04
 */
@Service
public class SelectDeadlineServiceImpl extends ServiceImpl<SelectDeadlineMapper, SelectDeadline> implements ISelectDeadlineService {

    @Autowired
    private SelectDeadlineMapper selectDeadlineMapper;
    public void updateSelectDeadline(String deadline) {
        LambdaQueryWrapper<SelectDeadline> wrapper = new LambdaQueryWrapper<>();
        SelectDeadline selectDeadline = selectDeadlineMapper.selectOne(null);
        SelectDeadline temp = selectDeadline;
        wrapper.eq(SelectDeadline::getSelectDeadline,selectDeadline.getSelectDeadline()); //这里的selectDeadline是上面的selectDeadline
        selectDeadlineMapper.delete(wrapper);//把原来的删除
        try {
            SelectDeadline selectDeadline1 = new SelectDeadline();
            selectDeadline1.setSelectDeadline(LocalDate.parse(deadline));
            selectDeadlineMapper.insert(selectDeadline1);//插入新的
        }
        catch (Exception e){
            selectDeadlineMapper.insert(temp);//如果出错了，就插入原来的
            throw new RuntimeException("日期格式错误");
        }

    }
}
