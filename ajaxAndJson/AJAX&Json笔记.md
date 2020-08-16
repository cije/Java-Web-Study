# AJAX

 1.    概念：Asynchronous Javascript And *XML* And *HTML*   异步JavaScript和XML 或 HTML

       -   Ajax 是指一种创建交互式、快速动态网页网页应用的网页开发技术，无需重新加载整个网页的情况下，能够更新部分网页的技术。

           通过在后台与服务器进行少量数据交换，Ajax 可以使网页实现异步更新。这意味着可以在不重新加载整个网页的情况下，对网页的某部分进行更新。

       -   异步和同步：客户端和服务器端相互通信的基础上

           -   同步：客户端必须等待服务器端的响应。在等待的期间客户端不能做其他操作。
           -   异步：客户端不需要等待服务器端的响应。在服务器处理请求的过程中，客户端可以进行其他的操作。

 2.    实现方式：

        1.    原生的J实现方式（了解）

              ```javascript
              //发送异步请求
              //1.创建核心对象
              var xmlhttp;
              if (window.XMLHttpRequest) {
                  // code for IE7+, Firefox, Chrome, Opera, Safari
              	xmlhttp = new XMLHttpRequest();
              } else {
                  // code for IE6, IE5
              	xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
              }
              //2.建立连接
              /*
              	参数：
              		1.请求方式
              			* get方式请求参数在url后面拼接 send方法为空参
              			* post方式请求参数send方法内定义
              		2.请求的URL
              		3.同步（false）或异步（true）请求
              */
              xmlhttp.open("GET", "ajaxServlet?username=tom", true);
              //3.发送请求
              xmlhttp.send();
              //4.接收并处理来自服务器的响应结果
              //获取方式 xmlhttp.responseText;
              //当服务器响应成功后再获取
              
              //当xmlhttp对象的就绪状态改变 时，出发时间onreadystatechange
              xmlhttp.onreadystatechange = function () {
              //判断readyState就绪状态是否为4，判断status响应代码是否为200
              	if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
              
              		let responseText = xmlhttp.responseText;
              		document.getElementById("myInput").value = responseText;
              	}
              }
              ```

              

        2.    JQuery实现方式

               	1. `$.ajax()`
                   -   语法：`$.ajax({键值对});`
               	2. `$.get()`：发送get请求
                   -   语法：`$.get(url,[data],[callback],[type])`
                       -   参数：
                           -   url：请求路径
                           -   data：请求参数
                           -   callback：回到函数
                           -   type：响应结果的类型
               	3. `$.post()`：发送post请求
                   -   语法：`$.get(url,[data],[callback],[type])`
                       -   参数：
                           -   url：请求路径
                           -   data：请求参数
                           -   callback：回到函数
                           -   type：响应结果的类型

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
            	1. 使用步骤：
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

# 案例：

 -    校验用户名是否存在

      1.  服务器响应的数据，在客户端使用时，要想当作json数据格式使用时

          1.  `$.get(type)`：将type指定为"json"

          2.  在服务器设置MIME类型

              `response.setContentType("application/json;charset=utf-8");`



