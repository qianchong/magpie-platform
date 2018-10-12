package edu.free.magpie.common.cli.core.advancequery;

import edu.free.magpie.common.cli.core.table.bootstrapTable.TablePage;
import edu.free.magpie.common.cli.core.table.bootstrapTable.TableQuery;
import edu.free.magpie.common.cli.core.table.bootstrapTable.TableQueryMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description： TODO
 * Author: qianchong
 * Date: Created in 2018/1/4 12:37
 * Company: senb
 * Copyright: Copyright (c) 2018/1/4
 * Version: 0.0.1
 * Modified By:
 */
public class AdvanceQueryPaser {
    private static Logger log = LoggerFactory.getLogger(AdvanceQueryPaser.class);

    public static final String LIKE = "like";
    public static final String EQ = "eq";
    public static final String QUERY_FIX = "query.";

    /**
     * @return TableQueryMap
     */
    public static TableQueryMap getParams(Map<String, String> param) {
        TableQueryMap tqm = new TableQueryMap();
        tqm.setPageRequest(createPageRequerst(param));
        tqm.setQuerys(createTableQuery(param));
        return tqm;
    }

    /**
     * 创建pageRequest对象，里面包括当前页，当前显示多少条记录和排序
     *
     * @param params
     * @return
     */
    private static PageRequest createPageRequerst(Map<String, String> params) {
        String page = params.get("page");
        String limit = params.get("limit");
        //String order = params.get("order");

        int pageValue = StringUtils.isEmpty(page) == true ? TablePage.PAGE : Integer.parseInt(page);
        pageValue = pageValue == 1 ? 0 : pageValue;
        int pagelimit = StringUtils.isEmpty(limit) == true ? TablePage.PAGE_SIZE : Integer.parseInt(limit);
        params.remove("page");
        params.remove("limit");
        return new PageRequest(pageValue, pagelimit);
    }

    /**
     * 解析高级QUERY参数，封装到TableQuery
     *
     * @param paramap key          关键字,必须以query.开头,后面为对象属性，例子:query.name,    query.age
     *                valueAndOper 操作符，包括两部分，前面为属性的值，后面为操作符例如, 张三&like,  20&EQ
     * @return List<TableQuery>
     */
    public static List<TableQuery> createTableQuery(Map<String, String> paramap) {
        List<TableQuery> querys = new ArrayList<TableQuery>();
        for (Map.Entry<String, String> param : paramap.entrySet()) {
            String key = param.getKey();
            String valueAndOper = param.getValue();

            int keyLenth = key.length();
            int queryStrLength = QUERY_FIX.length();
            if (!key.contains(QUERY_FIX)) {
                log.warn("表格高级查询参数解析： key={} 不正确！", key);
                throw new RuntimeException("表格高级查询参数解析： key:" + key + " 不正确！");
            } else {
                String queryField = key.substring(queryStrLength, keyLenth);
                String[] valueAndOperArr = valueAndOper.split("&");
                if (valueAndOperArr.length != 2) {
                    log.warn("表格高级查询参数解析： queryField={} 不正确！", queryField);
                    throw new RuntimeException("表格高级查询参数解析： queryField:" + queryField + " 不正确！");
                }
                String value = valueAndOperArr[0];
                String oper = valueAndOperArr[1];
                querys.add(new TableQuery(queryField, value, oper));
            }
        }
        return querys;
    }
}
