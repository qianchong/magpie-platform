package edu.free.magpie.common.supports.transform.tree;

import java.util.ArrayList;
import java.util.List;

public class BuildTreeTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        new BuildTreeTest().getTree();
    }

    public void getTree() throws NoSuchFieldException, IllegalAccessException {
        List<BuildTreeTestVO> treeList = new ArrayList<>();
        BuildTreeTestVO sysFunction = new BuildTreeTestVO("L01", null, "营销管理", "没有");
        treeList.add(sysFunction);

        BuildTreeTestVO sysFunction1 = new BuildTreeTestVO("L02", null, "营销管理", "没有");
        sysFunction1.setParent(sysFunction);
        treeList.add(sysFunction1);
//        treeList.add(new BuildTreeTestVO("L02", null, "客户管理", "没有"));
//        treeList.add(new BuildTreeTestVO("L03", null, "服务管理", "没有"));
//        //营销管理的子功能
//        treeList.add(new BuildTreeTestVO("L0101", "L01", "创建销售机会", "没有"));
//        treeList.add(new BuildTreeTestVO("L0102", "L01", "指派销售机会", "没有"));
//        treeList.add(new BuildTreeTestVO("L0103", "L01", "制定开发计划", "没有"));
//        treeList.add(new BuildTreeTestVO("L0104", "L01", "执行开发计划", "没有"));
////        //指派销售机会的子功能
//        treeList.add(new BuildTreeTestVO("L010201", "L0102", "经理指派", "没有"));
//        treeList.add(new BuildTreeTestVO("L010202", "L0102", "主管指派", "没有"));
////        //客户管理的子功能
//        treeList.add(new BuildTreeTestVO("L0201", "L02", "客户信息管理", "没有"));
//        treeList.add(new BuildTreeTestVO("L0202", "L02", "客户流失管理", "没有"));
////        //服务管理子功能
//        treeList.add(new BuildTreeTestVO("L0301", "L03", "咨询服务管理", "没有"));
//        treeList.add(new BuildTreeTestVO("L0302", "L03", "投诉服务管理", "没有"));
//        treeList.add(new BuildTreeTestVO("L0303", "L03", "查询服务管理", "没有"));

//        Properties p = new Properties();
//        p.setProperty("code", "id");//将SysFunction对象里的属性名“code”映射到json对象里的属性名“id”
//        p.setProperty("title", "name");//将SysFunction对象里的属性名“title”映射到json对象里的属性名“name”
//        p.setProperty("parent.code", "pid");//将SysFunction对象里的属性名“title”映射到json对象里的属性名“name”
//
//        List<Map<String, Object>> snebTreeNodes = SnebTreeNode.parseListToJsonTree(treeList, "code", "parent.code", p, new IParseTreeNodeCallBack() {
//            public void processTreeNode(SnebTreeNode node) {
//                node.getTag().put("open", true);//向节点追加一个属性,表示要zTree展开这个节点
//                node.getTag().put("click", "alert('" + node.getTag().get("id") + "')");//向节点追加一个属性click,表示要求zTree的节点具有响应点击事件的能力
//            }
//        }, new String[]{"title"});

    }
}
