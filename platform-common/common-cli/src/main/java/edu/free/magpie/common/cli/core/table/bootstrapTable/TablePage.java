package edu.free.magpie.common.cli.core.table.bootstrapTable;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class TablePage implements Serializable {

    private static final long serialVersionUID = -1680338318687461480L;

    public static final int PAGE = 0;
    public static final int PAGE_SIZE = 20;

    private long total;

    private List rows = new ArrayList<Object>();

    public TablePage() {
        super();
    }

    public TablePage(long total, List list) {
        super();
        this.total = total;
        this.rows = list;
    }
}
