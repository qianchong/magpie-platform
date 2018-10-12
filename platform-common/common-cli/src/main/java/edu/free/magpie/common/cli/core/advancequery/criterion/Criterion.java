package edu.free.magpie.common.cli.core.advancequery.criterion;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Description： TODO
 *
 * @author: qianchong
 * Date: Created in 2018/5/25 13:45
 * Company: senb
 * Copyright: Copyright (c) 2018/5/25
 * Version: 0.0.1
 * Modified By:
 */
public interface Criterion {

    /**
     * 条件表达式
     */
    enum Operator {
        EQ, NE, LIKE, GT, LT, GTE, LTE, AND, OR, IsNULL
    }

    /**
     * @param root
     * @param query
     * @param builder
     * @return
     */
    Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder);
}