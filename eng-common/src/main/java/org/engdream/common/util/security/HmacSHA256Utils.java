package org.engdream.common.util.security;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

public class HmacSHA256Utils {

    public static String digest(String key, String content) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            byte[] secretByte = key.getBytes("utf-8");
            byte[] dataBytes = content.getBytes("utf-8");

            SecretKey secret = new SecretKeySpec(secretByte, "HMACSHA256");
            mac.init(secret);

            byte[] doFinal = mac.doFinal(dataBytes);
            byte[] hexB = new Hex().encode(doFinal);
            return new String(hexB, "utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String digest(String key, Map<String, String[]> map) {
        Map<String, String> params = sortMap(map);
        StringBuilder s = new StringBuilder();
        for(String value : params.values()) {
            s.append(value);
        }
        return digest(key, s.toString());
    }

    public static void main(String[] args) {
        Map<String, String[]> params = new HashMap<>();
        String[] arr1 = {"x","y", "b"};
        String[] arr2 = {"x","y", "b"};
        String[] arr3 = {"x","y", "b"};
        params.put("1", arr2);
        params.put("a", arr1);
        params.put("d", arr3);
        params.put("c", arr1);
        Map<String, String> sorted = sortMap(params);
        for(Map.Entry<String, String> entry : sorted.entrySet()){
            System.out.println("key:"+entry.getKey()+",value:"+entry.getValue());
        }
    }
    private static Map<String, String> sortMap(Map<String, String[]> oriMap){
        Map<String, String> temp = new LinkedHashMap<>();
        for(Map.Entry<String, String[]> entry : oriMap.entrySet()){
            List<String> list = Arrays.asList(entry.getValue());
            Collections.sort(list, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            StringBuilder builder = new StringBuilder();
            for(String str : list){
                builder.append(str);
            }
            temp.put(entry.getKey(), builder.toString());
        }
        return sortMapByValue(temp);
    }

    /**
     * 使用 Map按value进行排序
     * @param oriMap
     * @return
     */
    public static Map<String, String> sortMapByValue(Map<String, String> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, String> sortedMap = new LinkedHashMap<String, String>();
        List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(
                oriMap.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        Iterator<Map.Entry<String, String>> iter = entryList.iterator();
        Map.Entry<String, String> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }

}
