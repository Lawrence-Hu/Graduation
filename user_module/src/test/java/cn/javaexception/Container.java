package cn.javaexception;

import java.util.HashMap;
import java.util.Map;

public class Container {

    Map<Class,Object> map = new HashMap<>();

    public Object getObj(Class name) {
        return map.get(name);
    }

    public void addObj(Class name,Object o) {
        map.put(name,o);
    }
}
