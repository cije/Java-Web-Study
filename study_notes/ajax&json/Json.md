# JSON

 1.    概念：JavaScript Object Notation         JavaScript 对象简谱（表示法）

       -   json现在多用于存储和交换文本信息的语法
       -   进行数据的传输
       -   JSON比XML更小、更快，更易解析。

2. 语法：

    1. 基本规则

        -   数据在名称/值对中：json数据是由键值对构成的
            -   键用引号引起来，也可以不使用引号
            -   值：
                -   数字（整数或浮点数）
                -   字符串（在双引号中）
                -   逻辑值（true 或 false）
                -   数组（在方括号[]中）
                -   对象（在花括号{}中）
                -   null
        -   数据由逗号分隔：多个键值对由逗号分隔
        -   花括号保存对象：使用{}定义json格式
        -   方括号保存数组：[]

    2. 获取数据：

        1. `json对象.键名`
        2. `json对象["键名"]`
        3. `数组对象[索引]`

    3. JSON数据和Java对象的相互转换

        -   JSON解析器：
            -   常见的解析器：`Jsonlib`，`Gson`,`fastjson`,`jackson`

        1. JSON转为Java对象
            1. 导入jackson相关jar包
            2. 创建Jackson核心对象 ObjectMapper
            3. 调用ObjectMapper的相关方法进行转换
                1. `readValue(json字符串数据,Class)`
        2. Java都西昂转换JSON
            - 使用步骤：
                1. 导入jackson相关jar包
                2. 创建Jackson核心对象 ObjectMapper
                3. 调用ObjectMapper的相关方法进行转换
                    1. 转换方法：
                        -   weiteValue(参数1,obj)
                            -   参数1：
                                -   File：将obj对象转换为json对象，并将其保存到文件中
                                -   Writer：将obj对象转换为json对象，并将其填充到字符输出流中
                                -   OutputStream：将obj对象转换为json对象，并将json数据填充到字节输出流中
                        -   writeValueAsString(obj)  将对象转为json字符串
                    2. 注解
                        1. `@JsonIgnore`：排除属性
                        2. `@JsonFormat`：属性值格式化
                    3. 复杂java对象转换
                        1. List：数组
                        2. Map：对象格式一致