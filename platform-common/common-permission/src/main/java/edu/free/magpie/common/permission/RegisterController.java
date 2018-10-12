package edu.free.magpie.common.permission;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * project: sneb
 * Description： 注册到平台的客户端，提供给服务端访问的controller，
 *
 * @author: ryan
 * @create: Created in 2018/8/15 14:07
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/8/15
 * Version: 0.0.1
 * Modified By:
 */
@Controller
@RequestMapping("/sneb/client")
public class RegisterController {

    /**
     * 提供给服务平台的api
     *
     * @return
     */
    @RequestMapping("/api")
    @ResponseBody
    public ResponseEntity clientInfo() {
        return ResponseEntity.ok(SnebRegisterContext.getAuthRegisterApiVo());
    }

    /**
     * 提供给服务平台的菜单
     *
     * @return
     */
    @RequestMapping("/menu")
    @ResponseBody
    public ResponseEntity menuInfo() throws IOException {
        return ResponseEntity.ok(SnebRegisterContext.getAuthRegisterApiVo());
    }

    /**
     * 提供给服务平台的客户端信息
     *
     * @return
     */
    @RequestMapping("/registInfo")
    @ResponseBody
    public List<IAuthResourceDetails> registInfo() throws IOException {
        List<IAuthResourceDetails> iAuthResourceDetails = AuthResourceContext.getAuthResourceDetailsList();
        return iAuthResourceDetails;
    }
}
