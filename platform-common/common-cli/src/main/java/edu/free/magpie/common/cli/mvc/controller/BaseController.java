
package edu.free.magpie.common.cli.mvc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class BaseController {

    @Value("${html.layout.dir:views}")
    public String htmlPageDir;

    @GetMapping(value = "/ping")
    @ResponseBody
    public String ping() {
        return "pong";
    }

    protected static final String ADD = "Add";
    protected static final String INDEX = "Index";
    protected static final String SUCCESS = "success";

    /** html page dir namespace, 依据类名*/
    protected String prefix = "";

    /**
     * 请求/WEB-INF/下的视图文件
     */
    @RequestMapping(value = "/toUrl")
    public String toUrl(@RequestParam("toUrl") String toUrl) {
        return "/" + htmlPageDir + "/" + toUrl;
    }

    public void setAttribute(String key, Object obj) {
        this.getRequest().setAttribute(key, obj);
    }

    public Map<String, String[]> getParameterMap() {
        return getRequest().getParameterMap();
    }

    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    public HttpServletResponse getResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }

    @RequestMapping("/index")
    public String index() {
        return getPrefix(INDEX);
    }

    public String getPrefix(String suffix) {
        return "/" + htmlPageDir + "/" + prefix + "/" + prefix + suffix;
    }

    /**
     * 获取session
     *
     * @return
     */
    public HttpSession getSession() {
        HttpSession session = this.getRequest().getSession();
        return session;
    }

    /**
     * 获取ServletContext
     *
     * @return
     */
    public ServletContext getServletContent() {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();
        return servletContext;
    }

    /**
     * 获取ModelAndView
     *
     * @return
     */
    public ModelAndView getModelAndView() {
        return new ModelAndView();
    }

    public ModelAndView get404ModelAndView() {
        ModelAndView view = new ModelAndView();
        view.setViewName("404");
        return view;
    }

    /**
     * 获取ip
     *
     * @return
     */
    public String getRemortIP() {
        HttpServletRequest request = this.getRequest();
        String ip = "";
        if (request.getHeader("x-forwarded-for") == null) {
            ip = request.getRemoteAddr();
        } else {
            ip = request.getHeader("x-forwarded-for");
        }
        return ip;
    }

    /**
     * 获取port
     *
     * @return
     */
    public int getPort() {
        return this.getRequest().getServerPort();
    }

    /**
     * 获取ip
     *
     * @return
     */
    public String getIpAddr() {
        HttpServletRequest request = this.getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
