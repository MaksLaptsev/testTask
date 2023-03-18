package ru.clevertec.utils.checkCreator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Converter<K,V> {
    public Map<K,Integer> getMapFromList(List<K> list){
        Map<K,Integer> map = new HashMap<>();
        for (K k:list) {
            if(!map.containsKey(k)){
                map.put(k,1);
            }else {map.put(k, map.get(k)+1);}
        }
        return map;
    }
}
