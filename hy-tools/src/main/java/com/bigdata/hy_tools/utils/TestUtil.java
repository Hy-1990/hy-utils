package com.bigdata.hy_tools.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicReference;

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

    public static LinkedHashMap getZK(String context) {
        LinkedHashMap result = new LinkedHashMap();
        try {
            JSONObject jsonObject = JSONObject.parseObject(context);
            if (!jsonObject.containsKey("collectData"))
                return result;
            JSONObject collectJsonObject = jsonObject.getJSONObject("collectData");
            if (!collectJsonObject.containsKey("ipEntities"))
                return result;
            JSONArray ipEntities = collectJsonObject.getJSONArray("ipEntities");
            if (EmptyUtil.isEmpty(ipEntities) || ipEntities.size() == 0)
                return result;
            Iterator<Object> entities = ipEntities.iterator();
            while (entities.hasNext()) {
                JSONObject entity = (JSONObject) entities.next();
                if (!entity.containsKey("ip") || !entity.containsKey("fields"))
                    continue;
                String ip = entity.getString("ip");
                JSONArray fields = entity.getJSONArray("fields");
                if (EmptyUtil.isEmpty(fields) || fields.size() == 0)
                    continue;
                JSONArray real_fields = new JSONArray();
                fields.stream().filter(x -> EmptyUtil.isNotEmpty((JSONObject) x)).forEach(real_fields::add);
                LinkedHashMap listLinkedHashMap = new LinkedHashMap<>();
                for (Object x : fields) {
                    JSONObject field = (JSONObject) x;
                    for (String key : field.keySet()) {
                        Object value = field.get(key);
                        listLinkedHashMap.put(key, value);
                    }
                }
                result.put(ip, listLinkedHashMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result.toString());
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<String> linkedBlockingDeque = new LinkedBlockingDeque<>();
        linkedBlockingDeque.put("a");
        linkedBlockingDeque.put("b");
        linkedBlockingDeque.put("c");
        linkedBlockingDeque.put("d");
        linkedBlockingDeque.put("e");



        System.out.println(linkedBlockingDeque.peekFirst());
        linkedBlockingDeque.forEach(System.out::println);
    }
}
