package duan.thesis_select_system.controller;


import duan.thesis_select_system.common.Result;
import duan.thesis_select_system.entity.Admin;
import duan.thesis_select_system.entity.Paper;
import duan.thesis_select_system.service.impl.AdminServiceImpl;
import duan.thesis_select_system.service.impl.PaperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author duanyhui
 * @since 2023-02-04
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private PaperServiceImpl paperService;

    @GetMapping("/get_myinfo")
    public Result get_myinfo(@RequestParam("uid") String uid){
        return Result.succ(adminService.GetInfoByUid(uid));
    }
    @PostMapping("/update_myinfo")
    public Result update_myinfo(@RequestBody Admin admin){
        adminService.updateInfo(admin);
        return Result.succ("修改成功");
    }
    @PostMapping("/update_password")
    public Result update_password(@RequestParam("uid") String uid,@RequestParam("password") String password){
        adminService.updatePassword(uid,password);
        return Result.succ("修改成功");
    }

    @GetMapping("/get_all_paper")
    public Result get_all_paper(@RequestParam(required = false,value = "teacherName") String teacherName,
                                @RequestParam(required = false,value = "title") String title,
                                @RequestParam(required = false,value = "checkStatus") String checkStatus,
                                @RequestParam(required = false,value = "type") String type,
                                @RequestParam(required = false,value = "schoolyear") Integer schoolyear){
        return Result.succ(paperService.GetAllPaper(teacherName,title,checkStatus,type,schoolyear));
    }

    @PostMapping("/check_paper")
    public Result check_paper(@RequestBody List<Paper> papers){
        for(Paper paper:papers){
            paperService.AdminCheckPaper(paper.getPno());
        }
        return Result.succ("审核成功");
    }

    @PostMapping("/refuse_paper")
    public Result refuse_paper(@RequestBody List<Paper> papers){
        for(Paper paper: papers){
            paperService.AdminRefusePaper(paper);
        }
        return Result.succ("审核选题成功");
    }




}

