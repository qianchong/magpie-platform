package edu.free.magpie.common.permission;

public class SnebRegistInfoVO {
    private String appId;
    private String serverType;
    private String appName;
    private SnebRegisterApiVO registerApiVO = new SnebRegisterApiVO();
    private SnebRegisterMenuVO registerMenuVO = new SnebRegisterMenuVO();

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public SnebRegisterApiVO getRegisterApiVO() {
        return registerApiVO;
    }

    public void setRegisterApiVO(SnebRegisterApiVO registerApiVO) {
        this.registerApiVO = registerApiVO;
    }

    public SnebRegisterMenuVO getRegisterMenuVO() {
        return registerMenuVO;
    }

    public void setRegisterMenuVO(SnebRegisterMenuVO registerMenuVO) {
        this.registerMenuVO = registerMenuVO;
    }
}
