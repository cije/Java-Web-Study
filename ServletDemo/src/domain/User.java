package domain;

import java.security.PublicKey;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User {
    private String name;
    private int age;
    private LocalDateTime date;

    public User(String name, int age, LocalDateTime date) {
        this.name = name;
        this.age = age;
        this.date = date;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDateTime getDate() {
        return date;
    }

    /**
     * 逻辑视图
     *
     * @return 日期格式化字符串
     */
    public String getDateStr() {
        if (date != null) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
            String format = df.format(date);
            return format;
        } else {
            return "";
        }

    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
