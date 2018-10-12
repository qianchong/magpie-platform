package edu.free.magpie.common.supports.transform.tree;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TreeNodeParse {
    private Properties fieldPropertyMap;
    private String[] parseFields;
    private Class clazz;

    public TreeNodeParse(Class clazz, Properties fieldPropertyMap, String[] parseFields) {
        this.clazz = clazz;
        this.fieldPropertyMap = fieldPropertyMap;
        this.parseFields = parseFields;
    }

    public NodeDescrbe parese(String key, String parentKey) throws NoSuchFieldException {
        List<Field> otherField = new ArrayList<>();
        //解析目标类上字段
        Field keyField = clazz.getDeclaredField(key);
        keyField.setAccessible(true);
        Field parentKeyField = null;
        if (parentKey.contains(".")) {
            String[] split = parentKey.split("\\.");
            String obj = split[0];
            parentKeyField = clazz.getDeclaredField(obj);
        } else {
            parentKeyField = clazz.getDeclaredField(parentKey);
        }
        parentKeyField.setAccessible(true);

        NodeDescrbe nodeDescrbe = new NodeDescrbe(clazz, otherField);
        nodeDescrbe.setKeyField(key, keyField);
        nodeDescrbe.setParentField(parentKey, parentKeyField);

        if (parseFields == null || parseFields.length == 0) {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);//设置可以访问私有字段
                otherField.add(field);
            }
        } else {
            for (String fieldName : parseFields) {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);//设置可以访问私有字段
                otherField.add(field);
            }
        }
        return nodeDescrbe;
    }

    public String getJsonProperty(String key) {
        return fieldPropertyMap.containsKey(key) == true ? fieldPropertyMap.getProperty(key) : key;
    }

    public <E> void put(SnebTreeNode node, NodeDescrbe nodeDescrbe, E e) throws IllegalAccessException, NoSuchFieldException {
        node.getTag().put(getJsonProperty(nodeDescrbe.getField().getName()), nodeDescrbe.getField().get(e));
        String parentKey = nodeDescrbe.getParentKey();
        Field parentField = nodeDescrbe.getParentField();
        if (parentKey.contains(".")) {
            String[] split = parentKey.split("\\.");
            String fieldName = split[1];

            Object o = parentField.get(e);
            if (null != o) {
                Field declaredField = o.getClass().getDeclaredField(fieldName);
                declaredField.setAccessible(true);
                Object o1 = declaredField.get(o);
                node.getTag().put(getJsonProperty(parentKey), o1);
            }
        } else {
            node.getTag().put(getJsonProperty(nodeDescrbe.getParentField().getName()), parentField.get(e));
        }


        for (Field f : nodeDescrbe.getOtherField()) {
            node.getTag().put(getJsonProperty(f.getName()), f.get(e));
        }
    }

    public <E> String getParentFieldValue(Field parentField, E e, String parentKey) throws IllegalAccessException, NoSuchFieldException{
        if (parentKey.contains(".")) {
            String[] split = parentKey.split("\\.");
            String fieldName = split[1];
            Object o = parentField.get(e);
            if (null != o) {
                Field declaredField = o.getClass().getDeclaredField(fieldName);
                declaredField.setAccessible(true);
                Object o1 = declaredField.get(o);
                return o1 + "";
            }
        }
        return  parentField.get(e) + "";
    }

    /**
     * 树的描述文件
     */
    public class NodeDescrbe {
        private String key;
        private String parentKey;
        private Field field;
        private Field parentField;
        /**
         * 当前目录类类型
         */
        private Class clazz;


        /**
         * 要生成的其它类字段
         */
        private List<Field> otherField = new ArrayList<>();

        public NodeDescrbe(Class clazz, List<Field> otherField) {
            this.clazz = clazz;
            this.otherField = otherField;
        }

        public void setKeyField(String key, Field keyField) {
            this.key = key;
            this.field = keyField;
        }

        public void setParentField(String parentKey, Field parentField) {
            this.parentKey = parentKey;
            this.parentField = parentField;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getParentKey() {
            return parentKey;
        }

        public void setParentKey(String parentKey) {
            this.parentKey = parentKey;
        }

        public Field getField() {
            return field;
        }

        public void setField(Field field) {
            this.field = field;
        }

        public Field getParentField() {
            return parentField;
        }

        public void setParentField(Field parentField) {
            this.parentField = parentField;
        }

        public Class getClazz() {
            return clazz;
        }

        public void setClazz(Class clazz) {
            this.clazz = clazz;
        }

        public List<Field> getOtherField() {
            return otherField;
        }

        public void setOtherField(List<Field> otherField) {
            this.otherField = otherField;
        }
    }
}
