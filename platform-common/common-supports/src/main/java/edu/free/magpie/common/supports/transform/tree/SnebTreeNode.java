package edu.free.magpie.common.supports.transform.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义的树节点
 */
public class SnebTreeNode {
    // 节点携带的值
    private Map<String, Object> tag;

    // 儿子节点
    private List<SnebTreeNode> nodes;

    /** 节点信息键值对,该属性不会为空指针,但可能没有键值对存在[就是map.size()==0] */
    public Map<String, Object> getTag() {
        if (tag == null)
            tag = new HashMap<>();
        return tag;
    }

    public void setTag(Map<String, Object> tag) {
        this.tag = tag;
    }

    /** 获得所有子节点,该属性不会为空指针,但可能没有集合元素存在
     */
    public List<SnebTreeNode> getNodes() {
        if (nodes == null)
            nodes = new ArrayList<>();
        return nodes;
    }

    public void setNodes(List<SnebTreeNode> nodes) {
        this.nodes = nodes;
    }

    public SnebTreeNode(Map<String, Object> tag, List<SnebTreeNode> nodes) {
        super();
        this.tag = tag;
        this.nodes = nodes;
    }

    public SnebTreeNode() {
        super();
    }

    public SnebTreeNode(Map<String, Object> tag) {
        super();
        this.tag = tag;
    }

    /**
     * <pre>
     * 		将当前TreeNode解析为JSON字串 构造的目标格式示例:
     *        {
     * 			id:1,
     * 			name:'手机',
     * 			nodes:[
     *                {id:11,name:'诺基亚'},
     *                {id:12,name:'三星',nodes:[
     *                    {id:121,name:'I9000(联通版)'},
     *                    {id:122,name:'I9000(移动版)'},
     *                    {id:123,name:'Galaxy Naos'}
     * 				]},
     *                {id:13,name:'索爱'}
     * 			]
     *        }
     * </pre>
     */
    private String parseTreeNode(SnebTreeNode node) {
        if (node == null)
            throw new RuntimeException("节点不能为空");
        StringBuilder nodeBuilder = new StringBuilder("{");// JSON对象开始
        // 把Map中的键值对构造成json对象属性
        for (Map.Entry<String, Object> kvp : node.getTag().entrySet()) {
            if (kvp.getValue() == null || kvp.getValue().getClass() == String.class)
                nodeBuilder.append(kvp.getKey() + ":\"" + kvp.getValue() + "\",");
            else
                nodeBuilder.append(kvp.getKey() + ":" + kvp.getValue() + ",");
        }

        // 构造子节点
        nodeBuilder.append("nodes:["); // 子节点开始
        for (SnebTreeNode cnode : node.getNodes()) {
            if (cnode == null)
                continue;
            nodeBuilder.append(parseTreeNode(cnode) + ",");// 递归调用ParseTreeNode返回一个完整的JSON字串:"{属性1:value,属性2:value}"
        }
        if (nodeBuilder.charAt(nodeBuilder.length() - 1) == ',')// 去掉末尾逗号
            nodeBuilder.deleteCharAt(nodeBuilder.length() - 1);
        nodeBuilder.append("]"); // 子节点结束

        nodeBuilder.append("}"); // JSON对象结束

        return nodeBuilder.toString();
    }

    public String toJson() {
        return parseTreeNode(this);
    }
}