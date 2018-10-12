package edu.free.magpie.common.supports.transform.tree;

public class BuildTreeTestVO {
    private String code;
    private String parentCode;
    private String title;
    private String desc;
    private BuildTreeTestVO parent;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 功能编号
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 父级功能编号
     */
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    /**
     * 功能名称,现在树上的节点文本
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BuildTreeTestVO(String code, String parentCode, String title) {
        super();
        this.code = code;
        this.parentCode = parentCode;
        this.title = title;
    }

    public BuildTreeTestVO(String code, String parentCode, String title, String desc) {
        super();
        this.code = code;
        this.parentCode = parentCode;
        this.title = title;
        this.desc = desc;
    }

    public BuildTreeTestVO() {
        super();
    }

    public BuildTreeTestVO getParent() {
        return parent;
    }

    public void setParent(BuildTreeTestVO parent) {
        this.parent = parent;
    }
}
