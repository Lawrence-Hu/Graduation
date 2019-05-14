package cn.javaexception;
@Component
public class Demo {
    @Inject
    private Demo name;

    public Object getName() {
        return name;
    }

    public void setName(Demo name) {
        this.name = name;
    }
}
