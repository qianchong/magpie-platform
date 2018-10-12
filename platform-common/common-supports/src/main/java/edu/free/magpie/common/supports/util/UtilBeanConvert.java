package edu.free.magpie.common.supports.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * project: sneb
 * Description：封装BeanUtils，对List进行类型转换。
 *
 * @author: ryan
 * @create: Created in 2018/8/9 8:33
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/8/9
 * Version: 0.0.1
 * Modified By:
 */
@Component
public class UtilBeanConvert<V, T> {
    private static UtilBeanConvert instance;

    private UtilBeanConvert() {
    }

    public static UtilBeanConvert getInstance() {
        if (instance == null) {
            instance = new UtilBeanConvert();
        }
        return instance;
    }

    /**
     * @param srcList 要转换对象
     * @param toList  转换到对象
     * @param clazz   转换的类型
     * @return
     */
    public List<T> toList(Collection<V> srcList, Collection<T> toList, Class<T> clazz) {
        return toStream(srcList, toList, clazz).collect(Collectors.toList());
    }

    /**
     * @param srcList 要转换对象
     * @param toList  转换到对象
     * @param clazz   转换的类型
     * @return
     */
    public Set<T> toSet(Collection<V> srcList, Collection<T> toList, Class<T> clazz) {
        return toStream(srcList, toList, clazz).collect(Collectors.toSet());
    }

    public Stream<T> toStream(Collection<V> srcList, Collection<T> toList, Class<T> clazz) {
        Stream stream = srcList.stream().map(temp -> {
            T vo = null;
            try {
                vo = clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            BeanUtils.copyProperties(temp, vo);
            return vo;
        });
        return stream;
    }

    /**
     *
     * @param srcList 要转换对象
     * @param toList  转换到对象
     * @param clazz   转换的类型
     * @return
     */
    public List<T> toVO(List<V> srcList, List<T> toList,Class<T> clazz) {
        return toList(srcList,toList,clazz);
    }

}
