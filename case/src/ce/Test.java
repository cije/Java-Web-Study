package ce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String, String[]> map = new HashMap<>();
        map.put("name", new String[]{"笨蛋", "坏蛋"});
        map.put("msg", new String[]{"da笨蛋", "da坏蛋"});
        for (String key : map.keySet()) {
            String[] values = map.get(key);
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                if (value.contains("笨蛋") || value.contains("坏蛋")) {
                    value = value.replaceAll("笨蛋", "***");
                    value = value.replaceAll("坏蛋", "***");
                }
                values[i] = value;
            }
            map.put(key, values);
        }
        for (String s : map.keySet()) {
            System.out.println(Arrays.toString(map.get(s)));
        }
    }
}
