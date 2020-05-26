package com.bigdata.hy_tools.utils;

import java.util.LinkedHashMap;

/**
 * @author huyi
 * @date 2020/5/25 17:59
 */
public class TestUtil {
    private static final LinkedHashMap linkedHashMap = new LinkedHashMap<>(16, 0.75f, true);

    public void init() {

    }

    public String getValue(Integer key) {
        return linkedHashMap.get(key).toString();
    }

    public void setValue(Integer key, String value) {
        linkedHashMap.put(key, value);
    }

    public void getPrint() {
        linkedHashMap.forEach((k, v) -> System.out.println(k + "," + v));
    }
}
