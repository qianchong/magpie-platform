package edu.free.magpie.common.cli.core.advancequery.criterion;

import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * 条件构造器 用于创建条件表达式
 *
 * @Class Name Restrictions
 * @Author lee
 */
public class R {

    /**
     * 等于
     *
     * @param fieldName  属性
     * @param value      值
     * @param ignoreNull 忽略null值
     * @return
     */
    public static SimpleExpression eq(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Criterion.Operator.EQ);
    }

    /**
     * 不等于
     *
     * @param fieldName  属性
     * @param value      值
     * @param ignoreNull 忽略null值
     * @return
     */
    public static SimpleExpression ne(String fieldName, Object value,
                                      boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Criterion.Operator.NE);
    }

    /**
     * 模糊匹配
     *
     * @param fieldName  属性
     * @param value      值
     * @param ignoreNull 忽略null值
     * @return
     */
    public static SimpleExpression like(String fieldName, String value,
                                        boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Criterion.Operator.LIKE);
    }

    /**
     * 大于
     *
     * @param fieldName  属性
     * @param value      值
     * @param ignoreNull 忽略null值
     * @return
     */
    public static SimpleExpression gt(String fieldName, Object value,
                                      boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Criterion.Operator.GT);
    }

    /**
     * 小于
     *
     * @param fieldName  属性
     * @param value      值
     * @param ignoreNull 忽略null值
     * @return
     */
    public static SimpleExpression lt(String fieldName, Object value,
                                      boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Criterion.Operator.LT);
    }

    /**
     * 小于等于
     *
     * @param fieldName  属性
     * @param value      值
     * @param ignoreNull 忽略null值
     * @return
     */
    public static SimpleExpression lte(String fieldName, Object value,
                                       boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Criterion.Operator.GTE);
    }

    /**
     * 大于等于
     *
     * @param fieldName  属性
     * @param value      值
     * @param ignoreNull 忽略null值
     * @return
     */
    public static SimpleExpression gte(String fieldName, Object value,
                                       boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Criterion.Operator.LTE);
    }

    /**
     * 并且
     *
     * @param criterions
     * @return
     */
    public static LogicalExpression and(Criterion... criterions) {
        return new LogicalExpression(criterions, Criterion.Operator.AND);
    }

    /**
     * 或者
     */
    public static LogicalExpression or(Criterion... criterions) {
        return new LogicalExpression(criterions, Criterion.Operator.OR);
    }

    /**
     * 包含
     *
     * @param fieldName  属性
     * @param value      值
     * @param ignoreNull 忽略null值
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static LogicalExpression in(String fieldName, Collection value, boolean ignoreNull) {
        boolean ignore = ignoreNull && (value == null || value.isEmpty());
        if (ignore) {
            return null;
        } else {
            SimpleExpression[] ses = new SimpleExpression[value.size()];
            int i = 0;
            for (Object obj : value) {
                ses[i] = new SimpleExpression(fieldName, obj, Criterion.Operator.EQ);
                i++;
            }
            return new LogicalExpression(ses, Criterion.Operator.OR);
        }
    }

    /**
     * 不包含
     *
     * @param fieldName  属性
     * @param value      值
     * @param ignoreNull 忽略null值
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static LogicalExpression NotIn(String fieldName, Collection value, boolean ignoreNull) {
        boolean ignore = ignoreNull && (value == null || value.isEmpty());
        if (ignore) {
            return null;
        } else {
            SimpleExpression[] ses = new SimpleExpression[value.size()];
            int i = 0;
            for (Object obj : value) {
                ses[i] = new SimpleExpression(fieldName, obj, Criterion.Operator.NE);
                i++;
            }
            return new LogicalExpression(ses, Criterion.Operator.AND);
        }
    }

    /**
     * 包含
     *
     * @param fieldName  属性
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static SimpleExpression isNull(String fieldName) {
        return new SimpleExpression(fieldName, null, Criterion.Operator.IsNULL);
    }
}