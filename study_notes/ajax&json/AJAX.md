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



