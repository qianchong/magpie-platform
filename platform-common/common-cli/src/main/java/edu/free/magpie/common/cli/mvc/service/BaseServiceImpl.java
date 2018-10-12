package edu.free.magpie.common.cli.mvc.service;


import edu.free.magpie.common.cli.core.advancequery.AdvanceQueryPaser;
import edu.free.magpie.common.cli.core.advancequery.criterion.QueryBuild;
import edu.free.magpie.common.cli.core.advancequery.criterion.R;
import edu.free.magpie.common.cli.core.table.bootstrapTable.TableQuery;
import edu.free.magpie.common.cli.core.table.bootstrapTable.TableQueryMap;
import edu.free.magpie.common.cli.mvc.dao.BaseDao;
import edu.free.magpie.common.cli.vo.ResultTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.*;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 通用Service类，封装了常用的Service层的操作
 *
 * @param <T>
 * @param <ID>
 */
public class BaseServiceImpl<T, ID extends Serializable> {

    private BaseDao<T, ID> baseDao;

    @Resource
    public JdbcTemplate jdbcTemplate;

    @Autowired
    private ApplicationContext applicationContext;

    public BaseServiceImpl(BaseDao<T, ID> baeDao) {
        this.baseDao = baeDao;
    }

    /**
     * 通用的保存
     *
     * @param entity 要保存的实体
     * @return
     */
    public T save(T entity) {
        return baseDao.save(entity);
    }

    /**
     * 通过主键编号 进行删除
     *
     * @param id 主键值
     */
    public void delById(ID id) {
        baseDao.deleteById(id);
    }

    /**
     * 通过实体对象进行删除数据
     *
     * @param entity
     */
    public void del(T entity) {
        baseDao.delete(entity);
    }

    /**
     * 通过实体的主键编号进行查询实体
     *
     * @param oid
     * @return
     */
    public T getById(ID oid) {
        return baseDao.getOne(oid);
    }

    /**
     * 通过实体主键编号集合查询实体集
     *
     * @param ids
     * @return
     */
    public List<T> findAll(Iterable<ID> ids) {
        return baseDao.findAllById(ids);
    }

    /**
     * 通过实体主键编号集删除实体集
     *
     * @param ids
     */
    public void delAll(Iterable<ID> ids) {
        List<T> list = findAll(ids);
        if (!list.isEmpty()) {
            baseDao.deleteInBatch(list);
        }
    }

    /**
     * 通过实体主键编号删除实体集
     *
     * @param ids
     */
    public void delAll(ID[] ids) {
        List<ID> idsList = java.util.Arrays.asList(ids);
        delAll(idsList);
    }

    /**
     * 通过实体集合删除实体集
     *
     * @param entities
     */
    public void delAllByEntitys(Iterable<? extends T> entities) {
        baseDao.deleteAll(entities);
    }

    /**
     * 通过主键ID查询实体
     *
     * @param id
     * @return
     */
    public T get(ID id) {
        return baseDao.getOne(id);
    }

    /**
     * 通过主键ID判断实体是否存在
     *
     * @param id
     * @return
     */
    public boolean exists(ID id) {
        return baseDao.existsById(id);
    }

    /**
     * 分页查询
     *
     * @param page 当前多少页
     * @param size 每页显示多少条
     * @return
     */
    public Page<T> findPage(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return baseDao.findAll(pageable);
    }

//    public Page<T> findPage() {
//        Pageable pageable = new PageRequest(0, 10, Sort.Direction.ASC, "oid");
//        return baseDao.findAll(pageable);
//    }

    public Page<T> findPage(PageRequest pageRequest) {
        return baseDao.findAll(pageRequest);
    }

    /**
     * param需要分页参数
     * page 当前第几页
     * limit 每页多少条
     * 查询包括两部分：键 query.name 值 张三&like
     *
     * @param param
     * @return
     */
    public ResultTable findPage(Map<String, String> param) {
        TableQueryMap tqm = AdvanceQueryPaser.getParams(param);
        Page<T> resultPage = findPage(tqm.getPageRequest(), createQueryBuild(tqm.getQuerys()));
        return new ResultTable(resultPage.getTotalElements(), resultPage.getContent());
    }

    public Page<T> findToPage(Map<String, String> param) {
        TableQueryMap tqm = AdvanceQueryPaser.getParams(param);
        return findPage(tqm.getPageRequest(), createQueryBuild(tqm.getQuerys()));
    }

    private QueryBuild<T> createQueryBuild(List<TableQuery> tqList) {
        QueryBuild<T> queryBuild = new QueryBuild<T>();
        for (TableQuery tq : tqList) {
            String oper = tq.getOper();
            Object value = tq.getValue();
            String queryField = tq.getField();
            if (AdvanceQueryPaser.EQ.equals(oper)) {
                queryBuild.add(R.eq(queryField, value, true));
            } else if (AdvanceQueryPaser.LIKE.equals(oper)) {
                queryBuild.add(R.like(queryField, value.toString(), true));
            }
        }
        return queryBuild;
    }

    public Page<T> findPage(PageRequest pageRequest, QueryBuild<T> queryParams) {
        return baseDao.findAll(queryParams, pageRequest);
    }

    /**
     * 查询全部数据
     *
     * @return
     */
    public List<T> findAll() {
        return baseDao.findAll();
    }

    public List<T> findAll(QueryBuild<T> criteria) {
        Sort sort = criteria.getSort();
        if (null == sort) {
            return baseDao.findAll(criteria);
        }
        return baseDao.findAll(criteria, sort);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll(String ids) {
        String idArr[] = ids.split(",");
        List<ID> idList = new ArrayList<ID>();
        for (String oid : idArr) {
            idList.add((ID) Long.valueOf(oid));
        }
        return this.findAll(idList);
    }

    public  <S extends T> List<S> findAll(Example<S> var1){
        return baseDao.findAll(var1);
    }
}
