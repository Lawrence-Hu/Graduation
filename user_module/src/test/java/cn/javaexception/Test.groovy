package cn.javaexception


import java.lang.reflect.Field
import java.lang.reflect.InvocationTargetException

class Test{
    static void main(String[] args) throws UnsupportedEncodingException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Container container = new Container()
        List<Class<?>> allClassByPackageName = ClassUtil.getAllClassByPackageName(DemoApplication.class.getPackage())
        for (Class<?> clazz : allClassByPackageName) {
            if(null!=clazz.getAnnotation(Component.class)){
                Object o = clazz.newInstance()
                container.addObj(clazz,o)
            }
        }
        for (Class<?> clazz : allClassByPackageName) {
            for (Field field : clazz.getDeclaredFields()) {
                if(null!=field.getAnnotation(Inject.class)){
                    field.setAccessible(true)
                    Object obj = container.getObj(clazz)
                    if (null!= obj){
                        field.set(obj, obj)
                    }
                }
            }
        }
        def demo = container.getObj(Demo.class) as Demo
        println demo.getName()

    }
}
