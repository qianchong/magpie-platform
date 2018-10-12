package edu.free.magpie.service.user.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.free.magpie.api.user.dto.LoginUser;
import edu.free.magpie.common.cli.mvc.controller.CrudController;
import edu.free.magpie.service.user.web.entity.SysUserEntity;
import edu.free.magpie.service.user.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sysUser")
public class SysUserController extends CrudController<SysUserEntity, String> {

    protected SysUserController(SysUserService sysUserService) {
        super(sysUserService);
    }

    @Autowired
    private SysUserService sysUserService;

    @Override
    @JsonView(SysUserEntity.UserSimpleView.class)
    @ResponseBody
    public List<SysUserEntity> findAll() {
        return super.findAll();
    }

    /**
     * @param username
     * @return
     */
    @GetMapping(value = "/login")
    @ResponseBody
    public LoginUser loadUserLoginName(@RequestParam String username) {
        return sysUserService.loadUserByUsername(username);
    }
}
