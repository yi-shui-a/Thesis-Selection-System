package duan.thesis_select_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.thesis_select_system.entity.Admin;
import duan.thesis_select_system.mapper.AdminMapper;
import duan.thesis_select_system.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import duan.thesis_select_system.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duanyhui
 * @since 2023-02-04
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;
    public Admin GetInfoByUid(String uid) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getUid, uid);
        Admin admin = adminMapper.selectOne(wrapper);
        admin.setPassword(null);
        return admin;

    }

    public void updateInfo(Admin admin) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getUid, admin.getUid());
        adminMapper.update(admin, wrapper);
    }

    public void updatePassword(String uid, String password) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getUid, uid);
        Admin admin = new Admin();
        admin.setPassword(HashUtils.getBC(password));
        adminMapper.update(admin, wrapper);
    }
}
