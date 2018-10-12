package edu.free.magpie.common.cli.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ResultTable implements Serializable {
    private static final long serialVersionUID = -5471550257612512956L;

    /** 返回码 */
    private String code;

    /** 附加返回信息 */
    private String msg;

    /** 总记录数 */
    private long count;

    /**查询出的记录 */
    private List data = new ArrayList<Object>();

    public ResultTable() {
        super();
    }

    public ResultTable(long total, List list) {
        super();
        this.msg = "";
        this.code = "0";
        this.count = total;
        this.data = list;
    }
}
