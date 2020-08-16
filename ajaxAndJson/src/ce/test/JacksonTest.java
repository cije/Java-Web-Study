package ce.test;

import ce.domain.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JacksonTest {
    //Java对啊ing转为JSON字符串
    @Test
    public void test1() throws IOException {
        //1.创建Person对象
        Person p = new Person();
        p.setName("张三");
        p.setAge(12);
        p.setGender("男");

        //2.创建Jackson核心对象
        ObjectMapper mapper = new ObjectMapper();

        //3.转换
        /*
            转换方法：
                weiteValue(参数1,obj)
                    参数1：
                        File：将obj对象转换为json对象，并将其保存到文件中
                        Writer：将obj对象转换为json对象，并将其填充到字符输出流中
                        OutputStream：将obj对象转换为json对象，并将json数据填充到字节输出流中
                writeValueAsString(obj)  将对象转为json字符串
         */
        String json = mapper.writeValueAsString(p);
//        System.out.println(json);

        //writeValue 写到文件中
//        mapper.writeValue(new File("E:\\DATA\\lenovo\\Desktop\\p.json"), p);

        mapper.writeValue(new FileWriter("E:\\DATA\\lenovo\\Desktop\\p.json"), p);
    }

    @Test
    public void test2() throws IOException {
        //1.创建Person对象
        Person p = new Person();
        p.setName("张三");
        p.setAge(12);
        p.setGender("男");
        p.setBirthday(LocalDate.now());

        //2.创建Jackson核心对象
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(p);
        System.out.println(json);
    }

    @Test
    public void test3() throws IOException {
        //1.创建Person对象
        Person p = new Person();
        p.setName("张三");
        p.setAge(12);
        p.setGender("男");
        p.setBirthday(LocalDate.now());

        Person p1 = new Person();
        p1.setName("张三");
        p1.setAge(12);
        p1.setGender("男");
        p1.setBirthday(LocalDate.now());

        Person p2 = new Person();
        p2.setName("张三");
        p2.setAge(12);
        p2.setGender("男");
        p2.setBirthday(LocalDate.now());

        //创建list集合
        List<Person> list = new ArrayList<>();
        list.add(p);
        list.add(p1);
        list.add(p2);
        //2.创建Jackson核心对象
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);
        System.out.println(json);
    }

    @Test
    public void test4() throws IOException {
        //1.创建Map
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 23);
        map.put("gender", "男");
        //2.创建Jackson核心对象
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(map);
        System.out.println(json);
    }

    /**
     * json字符串转为java对象
     *
     * @throws IOException
     */
    @Test
    public void test5() throws IOException {
        //1.初始化json字符串
        String json = "{\"gender\":\"男\",\"name\":\"张三\",\"age\":23}";
        //2.创建Jackson核心对象
        ObjectMapper mapper = new ObjectMapper();
        //3.转换为Person对象
        Person person = mapper.readValue(json, Person.class);
        System.out.println(person);
    }
}
