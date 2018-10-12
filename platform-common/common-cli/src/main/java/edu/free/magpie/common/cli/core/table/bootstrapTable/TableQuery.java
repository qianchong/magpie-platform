package edu.free.magpie.common.cli.core.table.bootstrapTable;

import lombok.Data;

@Data
public class TableQuery {

    private String field;
    private Object value;
    private String oper;

    public TableQuery(String field, Object value, String oper) {
        super();
        this.field = field;
        this.value = value;
        this.oper = oper;
    }
}
