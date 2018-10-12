package edu.free.magpie.common.permission;

import org.springframework.stereotype.Component;

@Component
public class SampleAuthResourceDetails implements IAuthResourceDetails {
    private Long id;
    private Long pid;
    private String name;
    private String code;
    private String type;
    private Long orderNum;
    private String icon;
    private String url;
    private String method;
    private String systemName;
    private String moduleName;
    private Integer version;

    public SampleAuthResourceDetails() {
    }

    public SampleAuthResourceDetails(String name, String code, String type, Long orderNum, String icon, String url, String method) {
        this.name = name;
        this.code = code;
        this.type = type;
        this.orderNum = orderNum;
        this.icon = icon;
        this.url = url;
        this.method = method;
    }

    public SampleAuthResourceDetails(Long id, Long pid, String name, String code, String type, Long orderNum, String icon, String url, String method) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.code = code;
        this.type = type;
        this.orderNum = orderNum;
        this.icon = icon;
        this.url = url;
        this.method = method;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
