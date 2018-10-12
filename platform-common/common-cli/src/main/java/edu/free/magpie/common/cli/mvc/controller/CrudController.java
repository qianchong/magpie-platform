package edu.free.magpie.common.cli.mvc.controller;

import edu.free.magpie.common.cli.mvc.service.BaseServiceImpl;
import edu.free.magpie.common.cli.vo.ResultTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


public class CrudController<T, ID extends Serializable> extends BaseController {

    private final Logger log = LoggerFactory.getLogger(CrudController.class);

    BaseServiceImpl<T, ID> baseService;

    protected CrudController(BaseServiceImpl<T, ID> service) {
        this.baseService = service;
        getDaoBeanForApplicationContext();
        initPrefix();
    }


    @RequestMapping("/add")
    public String add() {
        setAttribute("entity", entity);
        return getPrefix(ADD);
    }

    /**
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public ResultTable list(@RequestParam Map<String, String> param) {
        return baseService.findPage(param);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(T entity) {
        preSave(entity);
        baseService.save(entity);
        return index();
    }

    public void preSave(T entity) {
    }


    @RequestMapping(value = "/edit/{oid}")
    public String edit(@PathVariable ID oid) {
        T entity = baseService.getById(oid);
        setAttribute("entity", entity);
        return getPrefix(ADD);
    }

    public void preEdit(T entity) {

    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public String del(@RequestParam(value = "ids[]") ID[] ids) {
        baseService.delAll(ids);
        return index();
    }

    /**
     * Entity Class Type
     */
    private Class<T> clazz;

    /**
     * Entity Class instance
     */
    private T entity;

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void getDaoBeanForApplicationContext() {
        Class c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
            clazz = (Class<T>) pt.getActualTypeArguments()[0];
            try {
                entity = clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String initPrefix() {
        prefix = getViewDir();
        if (StringUtils.isEmpty(prefix)) {
            prefix = clazz.getSimpleName();
            prefix = toLowerCaseFirstOne(prefix);
            log.info("init prefix {}", prefix);
        }
        return prefix;
    }

//    private QueryBuild<T> createQueryBuild(List<TableQuery> tqList) {
//        QueryBuild<T> queryBuild = new QueryBuild<T>();
//        for (TableQuery tq : tqList) {
//            String oper = tq.getOper();
//            Object value = tq.getValue();
//            String queryField = tq.getField();
//            if (AdvanceQueryPaser.EQ.equals(oper)) {
//                queryBuild.add(R.eq(queryField, value, true));
//            } else if (AdvanceQueryPaser.LIKE.equals(oper)) {
//                queryBuild.add(R.like(queryField, value.toString(), true));
//            }
//        }
//        return queryBuild;
//    }
    /**
     * 首字母转小写
     *
     * @param s
     * @return
     */
    private String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }
    public String getViewDir() {
        return null;
    }

    @RequestMapping(value = "/findAll")
    public List<T> findAll() {
        return baseService.findAll();
    }
}