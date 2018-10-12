package edu.free.magpie.common.supports.transform;

import edu.free.magpie.common.supports.exception.BizException;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Properties;
public class RetCodeProperties {
    private static Properties properties;

    static {
        try {
            properties = PropertiesLoaderUtils.loadAllProperties("resultCode.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void add(String key,String code, String title, String msg) {
        properties.put(key,code.concat(",").concat(title).concat(",").concat(msg));
    }

    public static IResultEnum get(String key) {
        if (StringUtils.isEmpty(key)) {
            throw new RuntimeException("要读取的KEY不存在");
        }
        String property = (String) properties.get(key);
        String[] split = property.split(",");
        String code = split[0];
        String title = split[1];
        String content = split[2];
        IResultEnum iResult = new IResultEnum() {
            @Override
            public String getCode() {
                return code;
            }

            @Override
            public String getTitle() {
                return title;
            }

            @Override
            public String getMsg() {
                return content;
            }

            @Override
            public IResultEnum get(String key) {
                return null;
            }
        };
        return iResult;
    }
}
