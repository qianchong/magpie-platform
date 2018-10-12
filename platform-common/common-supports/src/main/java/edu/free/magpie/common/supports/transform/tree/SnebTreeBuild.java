package edu.free.magpie.common.supports.transform.tree;

import com.alibaba.fastjson.JSONArray;

import java.lang.reflect.Field;
import java.util.*;

public class SnebTreeBuild {

    private static TreeNodeParse getNodeDescrbe(Class classzz, Properties fieldPropertyMap, String... parseFields) throws NoSuchFieldException {
        TreeNodeParse parse = new TreeNodeParse(classzz, fieldPropertyMap, parseFields);
        return parse;
    }

    private static <E> Map<String, SnebTreeNode> buildTreeNodeMap(List<E> entities,
                                                                  TreeNodeParse parse,
                                                                  String key,
                                                                  String parentKey,
                                                                  IParseTreeNodeCallBack parseCallBack)
            throws NoSuchFieldException, IllegalAccessException {

        //将这个集合封装成Map
        Map<String, SnebTreeNode> treeNodeMap = new HashMap<>();

        TreeNodeParse.NodeDescrbe nodeDescrbe = parse.parese(key, parentKey);
        Field field = nodeDescrbe.getField();

        for (E e : entities) {
            SnebTreeNode node = new SnebTreeNode();
            parse.put(node, nodeDescrbe, e);

            //判断回调是否为空
            if (parseCallBack != null)
                parseCallBack.processTreeNode(node);
            treeNodeMap.put(field.get(e).toString(), node);
        }
        return treeNodeMap;
    }

    public static <E> JSONArray buildTree(List<E> entities, String key, String parentKey)
            throws NoSuchFieldException, IllegalAccessException {
        return buildTree(entities, key, parentKey, null);
    }

    public static <E> JSONArray buildTree(List<E> entities, String key, String parentKey, Properties fieldPropertyMap)
            throws NoSuchFieldException, IllegalAccessException {
        return buildTree(entities, key, parentKey, fieldPropertyMap, null);
    }

    public static <E> JSONArray buildTree(List<E> entities, String key, String parentKey, Properties fieldPropertyMap, String... parseFields)
            throws NoSuchFieldException, IllegalAccessException {
        return buildTree(entities, key, parentKey, fieldPropertyMap, null, parseFields);
    }

    public static <E> JSONArray buildTree(List<E> entities, String key, String parentKey, Properties fieldPropertyMap, IParseTreeNodeCallBack parseCallBack, String... parseFields)
            throws NoSuchFieldException, IllegalAccessException {
        if (entities == null || entities.size() == 0 || null == key || "".equals(key) || null == parentKey || "".equals(parentKey))
            throw new RuntimeException("参数不是有效值,分析失败");
        Class classzz = entities.get(0).getClass();
        TreeNodeParse parse = getNodeDescrbe(classzz, fieldPropertyMap, parseFields);
        Map<String, SnebTreeNode> treeNodeMap = buildTreeNodeMap(entities, parse, key, parentKey, parseCallBack);

        TreeNodeParse.NodeDescrbe nodeDescrbe = parse.parese(key, parentKey);
        List<SnebTreeNode> nodes = new ArrayList<>();
        Field field = nodeDescrbe.getField();
        Field parentField = nodeDescrbe.getParentField();

        for (int i = 0; i < entities.size(); i++) {
            E e = entities.get(i);
            //如果当前功能的父级功能编号在treeNodeMap中存在
            String parentFieldValue = parse.getParentFieldValue(parentField, e, nodeDescrbe.getParentKey());
            if (treeNodeMap.containsKey(parentFieldValue + "")) {
                //找出父节点 添加子节点
                treeNodeMap.get(parentFieldValue + "").getNodes().add(treeNodeMap.get(field.get(e) + ""));
                continue;
            }
            //如果当期节点没有父亲节点则说明是根节点
            nodes.add(treeNodeMap.get(field.get(e) + ""));
        }

        StringBuilder jsonBuilder = new StringBuilder("[");
        for (SnebTreeNode treeNode : nodes) {
            jsonBuilder.append(treeNode.toJson() + ",");
        }
        if (jsonBuilder.charAt(jsonBuilder.length() - 1) == ',')
            jsonBuilder.deleteCharAt(jsonBuilder.length() - 1);
        jsonBuilder.append("]");
        return JSONArray.parseArray(jsonBuilder.toString());
    }

