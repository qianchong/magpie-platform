package edu.free.magpie.common.supports.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class UtilExceptionStackMsg {
    /**
     * 收集异常堆栈信息
     */
    public static String collectExceptionStackMsg(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        String strs = sw.toString();
        return strs;
    }

}
