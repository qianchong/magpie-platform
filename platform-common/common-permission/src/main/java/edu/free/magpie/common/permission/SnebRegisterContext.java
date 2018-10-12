package edu.free.magpie.common.permission;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * project: sneb
 * Description： 用户注册到平台eureka服务需要的注册信息，
 *
 * @author: ryan
 * @create: Created in 2018/8/14 15:04
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/8/14
 * Version: 0.0.1
 * Modified By:
 */
public class SnebRegisterContext {
    /**
     * 索引文件扩展后名
     */
    @Value("${eureka.instance.metadata-map.regist-menu:false}")
    private static boolean registMenu = false;

    private static SnebRegistInfoVO snebRegistInfoVO = new SnebRegistInfoVO();

    public static SnebRegisterApiVO getAuthRegisterApiVo() {
        return snebRegistInfoVO.getRegisterApiVO();
    }

    public static void addApi(String controlerName, String methodType, String apiName, String methodUrl) {
        getAuthRegisterApiVo().addApi(controlerName, methodType, apiName, methodUrl);
    }

    /**
     * 返回平台注册提供的所有资源，例如提供给平台的接口等。
     *
     * @return
     */
    public static SnebRegistInfoVO getRegistClientInfo() {
        createMenu();
        return snebRegistInfoVO;
    }

    /**
     * 返回平台注册提供的所有资源，例如提供给平台的接口等。
     *
     * @return
     */
    public static SnebRegisterMenuVO getMenu() {
        return createMenu();
    }

    private static SnebRegisterMenuVO createMenu() {
        if (!registMenu) {
            return null;
        }
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource("menu.json");
        File file = new File(url.getFile());
        String menuJsonStr = "";
        if (file.exists()) {
            try {
                menuJsonStr = FileUtil.readAsString(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        SnebRegisterMenuVO snebRegisterMenuVO = JSONObject.parseObject(menuJsonStr, SnebRegisterMenuVO.class);
        snebRegistInfoVO.setRegisterMenuVO(snebRegisterMenuVO);
        return snebRegisterMenuVO;
    }
}