    /**
     * 将对象集合解析为json格式的树形脚本
     *
     * @param <E>              集合成员类型
     * @param entities         待解析的集合,必须具备有效值
     * @param key              对象的唯一标识属性,必须具备有效值
     * @param parentKey        父对象的唯一标识属性,必须具备有效值
     * @param fieldPropertyMap java对象属性名到json对象属性名的映射,比如java对象的属性名是code,对应解析后的json对象的属性名是id
     * @param parseCallBack    解析回调函数,用于扩展树节点的属性,有了这个接口给开发这个一个可以自定义树形节点特殊属性的机会
     * @param parseFields      需要解析的字段,没有传递就解析对象的所有属性
     * @return json格式的树形脚本 例如结果如下格式:
     * <pre>
     * 		[
     * 				{id:11,name:'诺基亚'},
     * 				{id:12,name:'三星',nodes:[
     * 					{id:121,name:'I9000(联通版)'},
     * 					{id:122,name:'I9000(移动版)'},
     * 					{id:123,name:'Galaxy Naos'}
     * 				]},
     * 				{id:13,name:'索爱'},
     * 				{id:14,name:'TCL'},
     * 				{id:15,name:'摩托罗拉'}
     * 		]
     * 使用示范如下：
     * 	 * //用一个集合模拟数据表的内容,也就是一下集合的内容完全可以查询自数据库
     * 		List<BuildTreeTestVO> treeList = new ArrayList<BuildTreeTestVO>();
     * 		treeList.add(new BuildTreeTestVO("L01", null, "营销管理","没有"));
     * 		treeList.add(new BuildTreeTestVO("L02",null,"客户管理","没有"));
     * 		treeList.add(new BuildTreeTestVO("L03",null,"服务管理","没有"));
     * 	 	//营销管理的子功能
     * 		treeList.add(new BuildTreeTestVO("L0101","L01","创建销售机会","没有"));
     * 		treeList.add(new BuildTreeTestVO("L0102","L01","指派销售机会","没有"));
     * 		treeList.add(new BuildTreeTestVO("L0103","L01","制定开发计划","没有"));
     * 		treeList.add(new BuildTreeTestVO("L0104","L01","执行开发计划","没有"));
     * 		//指派销售机会的子功能
     * 		treeList.add(new BuildTreeTestVO("L010201","L0102","经理指派","没有"));
     * 		treeList.add(new BuildTreeTestVO("L010202","L0102","主管指派","没有"));
     * 		//客户管理的子功能
     * 		treeList.add(new BuildTreeTestVO("L0201","L02","客户信息管理","没有"));
     * 		treeList.add(new BuildTreeTestVO("L0202","L02","客户流失管理","没有"));
     * 		//服务管理子功能
     * 		treeList.add(new BuildTreeTestVO("L0301","L03","咨询服务管理","没有"));
     * 		treeList.add(new BuildTreeTestVO("L0302","L03","投诉服务管理","没有"));
     * 		treeList.add(new BuildTreeTestVO("L0303","L03","查询服务管理","没有"));
     *
     * 		try {
     * 		Properties p = new Properties();
     * 		p.setProperty("code", "id");//将SysFunction对象里的属性名“code”映射到json对象里的属性名“id”
     * 		p.setProperty("title", "name");//将SysFunction对象里的属性名“title”映射到json对象里的属性名“name”
     * </pre>
     * @throws SecurityException        java反射异常
     * @throws NoSuchFieldException     java反射异常
     * @throws IllegalArgumentException java反射异常
     * @throws IllegalAccessException   java反射异常
     */
    public static <E> List<Map<String, Object>> buildTreeList(List<E> entities, String key, String parentKey, Properties fieldPropertyMap, IParseTreeNodeCallBack parseCallBack, String... parseFields)
            throws NoSuchFieldException, IllegalAccessException {
        if (entities == null || entities.size() == 0 || null == key || "".equals(key) || null == parentKey || "".equals(parentKey))
            throw new RuntimeException("参数不是有效值,分析失败");
        List<Map<String, Object>> nodeList = new ArrayList<>();
        Class classzz = entities.get(0).getClass();
        TreeNodeParse parse = getNodeDescrbe(classzz, fieldPropertyMap, parseFields);
        Map<String, SnebTreeNode> treeNodeMap = buildTreeNodeMap(entities, parse, key, parentKey, parseCallBack);
        treeNodeMap.values().forEach(item -> {
            nodeList.add(item.getTag());
        });
        return nodeList;
    }

    public static <E> List<Map<String, Object>> buildTreeList(List<E> entities, String key, String parentKey, Properties fieldPropertyMap, String... parseFields)
            throws NoSuchFieldException, IllegalAccessException {
        return buildTreeList(entities, key, parentKey, fieldPropertyMap, null, parseFields);
    }

    public static <E> List<Map<String, Object>> buildTreeList(List<E> entities, String key, String parentKey, Properties fieldPropertyMap)
            throws NoSuchFieldException, IllegalAccessException {
        return buildTreeList(entities, key, parentKey, fieldPropertyMap, null);
    }

    public static <E> List<Map<String, Object>> buildTreeList(List<E> entities, String key, String parentKey)
            throws NoSuchFieldException, IllegalAccessException {
        return buildTreeList(entities, key, parentKey, null);
    }
}
