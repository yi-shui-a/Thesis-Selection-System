package duan.thesis_select_system.mapper;

import duan.thesis_select_system.entity.SelectDeadline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author duanyhui
 * @since 2023-02-04
 */
@Mapper
public interface SelectDeadlineMapper extends BaseMapper<SelectDeadline> {

    void updateSelectDeadline(String deadline);
}
