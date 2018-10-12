package edu.free.magpie.common.cli.core.table.bootstrapTable;

import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

public class TableQueryMap {

    private List<TableQuery> querys = new ArrayList<TableQuery>();
    private PageRequest pageRequest;

    public List<TableQuery> getQuerys() {
        return querys;
    }

    public void setQuerys(List<TableQuery> querys) {
        this.querys = querys;
    }

    public PageRequest getPageRequest() {
        return pageRequest;
    }

    public void setPageRequest(PageRequest pageRequest) {
        this.pageRequest = pageRequest;
    }
}
