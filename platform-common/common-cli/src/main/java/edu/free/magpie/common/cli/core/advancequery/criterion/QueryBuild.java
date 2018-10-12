package edu.free.magpie.common.cli.core.advancequery.criterion;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 定义一个查询条件容器
 */
public class QueryBuild<T> implements Specification<T> {
	private List<Criterion> criterions = new ArrayList<>();
	private Sort sort = null;

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if (!criterions.isEmpty()) {
			List<Predicate> predicates = new ArrayList<>();
			for (Criterion c : criterions) {
				predicates.add(c.toPredicate(root, query, cb));
			}
			// 将所有条件用 and 联合起来
			if (predicates.size() > 0) {
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		} else {

		}
		return cb.conjunction();
	}

	/**
	 * 增加简单条件表达式
	 */
	public void add(Criterion criterion) {
		if (criterion != null) {
			criterions.add(criterion);
		}
	}

	public Sort getSort() {
		return sort;
	}

	public void addSortDesc(String field) {
		sort = new Sort(Direction.DESC, new String[] { field });
	}

	public void addSortAsc(String field) {
		sort = new Sort(Direction.ASC, new String[] { field });
	}
}
