import java.util.HashMap;
import java.util.Map;

/**
 * desc
 *
 * @author fanyin
 * @date 2019/10/11 10:51
 **/
public class TT {

    public static void main(String[] args) {
        Map source = new HashMap(2){{
            put("firstName", "John");
            put("lastName", "Smith");
        }};

        System.out.println(source.getClass());
    }
}
